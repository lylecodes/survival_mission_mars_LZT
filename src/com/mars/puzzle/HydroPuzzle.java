package com.mars.puzzle;

import com.mars.display.Display;
import com.mars.util.TextParser;

import java.util.Scanner;

public class HydroPuzzle implements Puzzle {
    boolean isSolved = false;
    boolean checkFilter = false;
    boolean checkMonitors = false;
    boolean isTankProper = false;
    boolean isPowered = false;
    boolean isElectrolysis = false;
    Display display = new Display();
    TextParser parser = new TextParser();

    @Override // Puzzle interface
    public void showIntro(){
        display.displayText("text/hydroIntro.txt");
    }

    @Override
    public void runPuzzle(){
        hydroPuzzle();
    }

    @Override
    public boolean isSolved() {
        return isSolved;
    }

    private void hydroPuzzle() {
        if(!isSolved) {
            showIntro();
            runChallenge();
        }
        else{
            System.out.println("What are you doing? You solved this game already!");
        }

        // hydro specific puzzle here
    }

    private void runChallenge() {
        particleFilterCheck();
        sensorsAmmoniaAndAcetone();
        checkTankAndWaterLevel();
        buttonsPAndE();
    }

    private void particleFilterCheck() {
        if(!checkFilter) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Would you like to check the particle filter? Enter 'y' or 'n':\n>> ");
            String userInput = scanner.nextLine().toLowerCase();
            if(userInput.equals("y")){
                System.out.println("Particle Filter seems to be in good shape.");
                checkFilter = true;
            }
            else {
                System.out.println("Ok, but remember, the clock is ticking.");
            }
        }
        else {
            System.out.println("You've already verified the filter is good to go!");
        }
    }

    private void sensorsAmmoniaAndAcetone() {
        if(!checkMonitors) {
            if(checkFilter) {
                System.out.println("The next step listed on the placard was to check the NH3 [Ammonia] and " +
                        "C3H6O [Acetone] sensors to ensure the tank is safe.");
                System.out.println("They both look good. Now check the O2 sensor in the room by entering the " +
                        "command 'look oxygen'.");

                // TODO: Allow for 'look oxygen' command to be called.

                checkMonitors = true;
            }
            else {
                System.out.println("You may have missed step. Look at the challenge again and pay attention to the placard.");
            }
        }
        else {
            System.out.println("You've already verified the ammonia and acetone levels are both safe!");
        }
    }

    private void checkTankAndWaterLevel() {
        if(!isTankProper) {
            if(checkMonitors) {
                System.out.println("You visually inspect water tank and levels... ");
                System.out.println("They appear to be full.");
                System.out.println(" ");
                System.out.println("Outwardly, you wonder -- 'Hmm...It's like it was never activated.'" +
                        "\nWhy would they go through the trouble of building this outpost and never use it?'");
                System.out.println(" ");
                isTankProper = true;
                System.out.println("The placard said the next step was to power on, then engage electrolysis.");
                System.out.println("You scan the hydro console for buttons.");
            }
        }
        else {
            System.out.println("You've already inspected the tank for damage or wear and found nothing.  It's also " +
                    "full of water and not leaking!");
        }
    }
    private void buttonsPAndE() {
        if(isTankProper) {
            if(!isPowered) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("You see the Power button. Push it? Enter 'y' or 'n':\n>> ");
                String userInput = scanner.nextLine().toLowerCase();
                if(userInput.equals("y")){
                    System.out.println("You hear the distinct buzz of 60Hz power coming online in the hydro room." +
                            " Now we are getting somewhere. \nThere may be hope for you yet.");
                    isPowered = true;
                }
                else {
                    System.out.println("Alright, what are we doing here?  Remember, tick-tock...");
                }
            }
            if(!isElectrolysis) {
                if(isPowered) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("You see another button that says 'Engage Electrolysis System'. Push it? Enter 'y' or 'n':\n>> ");
                    String userInput = scanner.nextLine().toLowerCase();
                    if (userInput.equals("y")) {
                        System.out.println("That distinct buzz just got a bit louder.  You also hear a hissing sound.");
                        isElectrolysis = true;

                        // TODO:  switch oxygen to true in all locations, except solar farm

                    } else {
                        System.out.println("Alright, what are we doing here?  Remember, tick-tock...");
                    }
                }
                else {
                    System.out.println("You may have missed step. Look at the challenge again and pay attention to the placard.");
                }
            }
        }
    }
}