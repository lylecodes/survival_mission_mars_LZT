package com.mars.controller;

import com.mars.gui.alt.GameFrame;
import com.mars.objects.Inventory;
import com.mars.objects.Item;
import com.mars.objects.Location;
import com.mars.stats.Stats;
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;

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

    // Puzzles
    private boolean isGhSolved = false;
    private boolean isHydroSolved = false;
    private boolean isReactorSolved = false;
    private boolean isSolarSolved = false;

    public GameController(GameFrame gui) {
        this.gui = gui;
        this.currentLocation = locationMap.get("Docking Station");
        gui.setTitleScreenHandler(new TitleScreenHandler());

//        DELETE ME
//        Item key = new Item("key", "Opens Stuff");
//        inventory.add(key);
    }

    // Title Screen stuff
    class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("hello1");
            gui.createIntroScreen();
            gui.setIntroScreenHandler(new IntroScreenHandler());
        }
    }

    // Intro Screen stuff
    class IntroScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            System.out.println("hello2");
            gui.createGameScreen(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString()
            );
            gui.setDirectionChoiceButtonListeners(new GameScreenHandler());
            gui.setItemButtonListeners(new ItemButtonHandler());
            gui.setInventoryListener(new InventoryButtonHandler());

            gui.setLocationInfo(currentLocation);

        }
    }

    // Game Screen stuff
    class GameScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("hello3");
            allPuzzlesCompleted();
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

            //add User Stats
            gui.playerSetup(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString()
            );
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
                gui.setLocationInfo(currentLocation);
                System.out.println("Inventory: " + inventory.getInventory());
            }
            else {
                System.out.println("Inventory full");
                gui.popUp("You inventory is full");
                gui.setLocationInfo(currentLocation);
            }

        }
    }
    class InventoryButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inventoryName = ((JButton) e.getSource()).getText();
            System.out.println(inventoryName + " trying to use item");
        }
    }

    boolean allPuzzlesCompleted() {
        return true;
    }
}