package com.mars.puzzle;

import com.mars.display.Display;
import com.mars.util.TextParser;

import java.util.Scanner;

public class HydroPuzzle implements Puzzle {
    boolean isSolved = false;           // full challenge logic / completion check
    boolean checkFilter = false;        // check filter logic / completion check
    boolean checkMonitors = false;      // check monitors logic / completion check
    boolean isTankProper = false;       // water tank logic / completion check
    boolean isPowered = false;          // tank power logic / completion check
    boolean isElectrolysis = false;     // electrolysis system logic / completion check
    Display display = new Display();            // calling a new display
    TextParser parser = new TextParser();       // calling on TextParser

    @Override // Puzzle interface
    // launch challenge, display text
    public void showIntro(){
        display.displayText("text/hydroIntro.txt");
    }

    @Override
    //running the challenge
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
    }

    // series/sequence of events within the challenge
    private void runChallenge() {
        particleFilterCheck();
        sensorsAmmoniaAndAcetone();
        checkTankAndWaterLevel();
        buttonPower();
        buttonElectro();
        isSolved = true;
    }
    // filter check
    private void particleFilterCheck() {
        if(!checkFilter) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Would you like to check the particle filter? \nEnter 'y' or 'n':\n>> ");
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

    // NH3 & C3H6O sensor check
    private void sensorsAmmoniaAndAcetone() {
        if(!checkMonitors) {            // <-- checks to ensure particleFilterCheck is complete first
            if(checkFilter) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("The next step listed on the placard was to check the NH3 [Ammonia] and " +
                        "C3H6O [Acetone] sensors to ensure the tank is safe. Would you like to check those? \n" +
                        "Enter 'y' or 'n': \n>> ");
                String userInput = scanner.nextLine().toLowerCase();
                if(userInput.equals("y")) {
                    System.out.println("They both look good. The next step is to check the oxygen level.");
                    checkMonitors = true;
                    // TODO: Allow for 'look oxygen' command to be called.
                }
            }
            else {
                System.out.println("You may have missed step. Look at the challenge again and pay attention to the placard.");
            }
        }
        else {
            System.out.println("You've already verified the ammonia and acetone levels are both safe!");
        }
    }

    // tank and water level check
    private void checkTankAndWaterLevel() {
        if(!isTankProper) {
            if(checkMonitors) {         // <-- checks to ensure checkMonitors is complete first
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
            else {
                System.out.println("You may have missed step. Look at the challenge again and pay attention to the placard.");
            }
        }
        else {
            System.out.println("You've already inspected the tank for damage or wear and found nothing.  It's also " +
                    "full of water and not leaking!");
        }
    }

    // power sequence check
    private void buttonPower() {
        if (!isPowered) {
            if (isTankProper) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("You see the Power button. Push it? Enter 'y' or 'n':\n>> ");
                String userInput = scanner.nextLine().toLowerCase();
                if (userInput.equals("y")) {
                    System.out.println("You hear the distinct buzz of 60Hz power coming online in the hydro room." +
                            " Now we are getting somewhere. \nThere may be hope for you yet.");
                    isPowered = true;
                } else {
                    System.out.println("Alright, what are we doing here?  Remember, tick-tock...");
                }
            } else {
                System.out.println("You may have missed step. Look at the challenge again and pay attention to the placard.");
            }
        } else {
            System.out.println("The power to the water tank pressurization system has already been engaged! Let's move on.");
        }
    }

    // electrolysis system check
    private void buttonElectro() {
        if(!isElectrolysis) {
            if(isPowered) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("You see another button that says 'Engage Electrolysis System'. Push it? Enter 'y' or 'n':\n>> ");
                String userInput = scanner.nextLine().toLowerCase();
                if (userInput.equals("y")) {
                    System.out.println("That distinct buzz just got a bit louder.  You also hear a hissing sound. " +
                            "Congratulations, you have restored oxygen to the outpost.");
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