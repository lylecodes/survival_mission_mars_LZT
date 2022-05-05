package com.mars.puzzle;

import com.mars.display.Display;

import java.util.Random;
import java.util.Scanner;

public class SolarPuzzle implements Puzzle{
    boolean isSolved = false;
    Display display = new Display();



    private void solarIntro(){
        display.displayText("data/solarIntro.txt");
    }

    private boolean solarPuzzle(){

        //solar specific puzzle here
        if(!isSolved){
            solarIntro();
            while(true) {
                Scanner alignAttempt = new Scanner(System.in);
                System.out.println("Would you like to try to align the panels?");
                String panelAnswer = alignAttempt.nextLine();
                if(panelAnswer.equals("y")) {
                    Random rand = new Random();
                    int randInt = rand.nextInt(10);
                    if(randInt >= 5) {
                        System.out.println("Nicely done! You've aligned the panels");
                        isSolved = true;
                        break;
                    }
                    else {
                        System.out.println("I'm sorry, you've failed. Please keep trying!");
                    }
                }
                else {
                    System.out.println("OK, then...");
                    break;
                }
            }
        }
        else{
            System.out.println("What are you doing? You solved this game already!");
        }
        return isSolved;
    }

    //method that must be implemented(per Challenge interface)
    @Override
    public void showInstructions(){
        System.out.println("Here are some basic instructions for this solar challenge...");
    }
    @Override
    public void runPuzzle(){
        //solarChallenge specific logic

        solarPuzzle();
    }
}