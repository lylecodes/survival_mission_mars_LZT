package com.mars.gui.alt;

import com.mars.objects.Inventory;
import com.mars.objects.Location;
import com.mars.puzzle.Puzzle;
import com.mars.util.ResourceUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JOptionPane;


public class GameFrame extends JFrame {

    private Container gameContainer;

    private JPanel titleNamePanel, startButtonPanel, backGroundStoryButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, backGroundStoryPanel, playerStats, itemPanel, itemButtonPanel, invetoryPanel, inventoryButtonPanel;
    private JLabel titleNameLabel, playerPanelLabel, hpLabel, hpLabelNumber, inventoryLabel, inventoryLabelName, itemPanelLabel, locationNameLabel, invetoryPanelLabel;


    private JProgressBar progressBar, progressBarHealth, progressBarOxygen;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font menuLabelFont = new Font("Dialog", Font.BOLD, 20);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private Font itemButtonFont = new Font("Times New Roman", Font.PLAIN, 18);
    private  JButton startButton, backGroundStoryButton, choiceButton, choiceButton1, choiceButton2, choiceButton3, choiceButton4, challengeButton, itemButton, itemButton1, itemButton2, itemButton3, itemButton4, inventoryButton, inventoryButton1, inventoryButton2;
    private JTextArea mainTextArea, backGroundTextArea;
    private int playerHP, airdamageHP, silverRing;
    private String inventoryGame, position;
    private Inventory inventory = Inventory.getInstance();
    private JButton[] choiceButtons;
    private JButton[] itemButtons, inventoryButtons;

    public GameFrame() {
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

    public void createGameScreen(Integer hp, Integer oxygen, String inventory) {
        backGroundStoryPanel.setVisible(false);
        backGroundStoryButtonPanel.setVisible(false);

        createMainTextPanel();

        createButtonPanel();

        createPlayerPanel();

        createItemPanel();

        createInventoryPanel();

//        createInventoryLogoLabel();


//        createPlayerStats();

        playerSetup(hp, oxygen, inventory);

    }

    private void createMainTextPanel() {
        mainTextPanel = new JPanel();
        // added 100 to x and y
        mainTextPanel.setBounds(200, 200, 600, 250);
        mainTextPanel.setBackground(Color.GREEN);
        gameContainer.add(mainTextPanel);

        createLocationNameLabel();
        createMainTextArea();
    }

    private void createMainTextArea() {
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

    private void createButtonPanel() {
        choiceButtonPanel = new JPanel();
        // added 100 to x and y
        choiceButtonPanel.setBounds(350, 450, 300, 150);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(5, 1));
        gameContainer.add(choiceButtonPanel);

        choiceButton1 = newChoiceButton("Choice 1", "c1");
        choiceButton2 = newChoiceButton("Choice 2", "c2");
        choiceButton3 = newChoiceButton("Choice 3", "c3");
        choiceButton4 = newChoiceButton("Choice 4", "c4");
        challengeButton = newChoiceButton("", "");
        challengeButton.setFont(normalFont);

        choiceButtonPanel.add(choiceButton1);
        choiceButtonPanel.add(choiceButton2);
        choiceButtonPanel.add(choiceButton3);
        choiceButtonPanel.add(choiceButton4);
        choiceButtonPanel.add(challengeButton);


        choiceButtons = new JButton[]{choiceButton1, choiceButton2, choiceButton3, choiceButton4};
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

        // add challenge button
        if (location.getPuzzle()) {
            challengeButton.setText("Do " + location.getTypePuzzle().getName());
            challengeButton.setVisible(true);
        } else {
            challengeButton.setVisible(false);
        }

        while (buttonIdx < choiceButtons.length) {
            choiceButtons[buttonIdx].setVisible(false);
            buttonIdx++;
        }

        setItemInfo(location.getItemNames());
        showInventoryItems((ArrayList<String>) inventory.getInventory());
    }

    public void setDirectionChoiceButtonListeners(ActionListener listener) {
        for (JButton button : choiceButtons) {
            button.addActionListener(listener);
        }
    }

    public void setChallengeButtonListeners(ActionListener listener) {
        challengeButton.addActionListener(listener);
    }

    private JButton newChoiceButton(String buttonName, String actionCommandName) {
        choiceButton = new JButton(buttonName);
        choiceButton.setForeground(Color.BLACK);
        choiceButton.setFont(normalFont);
        choiceButton.setFocusPainted(false);
        choiceButton.setActionCommand(actionCommandName);
        return choiceButton;
    }

    private JButton newItemButton() {
        itemButton = new JButton();
        itemButton.setFont(itemButtonFont);
        itemButton.setFocusPainted(false);
        itemButton.setPreferredSize(new Dimension(175, 30));
        return itemButton;
    }

    private JButton newInventoryButton() {
        inventoryButton = new JButton();
        inventoryButton.setFont(itemButtonFont);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setPreferredSize(new Dimension(175, 30));
        return inventoryButton;
    }

    private void createPlayerPanel() {
        // Colors the progress bar green
        UIManager.put("ProgressBar.selectionForeground", Color.GREEN);

        playerPanel = new JPanel();
        playerPanel.setBounds(20, 15, 600, 100);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(3, 2));
        gameContainer.add(playerPanel);

        hpLabel = newPlayerPanelLabels("HP: ");
        progressBarHealth = newJProgressBar(0, 100, 100);
        inventoryLabel = newPlayerPanelLabels("Inventory: ");
        JLabel timeLabel = newPlayerPanelLabels("Time: 5:00");
        JLabel oxygenLabel = newPlayerPanelLabels("Oxygen: ");
        progressBarOxygen = newJProgressBar(0, 100, 100);

//        Labels
        playerPanel.add(hpLabel);
        playerPanel.add(oxygenLabel);

        playerPanel.add(progressBarHealth);
        playerPanel.add(progressBarOxygen);

        playerPanel.add(timeLabel);
        playerPanel.add(inventoryLabel);

    }

    private void createItemPanel() {
        itemPanel = new JPanel();
        itemPanel.setBackground(Color.RED);
        itemPanel.setBounds(12, 200, 175, 100);
        itemPanelLabel = new JLabel("Items seen:");
        itemPanelLabel.setFont(menuLabelFont);
        itemPanelLabel.setHorizontalAlignment(JLabel.CENTER);
//        itemPanelLabel.setSize(100, 10);
        itemPanel.add(itemPanelLabel);

        createItemButtonPanel();

        gameContainer.add(itemPanel);
    }

    private void createInventoryPanel() {
        invetoryPanel = new JPanel();
        invetoryPanel.setBounds(630, 15, 175, 150);
        invetoryPanel.setBackground(Color.black);
        invetoryPanelLabel = new JLabel("use invt item: ");
        invetoryPanelLabel.setFont(normalFont);
        invetoryPanelLabel.setForeground(Color.white);
        invetoryPanelLabel.setHorizontalAlignment(JLabel.CENTER);

        invetoryPanel.add(invetoryPanelLabel);

        createInventoryButtonPanel();

        gameContainer.add(invetoryPanel);
    }

    private void createInventoryButtonPanel() {
        inventoryButtonPanel = new JPanel();
        inventoryButtonPanel.setBounds(350, 450, 200, 300);
        inventoryButtonPanel.setBackground(Color.BLACK);
        inventoryButtonPanel.setLayout(new GridLayout(4, 1));

        inventoryButton1 = newInventoryButton();
        inventoryButton2 = newInventoryButton();

        inventoryButtonPanel.add(inventoryButton1);
        inventoryButtonPanel.add(inventoryButton2);

        invetoryPanel.add(inventoryButtonPanel);

        inventoryButtons = new JButton[]{inventoryButton1, inventoryButton2};

    }

    private void createItemButtonPanel() {
        itemButtonPanel = new JPanel();
        itemButtonPanel.setBounds(350, 450, 300, 300);
        itemButtonPanel.setBackground(Color.MAGENTA);
        itemButtonPanel.setLayout(new GridLayout(4, 1));

        itemButton1 = newItemButton();
        itemButton2 = newItemButton();
        itemButton3 = newItemButton();
        itemButton4 = newItemButton();

        itemButtonPanel.add(itemButton1);
        itemButtonPanel.add(itemButton2);
        itemButtonPanel.add(itemButton3);
        itemButtonPanel.add(itemButton4);

        itemPanel.add(itemButtonPanel);

        itemButtons = new JButton[]{itemButton1, itemButton2, itemButton3, itemButton4};
    }

    private void setItemInfo(Collection<String> itemNames) {
        int buttonIdx = 0;

        for (String itemName : itemNames) {
            itemButtons[buttonIdx].setText(itemName);
            itemButtons[buttonIdx].setVisible(true);
            buttonIdx++;
        }

        while (buttonIdx < itemButtons.length) {
            itemButtons[buttonIdx].setVisible(false);
            buttonIdx++;
        }
    }

    public void showInventoryItems(ArrayList<String> list) {
        int buttonIdx = 0;
        for (String item : list) {
            inventoryButtons[buttonIdx].setText(item);
            inventoryButtons[buttonIdx].setVisible(true);
            buttonIdx++;
        }

        while (buttonIdx < inventoryButtons.length) {
            inventoryButtons[buttonIdx].setVisible(false);
            buttonIdx++;
        }
    }

    public void popUp(String errorMessage){
        JOptionPane.showMessageDialog(gameContainer,
                errorMessage);
    }

    public void setItemButtonListeners(ActionListener l) {
        for (JButton button : itemButtons) {
            button.addActionListener(l);
        }
    }


    public void setInventoryListener(ActionListener k) {
        for (JButton button : inventoryButtons) {
            button.addActionListener(k);
        }
    }

    private JLabel newPlayerPanelLabels(String labelName) {
        playerPanelLabel = new JLabel(labelName);
        playerPanelLabel.setText(labelName);
        playerPanelLabel.setFont(normalFont);
        playerPanelLabel.setForeground(Color.white);
        return playerPanelLabel;
    }

    private JProgressBar newJProgressBar(int min, int max, int currentHealth) {
        progressBar = new JProgressBar(min, max);
        progressBar.setStringPainted(true);
        progressBar.setValue(currentHealth);
        return progressBar;
    }


    public void playerSetup(Integer hp, Integer oxygen, String inventory) {
        inventoryLabel.setText("Inventory: " + inventory);
        progressBarHealth.setValue(hp);
        progressBarOxygen.setValue(oxygen);

//        solarPanel();
    }

    private void createInventoryLogoLabel() {
//        inventoryLogoPanel = new JPanel() {{
//            setBackground(Color.blue);
//            setSize(10, 10);
//        }};
        JLabel inventoryLogoLabel = new JLabel() {{
            setSize(50, 50);
        }};
        ImageIcon imageIcon =
                ResourceUtils.getImageIconScaledToLabelSize("images/backpack.png", inventoryLogoLabel);
        inventoryLogoLabel.setIcon(imageIcon);
        inventoryLogoLabel.setBackground(Color.ORANGE);
//        mainTextPanel.add(inventoryLogoLabel);
    }

    public void createIntroScreen() {
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

    private void createBackGroundStoryPanel() {
        backGroundStoryPanel = new JPanel();
        backGroundStoryPanel.setBounds(20, 100, 600, 250);
        backGroundStoryPanel.setBackground(Color.BLACK);
    }

    private void createBackGroundStoryArea() {
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

    private void solarPanel() {
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
        inventoryGame = "";
        inventoryLabelName.setText(inventoryGame);
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

    private void playerAttack() {
        position = "playerAttack";

        int playerDamage = 0;

        if (inventoryGame.equals("key")) {
            playerDamage = new java.util.Random().nextInt(3);
        } else if (inventoryGame.equals("nothing")) {
            playerDamage = new java.util.Random().nextInt(12);
        }

        mainTextArea.setText("You " + playerDamage + " damage!");

        airdamageHP = airdamageHP - playerDamage;

        choiceButton1.setText(">");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void airdamageAttack() {
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

    private void win() {
        position = "win";

        mainTextArea.setText("You defeated the airdamage.\n\n(You obtained blank )");

        silverRing = 1;

        choiceButton1.setText("Go east");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
    }

    private void lose() {
        position = "lose";
        mainTextArea.setText("You are dead!\n\n");
        disableChoices();
    }

    private void ending() {
        position = "ending";
        mainTextArea.setText(" You are true hero.");
        disableChoices();
    }

    private void disableChoices() {
        choiceButton1.setText("");
        choiceButton2.setText("");
        choiceButton3.setText("");
        choiceButton4.setText("");
        choiceButton1.setVisible(false);
        choiceButton2.setVisible(false);
        choiceButton3.setVisible(false);
        choiceButton4.setVisible(false);
    }
}


//    public  class ChoiceHandler implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            String yourChoice = event.getActionCommand();
//            switch (position) {
//                case "solarPanel":
//                    switch (yourChoice) {
//                        case "c1":
//                            //if (keys == 1)
//                                ending();
//                            //else
//                                challenge();
//                            break;
//                        case "c2":
//                            challenge();
//                            if(playerHP < 1)
//                                lose();
//                            break;
//                        case "c3":
//                            crossRoad();
//                            break;
//                    }
//                    break;
//                case "north":
//                    switch (yourChoice) {
//                        case "c1":
//                            solarPanel();
//                            break;
//                    }
//                    break;
//                case "south":
//                    switch (yourChoice) {
//                        case "c1":
//                            solarPanel();
//                            break;
//                    }
//                    break;
//                case "east":
//                    switch (yourChoice) {
//                        case "c1":
//                            north();
//                            break;
//                        case "c2":
//                            east();
//                            break;
//                        case "c3":
//                            solarPanel();
//                            break;
//                        case "c4":
//                            west();
//                            break;
//                    }
//                    break;
//                case "west":
//                    switch (yourChoice) {
//                        case "c1":
//                            //place();//todo places into case
//                            break;
//                        case "c2":
//                            crossRoad();
//                            break;
//                    }
//                    break;
//                case "use":
//                    switch (yourChoice) {
//                        case "c1":
//                            north();
//                            break;
//                        case "c2":
//                            crossRoad();
//                            break;
//                    }
//                    break;
//                case "playerdamage":
//                    switch (yourChoice) {
//                        case "c1":
//                            if (airdamageHP < 1) {
//                                win();
//                            } else {
//                                airdamageAttack();
//                            }
//                            break;
//                    }
//                    break;
//
//                case "bonedamage":
//
//                    switch (yourChoice) {
//                        case "c1":
//                            if (playerHP < 1) {
//                                lose();
//                            } else {
//                                lose();
//                            }
//                            break;
//                    }
//                    break;
//                case "win":
//                    switch (yourChoice) {
//                        case "c1":
//                            crossRoad();
//                    }
//                    break;
//            }
//        }
//    }
//}