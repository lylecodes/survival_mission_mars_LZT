package com.mars;

import com.mars.display.Display;
import com.mars.objects.Location;
import com.mars.stats.Stats;
import com.mars.util.*;

import java.util.*;


// main engine for execution of program
public class Engine  {
    // instantiation of necessary variables required
    private Display display = new Display();
    private TextParser parser = new TextParser();
    private CommandProcessor processor = new CommandProcessor();
    private JSONHandler jsonhandler = new JSONHandler();
    private Map<String, Location> locationMap = jsonhandler.loadLocationMap();
    private Stats playerStats = new Stats();


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

        // this is the game clock / countdown timer logic
        Timer timer = new Timer();                          // create a timer
        TimerTask task = new Task();                        // create a task -- task.java executes shutdown
        long delay = 336 * 60000L;       // change when done testing    --  sets the length of delay
        timer.schedule(task, delay);                        // schedules the timer to execute task after delay
        long start = System.currentTimeMillis()/1000;       // marks start time of game, reduces from millisecs to secs
        long markDelay = delay/1000;                        // creates variable for delay, reduces from millisecs to secs
        long endTime = start + markDelay;                   // marks endTime of game
        String currentTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (start*1000));   // formats currentTime to SDF
        String dieTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (endTime*1000));     // formats endTime to SDF


        // functions while game is running
        while (isRunning) {


            // game clock output
            System.out.println("-----------------------------------------");
            System.out.println("You have 14 mission days to complete the tasks. " +
                    "1 minute of elapsed real time is equal 1 hour of elapsed game time within the Martian Outpost.");

            System.out.println("Start Time: " + currentTime);
            System.out.println("Time Until Death: " + dieTime);
            TimeCalc.findDifference(dieTime);


//            display.displayCurrentStatus(currentLocation);                      // display of location

            display.displayCurrentStatus(currentLocation, playerStats);                      // display of location


            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a command: \n>> ");                            // asking for input from user
            String userInput = scanner.nextLine();                              // getting input from user
            List<String> nextCommand = parser.getCommand(userInput);            // calling upon Parser to begin parse process
            currentLocation = locationMap.get(processor.processCommand(nextCommand, currentLocation, locationMap));    // setting location

        }


    } //end method runApp
}//end class engine