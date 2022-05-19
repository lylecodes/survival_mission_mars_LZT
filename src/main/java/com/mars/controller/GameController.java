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
import com.mars.util.JSONHandler;
import com.mars.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GameController {
    private final Audio audio = Audio.getInstance();
    private final JSONHandler jsonhandler = new JSONHandler();
    private final Map<String, Location> locationMap = jsonhandler.loadLocationMap();
    private final Stats playerStats = new Stats();
    private final GameFrame gui;
    private Location currentLocation;
    private final Inventory inventory = Inventory.getInstance();
    private final Display display = new Display();
    private static String dieTime;
    private Random rand = new Random();
    private List<String> randomEventNames = new ArrayList<>();
    private int minutesToCompleteGame = 10;
    private static boolean isGodMode = false;
    private static int boneLoss = 2;

    public static void setDieTime(String dieTime) {
        GameController.dieTime = dieTime;
    }

    public static void setGodMode(boolean godMode) {
        isGodMode = godMode;
    }

    public static void setBoneLoss(int boneLoss) {
        GameController.boneLoss = boneLoss;
    }

    public static void setHealthLoss(int healthLoss) {
        GameController.healthLoss = healthLoss;
    }

    private static int healthLoss = 5;
//    TIME

    public GameController(GameFrame gui) {
        this.gui = gui;
        this.currentLocation = locationMap.get("Docking Station");
        gui.setTitleScreenHandler(new TitleScreenHandler());

//        TIME set up end time
        dieTime = TimerSetUp.timeRun(minutesToCompleteGame);
    }

    // Title Screen stuff
    private class TitleScreenHandler implements ActionListener {
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
    private class GameScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            Game Events will go here
            IsGameEventActive.playerAtMiddleBuilding(currentLocation, audio);
            IsGameEventActive.playerActivatesGodMode(playerStats, isGodMode);
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
            playerStats.updateCurrentBoneLoss(boneLoss);
            playerStats.updateCurrentHealthLoss(healthLoss);
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

    private void checkForRandomEventAndEditLocation() {
        int randomNum = rand.nextInt(20);
        if (randomNum != 1) {
            if (randomEventNames.contains("steve")) return;
            randomEventNames.add("steve");
            Item item = new Item("Steve", "seems like a cool guy");
            currentLocation.addItem(item);
        }

        if (randomNum != 5) {
            if (randomEventNames.contains("middle")) return;
            randomEventNames.add("middle");
            Item alien = new Item("lil alien", "\"I work for Amazon\"");
            Item banana = new Item("banana", "it is a glowing banana healthItem");
            List<Item> roomItems = new ArrayList<>(Arrays.asList(alien ,banana));
            Map<String,String> directions = Map.of("north", "Hydro");
            locationMap.put("Middle Building",
                    new Location("Middle Building",
                            directions,
                            "I don't remember this place being here...",
                            roomItems, false, "", false));
            locationMap.get("Hydro").getDirections().put("south", "Middle Building");
        }

    }

    private class ItemButtonHandler implements ActionListener {
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

    private class InventoryButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inventoryName = ((JButton) e.getSource()).getText();
            String inventoryDescription = inventory.getItemDescription(inventoryName);

            int answer = gui.popUpInventory(inventoryName, inventoryDescription);
            if (answer == 2){
                gui.setLocationInfo(currentLocation);
                System.out.println("using item " + inventoryName );
                Item itemToUse = inventory.getItem(inventoryName);
                int value = inventory.use(itemToUse);
                if (value == 999) {
                    System.out.println();
                }
                else if (value == 998){
                    System.out.println("Steve activated");
                    gui.popUp("You feel the motivation and hype that was felt during the Windows95 release. \n" +
                            "In your ears you hear a slow chant 'Developers, Developers, DEVELOPERS!, DEVELOPERS!!'");
                    int response = gui.popUpGodMode();
                    if (response == 0){
                        System.out.println("GODMODE");
                        gui.popUp("GODMODE!!!!");
                        isGodMode = true;
                        audio.play("developers_song.wav");

                    }
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

    private class PuzzleButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.toString().contains("gym")){
                IsGameEventActive.playerAtGym(gui,
                        playerStats, currentLocation, locationMap,
                        inventory, dieTime, minutesToCompleteGame);
            }
            else{
                Puzzle puzzle = currentLocation.getTypePuzzle();
                boolean puzzleComplete = puzzle.runPuzzle();
                if (puzzleComplete) {

                    ((JButton) e.getSource()).setVisible(false);
            }

            }
        }
    }

    private class MainMenuButtonHandler implements ActionListener {
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
}