package com.mars.display;

import com.mars.gui.GameGui;
import com.mars.gui.MiddleMan;
import com.mars.objects.Inventory;
import com.mars.objects.Location;
import com.mars.stats.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Display {
    Inventory inventory = Inventory.getInstance();
//Implemented via displayText() method
//    public void displaySplash(){
//        //TODO: ASCII Art later (sort of done...)
//        System.out.println("Welcome to Survival Mission: Mars!");
//    }
    //method to clear the screen/clear console of any text (does not work in IDE right now...)
    public void clearScreen(){
//        try {
//            String os = System.getProperty("os.name"); //determine operating system (windows vs linux)
//
//            if (os.contains("Windows")){
//                Runtime.getRuntime().exec("cls"); //clear windows console with "cls" command
//            }
//            else {
//                Runtime.getRuntime().exec("clear"); //clear linux console with "clear command"
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error clearing screen: " + e); //display any error clearing screen
//        }
    }
    public String playGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to play a game? Enter y or n: \n>> ");
        boolean isValid = false;
        String userInput = "";
        while (!isValid){
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            if(userInput.matches("y|n")) { //only matching on y or n right now TODO: yes/no
                isValid = true;
            }
            else{
                System.out.println("please enter 'y' or 'n': ");
            }
        }
        return userInput;
    }
    public String playGameGUI(MiddleMan middle){
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Would you like to play a game? Enter y or n: \n>> ");
        middle.updateGUI("Would you like to play a game? Enter y or n: \n");
        boolean isValid = false;
        String userInput = "";
        while (!isValid){
//            userInput = scanner.nextLine();
            userInput = middle.listener();
            userInput = userInput.toLowerCase();
            if(userInput.matches("y|n")) { //only matching on y or n right now TODO: yes/no
                isValid = true;
            }
            else{
//                System.out.println("please enter 'y' or 'n': ");
                middle.updateGUI("please enter 'y' or 'n': ");
            }
        }
        System.out.println(4);
        return userInput;
    }

    public void displayText(String filePath){

        InputStream textInput = getFileFromResourceAsStream(filePath);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(textInput,"UTF-8"))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //read in the txt file and display lines
//        try (BufferedReader reader =
//                     new BufferedReader(new FileReader(filePath))){
//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//            }
//            System.out.println(); //empty line for looks
//        }
//        catch (IOException e) {
//            System.out.println("Sorry, file not found");
//        }
    }
    public String displayGUI(String filePath) {

        InputStream textInput = getFileFromResourceAsStream(filePath);
        String image ="";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(textInput, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                image =  image + line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void readMap(String filePath, String location){
        InputStream textInput = getFileFromResourceAsStream(filePath);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(textInput,"UTF-8"))){
            String line;
            while ((line = reader.readLine()) != null){
                if (line.contains(location)){
                    System.out.println(line.replace(location + "[ ]", location + "[X]"));
                }
                else {
                    System.out.println(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void displayCurrentStatus(Location location, Stats playerStats){
            //to display player health related stats
            HashMap<String, Integer> stats = playerStats.getStats();
            System.out.println("-----------------------------------------");
            System.out.println("Current Player Stats: ");
            System.out.println("Bone Density: " + (stats.get("Bone Density")).toString() + "%");
            System.out.println("Health: " + (stats.get("Health")).toString() + "%");

            //to display location related info
            List<String> allItems = new ArrayList<>();

            System.out.println("-----------------------------------------");
            System.out.println("You are in " + location.getName());
            System.out.println("Description: " + location.getDescription());

            System.out.println("You see the following items in the room: " + location.getItemNames());

            for(Map.Entry<String, String> entry: location.getDirections().entrySet()){
                System.out.println("You see a door to the " + entry.getKey() + ": " + entry.getValue());
            }
        }
    public void displayCurrentStatusGUI(Location location, Stats playerStats, GameGui gui){
        //to display player health related stats
        HashMap<String, Integer> stats = playerStats.getStats();
//        System.out.println("-----------------------------------------");
//        System.out.println("Current Player Stats: ");
//        System.out.println("Bone Density: " + (stats.get("Bone Density")).toString() + "%");
//        System.out.println("Health: " + (stats.get("Health")).toString() + "%");
        gui.updateStory("-----------------------------------------\n" +
                "Current Player Stats: " +
                "Bone Density: " +  (stats.get("Bone Density")).toString() + "% \n" +
                "Health: " + (stats.get("Health")).toString() + "% \n");

        //to display location related info
        List<String> allItems = new ArrayList<>();

//        System.out.println("-----------------------------------------");
//        System.out.println("You are in " + location.getName());
//        System.out.println("Description: " + location.getDescription());
        gui.updateStory("-----------------------------------------\n" +
                "\"You are in " +  location.getName() + "\n" +
                "Description: " + location.getDescription());

//        System.out.println("You see the following items in the room: " + location.getItemNames());
        gui.updateStory("You see the following items in the room: " + location.getItemNames() + "\n");
        for(Map.Entry<String, String> entry: location.getDirections().entrySet()){
//            System.out.println("You see a door to the " + entry.getKey() + ": " + entry.getValue());
            gui.updateStory("You see a door to the " + entry.getKey() + ": " + entry.getValue()+ "\n");
        }
    }


    public void displayCurrentStatus(Location location){
        List<String> allItems = new ArrayList<>();
        System.out.println("-----------------------------------------");
        System.out.println("You are in " + location.getName());
        System.out.println("Description: " + location.getDescription());

//        for(Map.Entry<String, String> entry: location.getItems().entrySet()){
//            allItems.add(entry.getKey());
//        }
        System.out.println("You see the following items in the room: " + location.getItemNames());

    
        for(Map.Entry<String, String> entry: location.getDirections().entrySet()){
            System.out.println("You see a door to the " + entry.getKey());
        }
    }

    public void displayPlayerInventory(){
        System.out.println(String.join(", ", inventory.getInventory()));
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = Display.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

}//end class display
