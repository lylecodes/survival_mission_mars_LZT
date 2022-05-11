package com.mars.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * Template
 */

public class GameGui {

    JTextArea text;
    JTextArea textMap;
    JFrame frame;
    JTextField field;

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
        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelStoryOutput.add(scroller);

        textMap = new JTextArea(10,50);
        textMap.setLineWrap(true);
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
    //    Action Listener Interface for "actionPerformed"
//    Event Handling method
    class UserInputListner implements ActionListener {
        public void actionPerformed(ActionEvent event){
//                takes in user input
            String userInput = field.getText();
            System.out.println(userInput);
            field.setText("");
//                Update text area with current user input
            text.setText("");
            text.append("button clicked user inputed \n" + userInput);
//                Map and inventory
            textMap.setText("");
            textMap.setText("map, inventory, oxygen, health");

        }

    }
    //    Second event listener
    class ScreenUpdateListner implements ActionListener {
        public void actionPerformed(ActionEvent event){

        }
    }
}


