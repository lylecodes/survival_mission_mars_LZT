package com.mars.controller;

import com.mars.gui.GameGui;
import com.mars.gui.alt.GameFrame;
import com.mars.objects.Inventory;
import com.mars.objects.Location;
import com.mars.stats.Stats;
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            gui.createGameScreen();
            gui.setDirectionChoiceButtonListeners(new GameScreenHandler());
            gui.setItemButtonListeners(new ItemButtonHandler());
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
            String direction = choice.split(" ")[1];
            String nextRoomName = currentLocation.getDirections().get(direction);
            currentLocation = locationMap.get(nextRoomName);
            // use command parser?
            gui.setLocationInfo(currentLocation);

            //add User Stats
            gui.playerSetup(playerStats.getStats().get("Health"),90,inventory.getInventory().toString());
//            gui.playerSetup(playerStats.getStats().get("Health"));


        }
    }

    class ItemButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String itemName = ((JButton) e.getSource()).getText();
            System.out.println(itemName + " added to inventory");
        }
    }

    boolean allPuzzlesCompleted() {
        return true;
    }
}