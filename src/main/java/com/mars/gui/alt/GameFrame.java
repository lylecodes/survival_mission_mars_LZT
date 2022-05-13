package com.mars.gui.alt;

import com.mars.objects.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private  Container gameContainer;
    private  JPanel titleNamePanel, startButtonPanel, backGroundStoryButtonPanel, mainTextPanel, itemPanel, choiceButtonPanel, playerPanel, backGroundStoryPanel;
    private  JLabel titleNameLabel, playerPanelLabel, hpLabel, hpLabelNumber, itemPanelLabel, inventoryLabel, inventoryLabelName, locationNameLabel;
    private  Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font menuLabelFont = new Font("Dialog", Font.BOLD, 20);
    private  Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private  JButton startButton, backGroundStoryButton, choiceButton, choiceButton1, choiceButton2, choiceButton3, choiceButton4;
    private  JTextArea mainTextArea, backGroundTextArea;
    private  int playerHP, airdamageHP, silverRing;
    private  String inventory, position;
    
    private  ChoiceHandler choiceHandler = new ChoiceHandler();

    private JButton[] choiceButtons;

    public GameFrame(){
        // added 200 to width and height
        setSize(1000, 800);
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

    private void createTitleNamePanel() {
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.red);
    }

    private void createTitleNameLabel() {
        titleNameLabel = new JLabel("Survival Mars");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
    }

    private void createStartButtonPanel() {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
    }

    private void createStartButton() {
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.RED);
        startButton.setFont(normalFont);
//        startButton.addActionListener(titleScreenHandler);
    }
    
    public void setTitleScreenHandler(ActionListener l) {
        startButton.addActionListener(l);
    }

    public void createGameScreen() {
        backGroundStoryPanel.setVisible(false);
        backGroundStoryButtonPanel.setVisible(false);

        createMainTextPanel();

        createButtonPanel();

        createPlayerPanel();

        createItemPanel();

        playerSetup();
    }

    private void createMainTextPanel(){
        mainTextPanel = new JPanel();
        // added 100 to x and y
        mainTextPanel.setBounds(200, 200, 600, 250);
        mainTextPanel.setBackground(Color.GREEN);
        gameContainer.add(mainTextPanel);

        createLocationNameLabel();
        createMainTextArea();
    }

    private void createMainTextArea(){
        createLocationNameLabel();
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
    }

    private void createLocationNameLabel() {
        locationNameLabel = new JLabel();
        locationNameLabel.setFont(menuLabelFont);
        mainTextPanel.add(locationNameLabel);
    }

    private void createButtonPanel(){
        choiceButtonPanel = new JPanel();
        // added 100 to x and y
        choiceButtonPanel.setBounds(350, 450, 300, 150);
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

        choiceButtons = new JButton[] {choiceButton1, choiceButton2, choiceButton3, choiceButton4};
    }

    public void setLocationInfo(Location location) {
        locationNameLabel.setText(location.getName());
        mainTextArea.setText(location.getDescription());
        int buttonIdx = 0;

        for (var directionPair : location.getDirections().entrySet()) {
            String direction = directionPair.getKey();
            String directionName = directionPair.getKey();

            String str = "Go " + direction;
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

    private JButton newChoiceButton(String buttonName, String actionCommandName) {
        choiceButton = new JButton(buttonName);
        choiceButton.setForeground(Color.BLACK);
        choiceButton.setFont(normalFont);
        choiceButton.setFocusPainted(false);
        choiceButton.addActionListener(choiceHandler);
        choiceButton.setActionCommand(actionCommandName);
        return choiceButton;
    }

    private void createPlayerPanel() {
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

    private void createItemPanel() {
        itemPanel = new JPanel();
        itemPanel.setBackground(Color.RED);
        itemPanel.setBounds(25, 175, 150, 300);
        itemPanel.setLayout(new GridLayout(4, 1));
        itemPanelLabel = new JLabel("Items seen:");
        itemPanelLabel.setFont(menuLabelFont);
        itemPanelLabel.setHorizontalAlignment(JLabel.CENTER);

        itemPanel.add(itemPanelLabel);
        gameContainer.add(itemPanel);
    }

    private JLabel newPlayerPanelLabels(String labelName){
        playerPanelLabel = new JLabel(labelName);
        playerPanelLabel.setText(labelName);
        playerPanelLabel.setFont(normalFont);
        playerPanelLabel.setForeground(Color.white);
        return playerPanelLabel;
    }

    private void playerSetup() {
        playerHP = 100;
        airdamageHP = 20;
        inventory = "key";
        inventoryLabelName.setText(inventory);
        hpLabelNumber.setText("" + playerHP);
        solarPanel();
    }

    public void createIntroScreen(){
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

    private void createBackGroundStoryPanel(){
        backGroundStoryPanel = new JPanel();
        backGroundStoryPanel.setBounds(20, 100, 600, 250);
        backGroundStoryPanel.setBackground(Color.BLACK);
    }

    private void createBackGroundStoryArea(){
        backGroundTextArea = new JTextArea("You have been deployed from Mars HQ to a remote outpost.\nYour objective is to return this outpost to operational status.\n" +
                " You have 14 days to complete these tasks.");
        backGroundTextArea.setBounds(200, 100, 600, 600);
        backGroundTextArea.setBackground(Color.black);
        backGroundTextArea.setForeground(Color.green);
        backGroundTextArea.setFont(normalFont);
        backGroundTextArea.setLineWrap(true);
    }

    private void createBackGroundStoryButtonPanel() {
        backGroundStoryButtonPanel = new JPanel();
        backGroundStoryButtonPanel.setBounds(290, 400, 200, 100);
        backGroundStoryButtonPanel.setBackground(Color.black);
    }

    private void createBackGroundStoryButton() {
        backGroundStoryButton = new JButton("START");
        backGroundStoryButton.setBackground(Color.black);
        backGroundStoryButton.setForeground(Color.RED);
        backGroundStoryButton.setFont(normalFont);
    }

    public void setIntroScreenHandler(ActionListener l) {
        backGroundStoryButton.addActionListener(l);
    }

    private  void solarPanel() {
        position = "In freaking space";
        mainTextArea.setText("You are stuck in a dying mars outpost. \n\nWhat do you do?");
        choiceButton1.setText("Go North");
        choiceButton2.setText("Go South");
        choiceButton3.setText("Go East");
        choiceButton4.setText("Do challenge");
    }

    private void challenge() {
        position = "challenge";
        mainTextArea.setText("town.");
        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void spacestation() {
        position = "spacestation";
        mainTextArea.setText("you are at 30");
        playerHP = playerHP - 3;
        hpLabelNumber.setText("" + playerHP);
        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void crossRoad() {
        position = "crossRoad";
        mainTextArea.setText("You are at a crossroad.\nIf you go south, you will go back to the town.");
        choiceButton1.setText("Go north");
        choiceButton2.setText("Go east");
        choiceButton3.setText("Go south");
        choiceButton4.setText("Go west");
    }

    private void south() {
        position = "south";
        mainTextArea.setText("There is a river.");
        playerHP = playerHP + 2;
        hpLabelNumber.setText("" + playerHP);
        choiceButton1.setText("Go south");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void east() {
        position = "east";
        mainTextArea.setText("You walked east");
        inventory = "";
        inventoryLabelName.setText(inventory);
        choiceButton1.setText("Go west");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void west() {
        position = "west";
        mainTextArea.setText("You go west!");
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void north() {
        position = "north";
        mainTextArea.setText("What do you do?");
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private  void playerAttack() {
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

    private  void airdamageAttack() {
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

    private  void win() {
        position = "win";

        mainTextArea.setText("You defeated the airdamage.\n\n(You obtained blank )");

        silverRing = 1;

        choiceButton1.setText("Go east");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private  void lose() {
        position = "lose";
        mainTextArea.setText("You are dead!\n\n");
        disableChoices();
    }

    private  void ending() {
        position = "ending";
        mainTextArea.setText(" You are true hero.");
        disableChoices();
    }

    private  void disableChoices(){
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
        choiceButton1.setVisible(false);
        choiceButton2.setVisible(false);
        choiceButton3.setVisible(false);
        choiceButton4.setVisible(false);
    }



    public  class ChoiceHandler implements ActionListener {
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