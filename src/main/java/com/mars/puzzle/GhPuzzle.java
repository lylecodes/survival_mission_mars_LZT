package com.mars.puzzle;

import com.mars.display.Display;
import com.mars.util.JSONHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GhPuzzle implements Puzzle{
    //fields
    private String name = "GhPuzzle";
    private boolean isSolved = false;
    private Display display = new Display();
    JLabel questionLabel;
    JButton yesBtn;
    JButton noBtn;

    // could make Question nodes, put inside of question array.
    // it would hold a string question, an action listener, and refs to yes/no children paths
    // but how would the question object make changes to gui? make relevant gui components static
    // make json to hold question data and gson?
    // currentQuestion = questionMap.get(currentQuestion.getChildren()[0]) ??-- for no --
    // you will store refs to children, keyed in map by string of some sort

//    private int questionIdx = 0;
//    private String question1 = "You notice a valve in the corner of the room connected to " +  //challenge hard coded/very guided at this point
//            "the water main. Do you turn it on? Enter 'y' or 'n'\n>> ";
//    private String question2 = "You see the water mister engage and moisten the soil." +
//            "Would you like to plant some seeds? Enter 'y' or 'n'\n>> ";
//
//    private ActionListener listener_1 = e -> {
//        String answer = ((JButton) e.getSource()).getText();
//        if ("Yes".equals(answer)) {
//            setNextQuestion(question1, actionListeners);
//        }
//    };
//
//    private ActionListener listener_2 = e -> {
//        System.out.println();
//    };
//
//    private ActionListener[] actionListeners = new ActionListener[] {listener_1, listener_2};
//    private String[] questions = new String[] {question1, question2};
//
//    public void startQuestions(JLabel label, JButton yesBtn, JButton noBtn) {
//        initGuiComponents(label, yesBtn, noBtn);
//        setNextQuestion();
//    }
//
//    public void initGuiComponents(JLabel label, JButton yesBtn, JButton noBtn) {
//        this.questionLabel = label;
//        this.yesBtn = yesBtn;
//        this.noBtn = noBtn;
//    }
//
//    public void setNextQuestion(String question) {
//        if (questionIdx == questions.length) return;
//
//        questionLabel.setText(questions[questionIdx]);
//        yesBtn.setText("Yes");
//        yesBtn.addActionListener(actionListeners[questionIdx]);
//        noBtn.setText("No");
//        noBtn.addActionListener(actionListeners[questionIdx]);
//
//        questionIdx++;
//    }

    @Override
    public String showIntro() {
        return JSONHandler.getFileContentAsString("text/greenHouseIntro.txt"); //display the GH intro
    }

    @Override
    public void runPuzzle() {
        greenHousePuzzle(); //run the room specific puzzle
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