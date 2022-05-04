package com.mars.util;

import com.mars.display.Display;
import com.mars.objects.Inventory;
import com.mars.objects.Item;
import com.mars.objects.Location;

import java.util.List;
import java.util.Map;

public class CommandProcessor {
    private Display display = new Display();

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
        else if(command.get(0).equals("look")) {                                                                        // 'look' functionality enabled to allow user to examine items and surroundings
            if(currentLocation.getItemNames().contains(command.get(1))) {                                                       // checking if second parsed word is valid inside currentLocation
                System.out.println("Upon examination you find " + currentLocation.getItemDescription(command.get(1)));          // output to user showing description of item, if valid in location
            }
            else if(Inventory.getInstance().lookItem().contains(command.get(1))) {                                                  // if not in currentLocation, check if in inventory
                System.out.println("Upon examination you find " + Inventory.getInstance().getItemDescription(command.get(1)));      // if item present in inventory, output to user description of item
            }
            else if(command.get(1).equals("room")) {
                System.out.println("Looking around this room, you see: " + currentLocation.getDescription());
            }
            else if(command.get(1).equals("inventory")) {
                if(Inventory.getInstance().getInventory().size() > 0) {
                    display.displayPlayerInventory();
                }
                else {
                    System.out.println("You currently have nothing in your inventory.");
                }
            }
            else if((command.get(1).equals("oxygen")) || (command.get(1).equals("O2"))) {
                if(currentLocation.getOxygen().equals(true)) {
                    System.out.println("The O2 Sensor indicates the oxygen levels are: SAFE");
                }
                else {
                    System.out.println("The O2 Sensor indicates the oxygen levels are: DANGEROUS");
                }
            }
            else {
                System.out.println("There is no item to examine.");                                                     // if nothing to examine, output to user informing as such
            }
        }
        else if(command.get(0).equals("get")) {                                                                         // 'get' functionality enabled to allow user to acquire items, add to inventory
            if(currentLocation.getItemNames().contains(command.get(1))) {                                               // checking if second parsed word is valid inside currentLocation
                Item carry = currentLocation.removeItem(command.get(1));                                                // if so, then assigning it a variable named 'carry'
                Inventory.getInstance().add(carry);                                                                     // adding to inventory
                System.out.println("You've retrieved the " + carry.getName() + " and added it to your inventory.");     // output to user informing item added to inventory
                display.displayPlayerInventory();                                                                       // output to user showing full inventory
            }
            else {
                System.out.println("There is nothing here to pick up. Are you seeing things?  Maybe check your sugar levels...");       // output to user reminding them there is nothing to acquire from this room
            }
        }
        else if(command.get(0).equals("drop")) {                                                                        // 'drop' functionality enabled to allow user to drop items from inventory, add to currentLocation
            if(Inventory.getInstance().lookItem().contains(command.get(1))) {                                           // checking to see if item in inventory
                Item dropping = Inventory.getInstance().drop(command.get(1));                                           // if so, assigning it a variable named 'dropping'
                currentLocation.addItem(dropping);                                                                      // adding 'dropping' item to currentLocation
                System.out.println("You have dropped the " + dropping.getName() + ", it is no longer in your " +        // output to user to inform them of the change
                        "inventory. It has been placed in this location.");
                display.displayPlayerInventory();
            }
            else {
                System.out.println("There is no item with that name in your inventory. Please try again.");             // output to user to inform them of invalid attempt to drop item
            }
        }
        else if(command.get(0).equals("use")) {                                 // TODO: what about consumable items? (mealkit) ...or Items that actuate something else? (key -> reactor)
            if(Inventory.getInstance().lookItem().contains(command.get(1))) {
                System.out.println("Item is here");
            }
            else {
                System.out.println("Use what?");
            }
        }
        else {
            System.out.println("That is an invalid command. Please try again.");                                        // if user input is not 'go' or 'quit', informs user of invalid command input
        }
        return nextLocation;                                                                                            // returns new location if needed elsewhere
    }

}