package com.mars.controller;

import com.mars.display.Display;
import com.mars.gui.alt.GameFrame;
import com.mars.objects.Inventory;
import com.mars.objects.Item;
import com.mars.objects.Location;
import com.mars.puzzle.GhPuzzle;
import com.mars.puzzle.Puzzle;
import com.mars.puzzle.ReactorPuzzle;
import com.mars.puzzle.SolarPuzzle;
import com.mars.stats.Stats;
import com.mars.util.Audio;
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;
import com.mars.util.*;
import com.sun.tools.javac.Main;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class GameController {
    private final Audio audio = Audio.getInstance();
    private final JSONHandler jsonhandler = new JSONHandler();
    private final Map<String, Location> locationMap = jsonhandler.loadLocationMap();
    private final Stats playerStats = new Stats();
    private final GameFrame gui;
    private Location currentLocation;
    private Inventory inventory = Inventory.getInstance();
    private Display display = new Display();
    private String dieTime;
    private int minutesToCompleteGame = 10;
    private Random rand;
//    TIME


    public GameController(GameFrame gui) {
        this.gui = gui;
        this.currentLocation = locationMap.get("Docking Station");
        gui.setTitleScreenHandler(new TitleScreenHandler());

//        TIME set up end time
        dieTime = TimerSetUp.timeRun(minutesToCompleteGame);
        rand = new Random();

    }

    // Title Screen stuff
    class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            System.out.println("hello1");
            gui.createGameScreen(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString(),
                    TimeCalc.findDifferenceGUI(dieTime, minutesToCompleteGame)

            );
            gui.setDirectionChoiceButtonListeners(new GameScreenHandler());
            gui.setChallengeButtonListeners(new PuzzleButtonHandler());
            gui.setItemButtonListeners(new ItemButtonHandler());
            gui.setInventoryListener(new InventoryButtonHandler());
            gui.setMainMenuButtonListeners(new MainMenuButtonHandler());
            gui.setLocationInfo(currentLocation);
            String storySplash = display.displayGUI("text/game_info.txt");

            gui.popUp(storySplash);
        }
    }

    // Game Screen stuff
    class GameScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

//            Game Events will go here
            IsGameEventActive.playerAtGym(gui,
                    playerStats, currentLocation, locationMap,
                    inventory, dieTime, minutesToCompleteGame);
            IsGameEventActive.playerAtGreenHouse(currentLocation, audio);
            IsGameEventActive.playerAtMiddleBuilding(currentLocation, audio);

//            Game GUI
            checkForRandomEventAndEditLocation();
            System.out.println("hello3");
            // get text input from field
            String choice = ((JButton) e.getSource()).getText();
            // get direction from input
            String direction = choice.split(" ")[1];
            // get next location name
            String nextRoomName = currentLocation.getDirections().get(direction);
            // set current location to next location
            currentLocation = locationMap.get(nextRoomName);
            // update gui with new location info
            gui.setLocationInfo(currentLocation);
            // Subtract Health and Bone Density per turn
            playerStats.updateCurrentBoneLoss(2);
            playerStats.updateCurrentHealthLoss(5);
            //add User Stats
            gui.playerSetup(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString(),
                    TimeCalc.findDifferenceGUI(dieTime, minutesToCompleteGame)
            );

//            is game over yet??
            IsGameOverYet.timeUp(gui, TimeCalc.findDifferenceGUI(dieTime, minutesToCompleteGame));
            IsGameOverYet.puzzlesSolved(gui, audio);
            IsGameOverYet.statsAtZero(gui,playerStats);
        }
    }

    public void checkForRandomEventAndEditLocation() {
        Item item = null;
        int randomNum = rand.nextInt(20);
        if (randomNum != 1) {
            item = new Item("Steve", "seems like a cool guy");
        }

        if (randomNum != 5) {
            Item alien = new Item("lil alien", "\"I work for Amazon\"");
            Item banana = new Item("banana", "it is a glowing banana healthItem");
            List<Item> roomItems = new ArrayList<>(Arrays.asList(alien ,banana));
            Map<String,String> directions = Map.of("north", "Green House");
            locationMap.put("Middle Building",
                    new Location("Middle Building",
                            directions,
                            "I don't remember this place being here...",
                            roomItems, false, "", false));
            locationMap.get("Hydro").getDirections().put("south", "Middle Building");
        }

        if (item != null) currentLocation.addItem(item);
    }

    class ItemButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get item name from button click
            String itemName = ((JButton) e.getSource()).getText();
            // remove item from current location and get reference
            if (inventory.getInventory().size() <= 1){
                System.out.println(inventory.getInventory().size());
                Item removedItem = currentLocation.removeItem(itemName);
                // add item to inventory
                inventory.add(removedItem);
                // reload location to show item is gone
                System.out.println("Inventory: " + inventory.getInventory());
                gui.setLocationInfo(currentLocation);

            }
            else {
                System.out.println("Inventory full");
                gui.popUp("You inventory is full, drop items if needed.");
                gui.setLocationInfo(currentLocation);
            }
        }
    }

    class InventoryButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inventoryName = ((JButton) e.getSource()).getText();
            String inventoryDescription = inventory.getItemDescription(inventoryName);

            int answer = gui.popUpInventory(inventoryName, inventoryDescription);
            if (answer == 2){
                gui.setLocationInfo(currentLocation);
                System.out.println("using item " + inventoryName );
                Item itemToUse = inventory.getItem(inventoryName);
                int value = inventory.use(itemToUse);
                if (value == 999 || value == 998) {
                    System.out.println();
                }
                else if (value > 0){
                    System.out.println(value);
                    playerStats.updateCurrentHealthGain(value);
                    gui.playerSetup(
                            playerStats.getStats().get("Health"),
                            playerStats.getStats().get("Bone Density"),
                            inventory.getInventory().toString(),
                            TimeCalc.findDifferenceGUI(dieTime, minutesToCompleteGame)
                    );
                    gui.popUp("You ate " + inventoryName + " and got " + value + " health back");
                }
                else{
                    gui.popUp("You cant use " + inventoryName);
                }
            }
            else if(answer == 1){
                System.out.println("dropping item " + inventoryName);
                Item dropItem = inventory.drop(inventoryName);
                currentLocation.droppedItemAddedToRoom(dropItem);
            }
            else{
                System.out.println("cancel");
            }

            gui.setLocationInfo(currentLocation);
        }
    }

    class PuzzleButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Puzzle puzzle = currentLocation.getTypePuzzle();
            boolean puzzleComplete = puzzle.runPuzzle();
            if (puzzleComplete) {

                ((JButton) e.getSource()).setVisible(false);
            }
        }
    }

    class MainMenuButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int reply = gui.popUpGameOption();
            System.out.println(reply);
            if (reply == 5) {
                audio.toggleMute();
            }
            else if (reply == 4){
                System.out.println("mission list");
                StringBuilder sb = new StringBuilder("Mission To Do: \n");
                sb.append("Green House operational: "  + GhPuzzle.isSolved + "\n");
                sb.append("Reactor Operational: " +  ReactorPuzzle.isSolved + "\n");
                sb.append("Solar Panels operation: " + SolarPuzzle.isSolved + "\n");
                gui.popUp(sb.toString());
            }
            else if (reply == 3){
                System.out.println("map");
                gui.popUpImage();
            }
            else if (reply == 2){
                System.out.println("game help");
                String help = display.displayGUI("text/help.txt");
                gui.popUp(help);
            }
            else if (reply == 1){
                gui.popUp("See you later space cowboy");
                System.exit(0);
            }
            else{
                System.out.println("Cancel");
            }
        }
    }
    boolean allPuzzlesCompleted() {
        return GhPuzzle.isSolved && SolarPuzzle.isSolved && ReactorPuzzle.isSolved;
    }
}