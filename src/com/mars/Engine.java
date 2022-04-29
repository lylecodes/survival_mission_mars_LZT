package com.mars;

import com.mars.display.Display;
import com.mars.util.TextParser;
import java.util.List;
import java.util.Scanner;
import com.mars.util.TextParser;

public class Engine {
    private Display display = new Display();
    private TextParser parser = new TextParser();

    public void runApp() {
        display.displaySplash();                        //Display welcome screen to user

        boolean isRunning = false;
        String answer = display.playGame();             //Ask if user wants to play a game
        if(answer.equals("Y")){
            isRunning = true;
        }
        else{
            System.out.println("You chose to not play :(");
            System.exit(0);
        }

        display.displayGameInfo();                      //display basic game info
        //boolean choseQuit = false;
        while (isRunning) {
            display.displayText("data/game_info.txt");
            display.displayText("data/game_menu.txt");
          
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a command: ");
            String userInput = scanner.nextLine();
            List<String> nextCommand = parser.getCommand(userInput);

            System.out.println("Do you want to exit the game?");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equals("Y")) {
                System.out.println("Exiting game...");
                break;
            }
        }
    }//end method runApp
}//end class engine
