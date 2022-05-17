package com.mars.puzzle;

import com.mars.util.JSONHandler;
import static com.mars.puzzle.Dialogue.*;

import java.util.*;

public class GhPuzzle implements Puzzle{
    //fields
    private String name = "GhPuzzle";
    private boolean isSolved = false;

    @Override
    public String showIntro() {
        return JSONHandler.getFileContentAsString("text/greenHouseIntro.txt"); //display the GH intro
    }

    @Override
    public boolean runPuzzle() {
        popUpDialogueEnd(showIntro(), this.getName());
        int res = popUpDialogue("You notice a valve in the corner of the room connected to " +  //challenge hard coded/very guided at this point
                "the water main. Do you turn it on?", this.getName());

        if (res == 0) {
            res = popUpDialogue("You see the water mister engage and moisten the soil." +
                    "Would you like to plant some seeds?", this.getName());
            if (res == 0) {
                isSolved = true;
                popUpDialogueEnd("Congratulations! You are able to grow food on Mars!", this.getName());
            } else if (res == 1) {
                popUpDialogueEnd("Oh well, your loss...have fun eating your MREs...", this.getName());
            } else {
                System.out.println("Cancelled");
            }
        } else if (res == 1){
            popUpDialogueEnd("Hmm....interesting choice.", this.getName());
        } else {
            System.out.println("Cancelled");
        }
        return isSolved();
    }

    @Override
    public boolean isSolved() { // is the challenge solved?
        return isSolved;
    }

    @Override
    public String getName() {
        return this.name;
    }

    // Unused code
    private void greenHousePuzzle(){
        //green house specific challenge to grow food
        // Turn valve
        // Water mister engages and moistens the soil
        // Plant seeds
        if(!isSolved){
            showIntro();
            System.out.print("You notice a valve in the corner of the room connected to " +  //challenge hard coded/very guided at this point
                    "the water main. Do you turn it on? Enter 'y' or 'n'\n>> ");            //if user responds y, they progress, if n, exits challenge
                                                                                            //TODO: input validation, add more options/variety
            Scanner scanner = new Scanner(System.in);
            String turnValve = scanner.nextLine();
            turnValve.toLowerCase();
            if(turnValve.equals("y")){
                System.out.println("You see the water mister engage and moisten the soil." +
                        "Would you like to plant some seeds? Enter 'y' or 'n'\n>> ");
                String plantSeeds = scanner.nextLine();
                plantSeeds.toLowerCase();
                if(plantSeeds.equals("y")){
                    System.out.println("Congratulations! You are able to grow food on Mars!");
                    isSolved = true;
                }
                else {
                    System.out.println("Oh well, your loss...have fun eating your MREs...");
                }
            }
            else{
                System.out.println("Hmm....interesting choice.");
            }

        }
        else{
            System.out.println("It looks like you've solved this challenge already!");
        }

    }
}