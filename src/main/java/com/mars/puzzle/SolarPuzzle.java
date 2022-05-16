package com.mars.puzzle;

import com.mars.display.Display;
import com.mars.util.JSONHandler;

import java.util.Random;
import java.util.Scanner;

public class SolarPuzzle implements Puzzle{
    String name = "SolarPuzzle";
    boolean isSolved = false;
    Display display = new Display();



    @Override // Puzzle interface
    public String showIntro(){
        return JSONHandler.getFileContentAsString("text/solarIntro.txt");
    }

    private void solarPuzzle(){

        //solar specific puzzle here
        if(!isSolved){
            showIntro();
            while(true) {
                Scanner alignAttempt = new Scanner(System.in);
                System.out.print("Would you like to try to align the panels? 'y' or 'n':\n>>");
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
    }

    @Override
    public void runPuzzle(){
        //solarChallenge specific logic

        solarPuzzle();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean isSolved() {
        return isSolved;
    }
}