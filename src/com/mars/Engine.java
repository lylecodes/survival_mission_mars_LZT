package com.mars;

import com.mars.display.Display;
import com.mars.objects.Location;
import com.mars.util.CommandProcessor;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Engine {
    private Display display = new Display();
    private TextParser parser = new TextParser();
    private CommandProcessor processor = new CommandProcessor();
    private JSONHandler jsonhandler = new JSONHandler();
    private Map<String, Location> locationMap = jsonhandler.loadLocationMap();

    public void runApp() {
        display.displaySplash();                        //Display welcome screen to user

        boolean isRunning = false;
        String answer = display.playGame();             //Ask if user wants to play a game
        if(answer.equals("y")){
            isRunning = true;
        }
        else{
            System.out.println("You chose to not play :(");
            System.exit(0);
        }

        Location currentLocation = locationMap.get("Docking Station");
        display.displayText("data/game_info.txt");
        display.displayText("data/game_menu.txt");

        while (isRunning) {
            display.displayCurrentStatus(currentLocation);
          
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a command: ");
            String userInput = scanner.nextLine();
            List<String> nextCommand = parser.getCommand(userInput);
            currentLocation = locationMap.get(processor.processCommand(nextCommand, currentLocation, locationMap));

        }
    }//end method runApp
}//end class engine
