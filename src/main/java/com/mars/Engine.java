package com.mars;

import com.mars.display.Display;
import com.mars.gui.GameGui;
import com.mars.gui.MiddleMan;
import com.mars.objects.Location;
import com.mars.puzzle.GhPuzzle;
import com.mars.puzzle.HydroPuzzle;
import com.mars.puzzle.ReactorPuzzle;
import com.mars.puzzle.SolarPuzzle;
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
    GameGui gui = GameGui.getINSTANCE();
    MiddleMan middle = new MiddleMan(gui);
    private boolean isGhSolved = false;
    private boolean isHydroSolved = false;
    private boolean isReactorSolved = false;
    private boolean isSolarSolved = false;



    // method to actually run the application
    public void runApp() {
        gui.go();
        display.displayText("text/splash.txt");

        String storySplash = display.displayGUI("text/splash.txt"); // GUI splash
        gui.updateStory(storySplash); // GUI update story

        //display.displaySplash();                        // Display welcome screen to user
        boolean isRunning = false;                      // establish & setting boolean to default off for game execution
//        String answer = display.playGame();             // Ask if user wants to play a game

        String answer = display.playGameGUI(middle);

        if(answer.equals("y")){
            isRunning = true;                           // setting boolean on for game execution
        }
        else{
//            System.out.println("You chose to not play :(");     // message showing user their choice
            gui.updateStory("You chose to not play :("); //GUI
            System.exit(0);                              // exiting game load
        }
        Location currentLocation = locationMap.get("Docking Station");          // setting game start location on Map
        //display.displayText("text/game_info.txt");                      // display of game information
        //display.displayText("text/game_menu.txt");                      // display of game menu
        String gameInfo = display.displayGUI("text/game_info.txt"); // GUI splash
        gui.newStory(gameInfo); // GUI update story
        String gameMenu = display.displayGUI("text/game_menu.txt"); // GUI splash
        gui.updateStory(gameInfo); // GUI update story
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
//            System.out.println("-----------------------------------------");
//            System.out.println("You have 14 mission days to complete the tasks. " +
//                    "1 minute of elapsed real time is equal 1 hour of elapsed game time within the Martian Outpost.");
//
//            System.out.println("Start Time: " + currentTime);
//            System.out.println("Time Until Death: " + dieTime);
            TimeCalc.findDifference(dieTime);
            gui.updateStory("-----------------------------------------\n" +
                    "You have 14 mission days to complete the tasks. " +
                    "1 minute of elapsed real time is equal 1 hour of elapsed game time within the Martian Outpost." +
                    "Start Time: " + currentTime + "\n" +
                    "Time Until Death: "  + dieTime + "\n" +
                    "-----------------------------------------\n");



       
//            display.displayCurrentStatus(currentLocation, playerStats);                      // display of location
            display.displayCurrentStatusGUI(currentLocation,playerStats,gui);

//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Enter a command: \n>> ");                            // asking for input from user
//            String userInput = scanner.nextLine();                              // getting input from user
            String userInput = middle.listener();
            List<String> nextCommand = parser.getCommand(userInput);            // calling upon Parser to begin parse process
            currentLocation = locationMap.get(processor.processCommand(nextCommand, currentLocation, locationMap));    // setting location
            checkPuzzles(currentLocation);

            if(isHydroSolved && isGhSolved && isReactorSolved && isSolarSolved){
//                display.displayText("text/win.txt");
                display.displayGUI("text/win.txt");
                isRunning = false;
            }
        }


    } //end method runApp
    private void checkPuzzles(Location currentLocation){
        if (currentLocation.getTypePuzzle() instanceof GhPuzzle){
            if (currentLocation.isSolved()){
                isGhSolved = currentLocation.isSolved();
            }
        }
        else if(currentLocation.getTypePuzzle() instanceof HydroPuzzle){
            if (currentLocation.isSolved()){
                isHydroSolved = currentLocation.isSolved();
            }
        }
        else if(currentLocation.getTypePuzzle() instanceof ReactorPuzzle){
            if (currentLocation.isSolved()){
                isReactorSolved = currentLocation.isSolved();
            }
        }
        else if (currentLocation.getTypePuzzle() instanceof SolarPuzzle){
            if (currentLocation.isSolved()){
                isSolarSolved = currentLocation.isSolved();
            }
        }
    }
}//end class engine
