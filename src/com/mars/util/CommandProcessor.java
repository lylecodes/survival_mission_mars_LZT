package com.mars.util;

import com.mars.objects.Location;

import java.util.List;
import java.util.Map;

public class CommandProcessor {


    public String processCommand(List<String> command, Location currentLocation, Map<String, Location> locationMap) {
        String nextLocation = currentLocation.getName();
        if(command.get(0).equals("go")) {
            if(currentLocation.getDirections().containsKey(command.get(1))) {
                nextLocation = currentLocation.getDirections().get(command.get(1));
            } else {
                System.out.println("C'mon, get right, you can't go that way!");
            }
        }
        else if(command.get(0).equals("quit")) {
            System.out.println("Fine then! Bye!!");
            System.exit(0);
        }
        else {
            System.out.println("That is an invalid command. Please try again.");
        }
        return nextLocation;
    }

}