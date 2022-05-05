package com.mars.puzzle;

import com.mars.display.Display;
import com.mars.objects.Inventory;
import com.mars.util.TextParser;

import java.util.List;
import java.util.Scanner;


public class ReactorPuzzle implements Puzzle {
    boolean isSolved = false;
    Display display = new Display();
    TextParser parser = new TextParser();


    @Override
    public void showIntro() {
        display.displayText("text/reactorIntro.txt");
    }

    @Override
    public void runPuzzle() {
        reactorPuzzle();
    }

    @Override
    public boolean isSolved(){
        return isSolved;
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
}
