package com.mars.util;

import com.mars.objects.Location;

import java.util.List;
import java.util.Map;

public class CommandProcessor {

    // method to resolve action command inputs from user
    public String processCommand(List<String> command, Location currentLocation, Map<String, Location> locationMap) {
        String nextLocation = currentLocation.getName();                                                                // getting name of currentLocation and assign to nextLocation
        if(command.get(0).equals("go")) {                                                                               // checking if input command is 'go'
            if(currentLocation.getDirections().containsKey(command.get(1))) {                                           // checking if currentLocation has direction of movement provided by user input as an option
                nextLocation = currentLocation.getDirections().get(command.get(1));                                     // moving to nextLocation
            } else {
                System.out.println("C'mon, get right, you can't go that way!");                                         // otherwise informing user that pathway is not accessible
            }
        }
        else if(command.get(0).equals("quit")) {                                                                        // checking if user inputs option to 'quit'
            System.out.println("Fine then! Bye!!");                                                                     // sends quit message
            System.exit(0);                                                                                      // exits game
        }
        else {
            System.out.println("That is an invalid command. Please try again.");                                        // if user input is not 'go' or 'quit', informs user of invalid command input
        }
        return nextLocation;                                                                                            // returns new location if needed elsewhere
    }

}