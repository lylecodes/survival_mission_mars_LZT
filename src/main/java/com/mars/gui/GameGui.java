package com.mars.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * Template
 */

public class GameGui {

    private JTextArea text;
    private JTextArea textMap;
    private JFrame frame;
    private JTextField field;
    private String userResponse="";

    private GameGui(){
    }

    private static GameGui INSTANCE = new GameGui();

    public static GameGui getINSTANCE() {
        return INSTANCE;
    }

    public void go(){

        frame = new JFrame(); //        create window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //        when window closed end Java script

        JPanel panelUserCommand = new JPanel();
        panelUserCommand.setBackground(Color.darkGray);
        panelUserCommand.setLayout(new BoxLayout(panelUserCommand, BoxLayout.X_AXIS));

        JPanel panelStoryOutput = new JPanel();
        panelStoryOutput.setBackground(Color.GRAY);
        panelStoryOutput.setLayout(new BoxLayout(panelStoryOutput, BoxLayout.Y_AXIS));

        JPanel panelMapOutput = new JPanel();
        panelStoryOutput.setBackground(Color.GRAY);
        panelStoryOutput.setLayout(new BoxLayout(panelStoryOutput, BoxLayout.Y_AXIS));


        JButton button = new JButton("Submit!"); //        Create Button
        button.addActionListener(new UserInputListner()); //        Adds event listener to Button
        panelUserCommand.add(button);

        text = new JTextArea(10,50);
        text.setLineWrap(true);
//        set text whenn the game starts to the splash screen and what ever else they have
        text.setText("STORY GOES HERE");
        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelStoryOutput.add(scroller);

        textMap = new JTextArea(10,50);
        textMap.setLineWrap(true);
        text.setText("PLAYER MAP STUFF HERE");
        JScrollPane scrollerMap = new JScrollPane(textMap);
        scrollerMap.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerMap.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelMapOutput.add(scrollerMap);



        field = new JTextField(5);
        panelUserCommand.add(field);

        frame.getContentPane().add(BorderLayout.WEST, panelStoryOutput);
        frame.getContentPane().add(BorderLayout.EAST, panelMapOutput);
        frame.getContentPane().add(BorderLayout.SOUTH, panelUserCommand);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
    public void updateStory(String updateString){
//        text.setText("");
        text.append(updateString);
    }
    public void newStory(String updateString){
        text.setText("");
        text.setText(updateString);
    }
    public void updateHUB(String map){
        textMap.setText("");
        textMap.setText(map);
    }
    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }
    //    Action Listener Interface for "actionPerformed"
//    Event Handling method
    class UserInputListner implements ActionListener {
        public void actionPerformed(ActionEvent event){
//                takes in user input
            String userInput = field.getText();
            field.setText("");
            setUserResponse(userInput);
//            GO TO TRANSLATOR
//            System.out.println(userInput);
//            field.setText("");
////                Update text area with current user input
//            text.setText("");
//            text.append("button clicked user inputed \n" + userInput);
////                Map and inventory
//            textMap.setText("");
//            textMap.setText("map, inventory, oxygen, health");

        }

    }
    //    Second event listener
    class ScreenUpdateListner implements ActionListener {
        public void actionPerformed(ActionEvent event){

        }
    }
}


