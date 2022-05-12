package com.mars.gui.alt;

import com.mars.objects.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.Map;

public class GameFrame extends JFrame {
    private static Container gameContainer;
    private static JPanel titleNamePanel, startButtonPanel, backGroundStoryButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, backGroundStoryPanel;
    private static JLabel titleNameLabel, playerPanelLabel, hpLabel, hpLabelNumber, inventoryLabel, inventoryLabelName;
    private static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private static Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private static JButton startButton, backGroundStoryButton, choiceButton, choiceButton1, choiceButton2, choiceButton3, choiceButton4;
    private static JTextArea mainTextArea, backGroundTextArea;
    private static int playerHP, airdamageHP, silverRing;
    private static String inventory, position;

    private static IntroScreenHandler introScreenHandler = new IntroScreenHandler();
    private static TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
    private static ChoiceHandler choiceHandler = new ChoiceHandler();

    private static JButton[] choiceButtons;


    public GameFrame(){
        setSize(800, 600);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        gameContainer = getContentPane();
        createTitleNamePanel();
        createTitleNameLabel();
        createStartButtonPanel();
        createStartButton();

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        gameContainer.add(titleNamePanel);
        gameContainer.add(startButtonPanel);
    }

    private static void createTitleNamePanel() {
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.red);
    }

    private static JLabel createTitleNameLabel() {
        titleNameLabel = new JLabel("Survival Mars");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        return titleNameLabel;
    }

    private static void createStartButtonPanel() {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
    }

    private static void createStartButton() {
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.RED);
        startButton.setFont(normalFont);
        startButton.addActionListener(titleScreenHandler);
    }

    private static void createGameScreen() {
        backGroundStoryPanel.setVisible(false);
        backGroundStoryButtonPanel.setVisible(false);

        createMainTextPanel();

        createButtonPanel();

        createPlayerPanel();

        playerSetup();
    }

    private static void createMainTextPanel(){
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.BLACK);
        gameContainer.add(mainTextPanel);

        createMainTextArea();
    }

    private static void createMainTextArea(){
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }

    private static void createButtonPanel(){
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        gameContainer.add(choiceButtonPanel);

        choiceButton1 = newChoiceButton("Choice 1", "c1");
        choiceButton2 = newChoiceButton("Choice 2", "c2");
        choiceButton3 = newChoiceButton("Choice 3", "c3");
        choiceButton4 = newChoiceButton("Choice 4", "c4");

        choiceButtonPanel.add(choiceButton1);
        choiceButtonPanel.add(choiceButton2);
        choiceButtonPanel.add(choiceButton3);
        choiceButtonPanel.add(choiceButton4);
    }

    public void setLocationInfo(Location location) {
        position = location.getName();
        mainTextArea.setText(location.getDescription());
        int buttonIdx = 0;

        for (var directionPair : location.getDirections().entrySet()) {
            String direction = directionPair.getKey();
            String directionName = directionPair.getKey();

            String str = "Go " + direction + "to " + directionName;
            choiceButtons[buttonIdx].setText(str);
            choiceButtons[buttonIdx].setVisible(true);
            buttonIdx++;
        }

        while (buttonIdx < choiceButtons.length) {
            choiceButtons[buttonIdx].setVisible(false);
            buttonIdx++;
        }
    }

    public void addDirectionChoiceButtonListeners(ActionListener listener) {
        for (var button : choiceButtons) {
            button.addActionListener(listener);
        }
    }

    private static JButton newChoiceButton(String buttonName, String actionCommandName) {
        choiceButton = new JButton(buttonName);
        choiceButton.setForeground(Color.BLACK);
        choiceButton.setFont(normalFont);
        choiceButton.setFocusPainted(false);
        choiceButton.addActionListener(choiceHandler);
        choiceButton.setActionCommand(actionCommandName);
        return choiceButton;
    }

    private static void createPlayerPanel() {
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1, 4));
        gameContainer.add(playerPanel);

        hpLabel = newPlayerPanelLabels("HP: ");
        hpLabelNumber = newPlayerPanelLabels("");
        inventoryLabel = newPlayerPanelLabels("Inventory: ");
        inventoryLabelName = newPlayerPanelLabels("");
        JLabel timeLabel = newPlayerPanelLabels("Time: ");
        JLabel timeLabelName = newPlayerPanelLabels("");

        playerPanel.add(hpLabel);
        playerPanel.add(hpLabelNumber);
        playerPanel.add(inventoryLabel);
        playerPanel.add(inventoryLabelName);
    }

    private static JLabel newPlayerPanelLabels(String labelName){
        playerPanelLabel = new JLabel(labelName);
        playerPanelLabel.setText(labelName);
        playerPanelLabel.setFont(normalFont);
        playerPanelLabel.setForeground(Color.white);
        return playerPanelLabel;
    }

    private static void playerSetup() {
        playerHP = 100;
        airdamageHP = 20;
        inventory = "key";
        inventoryLabelName.setText(inventory);
        hpLabelNumber.setText("" + playerHP);
        solarPanel();
    }

    private static void createIntroScreen(){
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        createBackGroundStoryPanel();
        createBackGroundStoryArea();

        createBackGroundStoryButtonPanel();
        createBackGroundStoryButton();

        backGroundStoryPanel.add(backGroundTextArea);
        backGroundStoryButtonPanel.add(backGroundStoryButton);

        gameContainer.add(backGroundStoryPanel);
        gameContainer.add(backGroundStoryButtonPanel);
    }

    private static void createBackGroundStoryPanel(){
        backGroundStoryPanel = new JPanel();
        backGroundStoryPanel.setBounds(20, 100, 600, 250);
        backGroundStoryPanel.setBackground(Color.BLACK);
    }

    private static void createBackGroundStoryArea(){
        backGroundTextArea = new JTextArea("You have been deployed from Mars HQ to a remote outpost.\nYour objective is to return this outpost to operational status.\n" +
                " You have 14 days to complete these tasks.");
        backGroundTextArea.setBounds(200, 100, 600, 600);
        backGroundTextArea.setBackground(Color.black);
        backGroundTextArea.setForeground(Color.green);
        backGroundTextArea.setFont(normalFont);
        backGroundTextArea.setLineWrap(true);
    }

    private static void createBackGroundStoryButtonPanel() {
        backGroundStoryButtonPanel = new JPanel();
        backGroundStoryButtonPanel.setBounds(290, 400, 200, 100);
        backGroundStoryButtonPanel.setBackground(Color.black);
    }

    private static void createBackGroundStoryButton() {
        backGroundStoryButton = new JButton("START");
        backGroundStoryButton.setBackground(Color.black);
        backGroundStoryButton.setForeground(Color.RED);
        backGroundStoryButton.setFont(normalFont);
        backGroundStoryButton.addActionListener(introScreenHandler);
    }

    private static void solarPanel() {
        position = "In freaking space";
        mainTextArea.setText("You are stuck in a dying mars outpost. \n\nWhat do you do?");
        choiceButton1.setText("Go North");
        choiceButton2.setText("Go South");
        choiceButton3.setText("Go East");
        choiceButton4.setText("Do challenge");
    }

    private static void challenge() {
        position = "challenge";
        mainTextArea.setText("town.");
        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void spacestation() {
        position = "spacestation";
        mainTextArea.setText("you are at 30");
        playerHP = playerHP - 3;
        hpLabelNumber.setText("" + playerHP);
        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void crossRoad() {
        position = "crossRoad";
        mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go back to the town.");
        choiceButton1.setText("Go north");
        choiceButton2.setText("Go east");
        choiceButton3.setText("Go south");
        choiceButton4.setText("Go west");
    }

    private static void south() {
        position = "south";
        mainTextArea.setText("There is a river.");
        playerHP = playerHP + 2;
        hpLabelNumber.setText("" + playerHP);
        choiceButton1.setText("Go south");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void east() {
        position = "east";
        mainTextArea.setText("You walked east");
        inventory = "";
        inventoryLabelName.setText(inventory);
        choiceButton1.setText("Go west");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void west() {
        position = "west";
        mainTextArea.setText("You go west!");
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void north() {
        position = "north";
        mainTextArea.setText("What do you do?");
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void playerAttack() {
        position = "playerAttack";

        int playerDamage = 0;

        if (inventory.equals("key")) {
            playerDamage = new java.util.Random().nextInt(3);
        } else if (inventory.equals("nothing")) {
            playerDamage = new java.util.Random().nextInt(12);
        }

        mainTextArea.setText("You " + playerDamage + " damage!");

        airdamageHP = airdamageHP - playerDamage;

        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void airdamageAttack() {
        position = "airdamageAttack";

        int airdamageDamage = 0;

        airdamageDamage = new java.util.Random().nextInt(6);

        mainTextArea.setText("The airdamage attacked you and gave " + airdamageDamage + " damage!");

        playerHP = playerHP - airdamageDamage;
        hpLabelNumber.setText("" + playerHP);

        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void win() {
        position = "win";

        mainTextArea.setText("You defeated the airdamage.\n\n(You obtained blank )");

        silverRing = 1;

        choiceButton1.setText("Go east");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private static void lose() {
        position = "lose";
        mainTextArea.setText("You are dead!\n\n");
        disableChoices();
    }

    private static void ending() {
        position = "ending";
        mainTextArea.setText(" You are true hero.");
        disableChoices();
    }

    private static void disableChoices(){
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
        choiceButton1.setVisible(false);
        choiceButton2.setVisible(false);
        choiceButton3.setVisible(false);
        choiceButton4.setVisible(false);
    }

    public static class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createIntroScreen();
        }
    }

    public static class IntroScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            createGameScreen();
        }
    }

    public static class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            switch (position) {
                case "solarPanel":
                    switch (yourChoice) {
                        case "c1":
                            //if (keys == 1)
                                ending();
                            //else
                                challenge();
                            break;
                        case "c2":
                            challenge();
                            if(playerHP < 1)
                                lose();
                            break;
                        case "c3":
                            crossRoad();
                            break;
                    }
                    break;
                case "north":
                    switch (yourChoice) {
                        case "c1":
                            solarPanel();
                            break;
                    }
                    break;
                case "south":
                    switch (yourChoice) {
                        case "c1":
                            solarPanel();
                            break;
                    }
                    break;
                case "east":
                    switch (yourChoice) {
                        case "c1":
                            north();
                            break;
                        case "c2":
                            east();
                            break;
                        case "c3":
                            solarPanel();
                            break;
                        case "c4":
                            west();
                            break;
                    }
                    break;
                case "west":
                    switch (yourChoice) {
                        case "c1":
                            //place();//todo places into case
                            break;
                        case "c2":
                            crossRoad();
                            break;
                    }
                    break;
                case "use":
                    switch (yourChoice) {
                        case "c1":
                            north();
                            break;
                        case "c2":
                            crossRoad();
                            break;
                    }
                    break;
                case "playerdamage":
                    switch (yourChoice) {
                        case "c1":
                            if (airdamageHP < 1) {
                                win();
                            } else {
                                airdamageAttack();
                            }
                            break;
                    }
                    break;

                case "bonedamage":

                    switch (yourChoice) {
                        case "c1":
                            if (playerHP < 1) {
                                lose();
                            } else {
                                lose();
                            }
                            break;
                    }
                    break;
                case "win":
                    switch (yourChoice) {
                        case "c1":
                            crossRoad();
                    }
                    break;
            }
        }
    }
}