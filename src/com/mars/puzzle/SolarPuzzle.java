package com.mars.puzzle;

import com.mars.display.Display;

public class SolarPuzzle implements Puzzle{
    boolean isSolved = false;
    Display display = new Display();



    private void solarIntro(){
        display.displayText("data/solarIntro.txt");
    }

    private boolean solarPuzzle(){

        //solar specific puzzle here
        if(!isSolved){

            System.out.println("Nicely done! You've aligned the panels");
            isSolved = true;
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
        solarIntro();
        solarPuzzle();
    }
}