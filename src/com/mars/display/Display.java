package com.mars.display;

import com.mars.objects.Location;
import com.mars.objects.Player;
import com.mars.objects.Stats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Display {

//Implemented via displayText() method
//    public void displaySplash(){
//        //TODO: ASCII Art later (sort of done...)
//        System.out.println("Welcome to Survival Mission: Mars!");
//    }
    //method to clear the screen/clear console of any text (does not work in IDE right now...)
    public void clearScreen(){
        try {
            String os = System.getProperty("os.name"); //determine operating system (windows vs linux)

            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls"); //clear windows console with "cls" command
            }
            else {
                Runtime.getRuntime().exec("clear"); //clear linux console with "clear command"
            }
        }
        catch (Exception e) {
            System.out.println("Error clearing screen: " + e); //display any error clearing screen
        }
    }
    public String playGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play a game? Enter y or n: ");
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
        System.out.println("----------------------");
        System.out.println("You are in " + location.getName());
        System.out.println("Description: " + location.getDescription());
        System.out.println("You see a " + Arrays.toString(location.getItems()));
    }
}//end class display
