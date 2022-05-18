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
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class GameController {
    private final TextParser parser = new TextParser();
    private final CommandProcessor processor = new CommandProcessor();
    private final JSONHandler jsonhandler = new JSONHandler();
    private final Map<String, Location> locationMap = jsonhandler.loadLocationMap();
    private final Stats playerStats = new Stats();
    private final GameFrame gui;
    private Location currentLocation;
    private Inventory inventory = Inventory.getInstance();
    private Display display = new Display();

    // Puzzles
    private static boolean isGhSolved = false;
    private static boolean isReactorSolved = false;
    private static boolean isSolarSolved = false;

    public GameController(GameFrame gui) {
        this.gui = gui;
        this.currentLocation = locationMap.get("Docking Station");
        gui.setTitleScreenHandler(new TitleScreenHandler());
    }

    // Title Screen stuff
    class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("hello1");

//            gui.setIntroScreenHandler(new IntroScreenHandler());
            gui.createGameScreen(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString()
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

    // Intro Screen stuff
//    class IntroScreenHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event){
//            System.out.println("hello2");
//            gui.createGameScreen(
//                    playerStats.getStats().get("Health"),
//                    playerStats.getStats().get("Bone Density"),
//                    inventory.getInventory().toString()
//            );
//            gui.setDirectionChoiceButtonListeners(new GameScreenHandler());
//            gui.setChallengeButtonListeners(new PuzzleButtonHandler());
//            gui.setItemButtonListeners(new ItemButtonHandler());
//            gui.setInventoryListener(new InventoryButtonHandler());
//            gui.setMainMenuButtonListeners(new MainMenuButtonHandler());
//
//            gui.setLocationInfo(currentLocation);
//
//        }
//    }

    // Game Screen stuff
    class GameScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (playerStats.getStats().get("Health") <= 0 || playerStats.getStats().get("Bone Density") <= 0){
               int response = gui.popUpPlayAgain();
               if(response == 0){
                   gui.setLocationInfo(locationMap.get("Docking Station"));
                   currentLocation = locationMap.get("Docking Station");
                   playerStats.updateCurrentHealthGain(120);
                   playerStats.updateCurrentBoneGain(120);
               }
               else{
                   System.exit(0);
               }
            }
            if (currentLocation.equals(locationMap.get("Gym"))){
                playerStats.updateCurrentBoneGain(120);
                gui.playerSetup(
                        playerStats.getStats().get("Health"),
                        playerStats.getStats().get("Bone Density"),
                        inventory.getInventory().toString()
                );
                gui.popUp("You just hit the gym, which restored your bone density");
            }
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
            playerStats.updateCurrentBoneLoss(5);
            playerStats.updateCurrentHealthLoss(10);
            //add User Stats
            gui.playerSetup(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString()
            );
            if (allPuzzlesCompleted()) {
                gui.popUp("You completed all of the puzzles! Amazing!");
                System.exit(0);
            }
//            System.out.println(inventory.getInventory().size());
//            gui.showInventoryItems((ArrayList<String>) inventory.getInventory());
        }
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
                if (value > 0){
                    System.out.println(value);
                    playerStats.updateCurrentHealthGain(value);
                    gui.playerSetup(
                            playerStats.getStats().get("Health"),
                            playerStats.getStats().get("Bone Density"),
                            inventory.getInventory().toString()
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
            if (reply == 3){
                System.out.println("mission list");
                StringBuilder sb = new StringBuilder("Mission To Do: \n");
                sb.append("Green House operational: "  + GhPuzzle.isSolved + "\n");
                sb.append("Reactor Operational: " +  ReactorPuzzle.isSolved + "\n");
                sb.append("Solar Panels operation: " + SolarPuzzle.isSolved + "\n");
                gui.popUp(sb.toString());
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