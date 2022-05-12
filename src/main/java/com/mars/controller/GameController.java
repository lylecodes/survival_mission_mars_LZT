package com.mars.controller;

import com.mars.gui.GameGui;
import com.mars.objects.Location;
import com.mars.stats.Stats;
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GameController {
    private final TextParser parser = new TextParser();
    private final CommandProcessor processor = new CommandProcessor();
    private final JSONHandler jsonhandler = new JSONHandler();
    private final Map<String, Location> locationMap = jsonhandler.loadLocationMap();
    private final Stats playerStats = new Stats();
    private final GameGui gui = GameGui.getINSTANCE();
    private boolean isGhSolved = false;
    private boolean isHydroSolved = false;
    private boolean isReactorSolved = false;
    private boolean isSolarSolved = false;
    Location currentLocation;          // setting game start location on Map

    public GameController() {
        this.currentLocation = locationMap.get("Docking Station");
        // add initial listeners to view
        // load current location
    }

    class GameInputListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get text input from field
            // send to command parser, which returns next room str
            // get next location from location map
            // pass room info to view and trigger update
        }
    }
}