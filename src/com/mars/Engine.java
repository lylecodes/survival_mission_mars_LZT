package com.mars;

import com.mars.display.Display;
import com.mars.objects.Inventory;
import com.mars.objects.Location;
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

// main engine for execution of program
public class Engine  {
    // instantiation of necessary variables required
    private Display display = new Display();
    private TextParser parser = new TextParser();
    private CommandProcessor processor = new CommandProcessor();
    private JSONHandler jsonhandler = new JSONHandler();
    private Map<String, Location> locationMap = jsonhandler.loadLocationMap();


    // method to actually run the application
    public void runApp() {
        display.displayText("text/splash.txt");
        //display.displaySplash();                        // Display welcome screen to user
        boolean isRunning = false;                      // establish & setting boolean to default off for game execution
        String answer = display.playGame();             // Ask if user wants to play a game
        if(answer.equals("y")){
            isRunning = true;                           // setting boolean on for game execution
        }
        else{
            System.out.println("You chose to not play :(");     // message showing user their choice
            System.exit(0);                              // exiting game load
        }

        Location currentLocation = locationMap.get("Docking Station");          // setting game start location on Map
        display.displayText("text/game_info.txt");                      // display of game information
        display.displayText("text/game_menu.txt");                      // display of game menu

        // functions while game is running
        while (isRunning) {
            display.displayCurrentStatus(currentLocation);                      // display of location

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a command: \n>> ");                            // asking for input from user
            String userInput = scanner.nextLine();                              // getting input from user
            List<String> nextCommand = parser.getCommand(userInput);            // calling upon Parser to begin parse process
            currentLocation = locationMap.get(processor.processCommand(nextCommand, currentLocation, locationMap));    // setting location
        }
    }//end method runApp
}//end class engine
