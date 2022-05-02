package com.mars.display;

import com.mars.objects.Location;
import com.mars.objects.Player;
import com.mars.objects.Stats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Display {

    public void displaySplash(){
        //TODO: ASCII Art later
        System.out.println("Welcome to Survival Mission: Mars!");
    }
    public String playGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play a game? Enter y or n: "); //TODO input validation
        String userInput = scanner.nextLine();
        return userInput.toLowerCase();
    }
    public void displayText(String filePath) {
        //read in the txt file and display lines
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            System.out.println(); //empty line for looks
        }
        catch (IOException e) {
            System.out.println("Sorry, file not found");
        }
    }

    public void displayCurrentStatus(Location location, Stats stats, Player player){
        System.out.println("Display player stats here"); //TODO
    }

    // Delete for test only
    public void displayCurrentStatus(Location location){
        List<String> allItems = new ArrayList<>();

        System.out.println("----------------------");
        System.out.println("You are in " + location.getName());
        System.out.println("Description: " + location.getDescription());
//        System.out.println("Items: " + location.getItems());
        for(Map.Entry<String, String> entry: location.getItems().entrySet()){
            allItems.add(entry.getKey());
        }
        System.out.println("You see the following items in the room: " + allItems);
    }
}//end class display
