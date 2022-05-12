package com.mars.controller;

import com.mars.gui.GameGui;
import com.mars.gui.alt.GameFrame;
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

    // Puzzles
    private boolean isGhSolved = false;
    private boolean isHydroSolved = false;
    private boolean isReactorSolved = false;
    private boolean isSolarSolved = false;

//    private final GameGui gui = GameGui.getINSTANCE();
    private final GameFrame gui;
//    private final
    private final Location currentLocation;          // setting game start location on Map

    public GameController(GameFrame gui) {
        System.out.println("hello");
        this.gui = gui;
        this.currentLocation = locationMap.get("Docking Station");
        // add initial listeners to view
        gui.addDirectionChoiceButtonListeners(new GameInputListener());
        // load current location
    }

    public boolean allPuzzlesCompleted() {
        return true;
    }

    class GameInputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get text input from field
            String choice = ((JButton) e.getSource()).getText();
            System.out.println(choice);
            // send to command parser, which returns next room str
            // get next location from location map
            // pass room info to view and trigger update
            allPuzzlesCompleted();
            gui.setLocationInfo(currentLocation);
        }
    }
}