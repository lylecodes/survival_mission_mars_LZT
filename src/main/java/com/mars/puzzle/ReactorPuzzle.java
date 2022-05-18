package com.mars.puzzle;

import com.mars.objects.Inventory;
import com.mars.util.Audio;
import com.mars.util.JSONHandler;
import com.mars.util.TextParser;

import java.util.List;
import java.util.Scanner;
import static com.mars.puzzle.Dialogue.*;


public class ReactorPuzzle implements Puzzle {
    private String name = "ReactorPuzzle";
    public static boolean isSolved = false;
    TextParser parser = new TextParser();
    Audio audio = Audio.getInstance();

    @Override
    public String showIntro() {
        return JSONHandler.getFileContentAsString("text/reactorIntro.txt");
    }

    @Override
    public boolean runPuzzle() {
        popUpDialogueEnd(showIntro(), this.getName());
        int res;
        if (Inventory.getInstance().lookItem().contains("r_key")) {
            res = popUpDialogue("Do you want to use the r_key?", this.getName());
            if (res == 0) {
                popUpDialogueEnd("Reactor recognizes key and lever lights up", this.getName());
                res = popUpDialogue("Do you want to pull the lever?", this.getName());
                audio.play("turbine_start.wav");
                if (res == 0) {
                    popUpDialogueEnd("You pulled the lever", this.getName());
                    popUpDialogueEnd("The reactor whirrs as it spins up", this.getName());
                    popUpDialogueEnd("Full power restored to outpost", this.getName());
                    isSolved = true;
                } else if (res == 1) {
                    popUpDialogueEnd("See ya", this.getName());
                } else {
                    System.out.println("Cancelled");
                }
            } else if (res == 1) {
                popUpDialogueEnd("See ya", this.getName());
            } else {
                System.out.println("Cancelled");
            }
        } else {
            popUpDialogueEnd("You need the R_key to activate the reactor", this.getName());
        }
        return isSolved();
    }

    @Override
    public boolean isSolved(){
        return isSolved;
    }

    private void runChallenge(){
        System.out.println("Reactor recognizes key and lever lights up");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to pull the lever? 'y' or 'n':\n>> ");
        String userInput = scanner.nextLine().toLowerCase();
        if(userInput.equals("y")){
            System.out.println("You pulled the lever");
            System.out.println("Reactor whirrs as it spins up");
            System.out.println("Full power restored to outpost");
            isSolved = true;
        }
        else{
            System.out.println("ok maybe next time.");
        }

    }

    private void reactorPuzzle(){
        if(!isSolved){
            showIntro();
            if(Inventory.getInstance().lookItem().contains("r_key")){
                System.out.print("Do you want to use the r_key: Enter \"use r_key\"\n>> ");
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();                              // getting input from user
                List<String> nextCommand = parser.getCommand(userInput);
                if (nextCommand.get(0).equals("use")){
                    if (nextCommand.get(1).equals("r_key")){
                       runChallenge();
                    }
                    else {
                        System.out.println("Can't use that here");
                    }
                }
                else {
                    System.out.println("Can't use that here");
                }
            }
            else{
                System.out.println("You need the R_key to activate the reactor");
            }
        }
        else{
            System.out.println("What are you doing? You solved this game already!");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }


}
