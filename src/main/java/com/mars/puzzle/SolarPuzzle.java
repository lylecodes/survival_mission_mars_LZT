package com.mars.puzzle;

import com.mars.util.JSONHandler;
import static com.mars.puzzle.Dialogue.*;

import java.util.Random;

public class SolarPuzzle implements Puzzle{
    private String name = "SolarPuzzle";
    public static boolean isSolved = false;

    @Override // Puzzle interface
    public String showIntro(){
        return JSONHandler.getFileContentAsString("text/solarIntro.txt");
    }

    @Override
    public boolean runPuzzle(){
        popUpDialogueEnd(showIntro(), this.getName());
        int res = popUpDialogue("Would you like to try to align the panels?", this.getName());
        if (res == 0) {
            Random rand = new Random();
            int randInt = rand.nextInt(10);
            if (randInt >= 5) {
                popUpDialogueEnd("Nicely done! You've aligned the panels", this.getName());
                isSolved = true;
            } else {
                popUpDialogueEnd("I'm sorry, you've failed. Please keep trying!", this.getName());
            }
        } else if (res == 1) {
            popUpDialogueEnd("OK, then...", this.getName());
        } else {
            System.out.println("Cancelled");
        }
        return isSolved();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean isSolved() {
        return isSolved;
    }
}