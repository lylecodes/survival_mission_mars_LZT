package com.mars.gui.alt;

import com.mars.display.Display;
import com.mars.objects.Inventory;
import com.mars.objects.Location;
import com.mars.util.ResourceUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {

    public static Container gameContainer;

    private JPanel titleNamePanel, startButtonPanel, backGroundStoryButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, backGroundStoryPanel,  itemPanel, itemButtonPanel, invetoryPanel, inventoryButtonPanel;
    private JLabel titleNameLabel, playerPanelLabel, hpLabel, inventoryLabel, itemPanelLabel, locationNameLabel, timeLeft;
    private JProgressBar progressBar, progressBarHealth, progressBarOxygen;

    private Font menuLabelFont = new Font("Dialog", Font.BOLD, 20);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private Font itemButtonFont = new Font("Times New Roman", Font.PLAIN, 18);
    private  JButton startButton, backGroundStoryButton, choiceButton, choiceButton1, choiceButton2, choiceButton3, choiceButton4, challengeButton, itemButton, itemButton1, itemButton2, itemButton3, itemButton4, inventoryButton, inventoryButton1, inventoryButton2, gameMenu, hitTheGym;
    private JTextArea mainTextArea, backGroundTextArea;

    private int playerHP;
    private String inventoryGame, position;

    private Inventory inventory = Inventory.getInstance();
    private JButton[] choiceButtons;
    private JButton[] itemButtons, inventoryButtons;
    private Display display = new Display();
    private ImageIcon imageIcon =
            ResourceUtils.getImageIconScaledToLabelSizePopUp("images/happyMars.png");

    public GameFrame() {
        setSize(1000, 800);
        setResizable(false);
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
        createIntroScreen();
    }

    public void setFireImage() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 600, 1000, 190);
        panel.setBackground(Color.black);
        JPanel panelTop = new JPanel();
        panelTop.setBounds(200, 110, 600, 90);
        panelTop.setBackground(Color.black);
        JPanel panelAlien = new JPanel();
        panelAlien.setBounds(0, 300, 200, 200);
        panelAlien.setBackground(Color.black);
        JPanel panelGalaxy2 = new JPanel();
        panelGalaxy2.setBounds(850, 300, 200, 200);
        panelGalaxy2.setBackground(Color.black);

        JLabel fireLabel = new JLabel() {{
            setSize(1000, 190);
        }};
        JLabel fireLabelTop = new JLabel() {{
            setSize(600, 90);
        }};
        JLabel alienLabel = new JLabel() {{
            setSize(200, 200);
        }};
        JLabel galaxy2Label = new JLabel() {{
            setSize(200, 200);
        }};


        ImageIcon imageIcon =
                ResourceUtils.getImageIconScaledToLabelSize("images/fire_gif.gif", fireLabel);
        ImageIcon imageIconSmall =
                ResourceUtils.getImageIconScaledToLabelSize("images/fire_gif.gif", fireLabelTop);
        ImageIcon alienIcon =
                ResourceUtils.getImageIconScaledToLabelSize("images/galaxy.png", alienLabel);
        ImageIcon galaxy2Icon =
                ResourceUtils.getImageIconScaledToLabelSize("images/galaxy2.png", alienLabel);
        fireLabel.setIcon(imageIcon);
        fireLabelTop.setIcon(imageIconSmall);
        alienLabel.setIcon(alienIcon);
        galaxy2Label.setIcon(galaxy2Icon);
        panel.add(fireLabel);
        panelTop.add(fireLabelTop);
        panelAlien.add(alienLabel);
        panelGalaxy2.add(galaxy2Label);
        gameContainer.add(panel);
        gameContainer.add(panelTop);
        gameContainer.add(panelAlien);
        gameContainer.add(panelGalaxy2);
    }

    private void createTitleNamePanel() {
        Color myBrickColor = new Color(134, 57, 57);
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(200, 200, 600, 150);
        titleNamePanel.setBackground(myBrickColor);
    }

    private void createTitleNameLabel() {
        titleNameLabel = new JLabel();
        ImageIcon imageIcon =
                ResourceUtils.getImageIconScaledToLabelSizePopUp("images/title.png");
        titleNameLabel.setIcon(imageIcon);
    }

    private void createStartButtonPanel() {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 500, 200, 100);
        startButtonPanel.setBackground(Color.black);
    }

    private void createStartButton() {
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.GREEN);
        startButton.setFont(normalFont);
//        startButton.addActionListener(titleScreenHandler);
    }

    public void setTitleScreenHandler(ActionListener l) {
        startButton.addActionListener(l);
    }

    public void createGameScreen(Integer hp, Integer oxygen, String inventory, String time) {
        backGroundStoryPanel.setVisible(false);
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        createMainTextPanel();

        createButtonPanel();

        createPlayerPanel();

        createItemPanel();

        createInventoryPanel();

        playerSetup(hp, oxygen, inventory, time);

    }

    private void createMainTextPanel() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(200, 200, 600, 250);
        mainTextPanel.setBackground(Color.decode("#d6723b"));
        gameContainer.add(mainTextPanel);

        createLocationNameLabel();
        createMainTextArea();
    }

    private void createMainTextArea() {
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.decode("#d6723b"));
        mainTextArea.setForeground(Color.darkGray);
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
        choiceButtonPanel.setBounds(300, 450, 400, 200);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(6, 1));
        gameContainer.add(choiceButtonPanel);

        choiceButton1 = newChoiceButton("Choice 1", "c1");
        choiceButton2 = newChoiceButton("Choice 2", "c2");
        choiceButton3 = newChoiceButton("Choice 3", "c3");
        choiceButton4 = newChoiceButton("Choice 4", "c4");
        challengeButton = newChoiceButton("", "");
        challengeButton.setFont(normalFont);
        hitTheGym = newChoiceButton("Hit the Gym","gymButton");
        hitTheGym.setFont(normalFont);

        choiceButtonPanel.add(choiceButton1);
        choiceButtonPanel.add(choiceButton2);
        choiceButtonPanel.add(choiceButton3);
        choiceButtonPanel.add(choiceButton4);
        choiceButtonPanel.add(challengeButton);
        choiceButtonPanel.add(hitTheGym);


        choiceButtons = new JButton[]{choiceButton1, choiceButton2, choiceButton3, choiceButton4};
        for (JButton button : choiceButtons) {
            button.setPreferredSize(new Dimension(300, 100));
        }
    }

    public void createIntroScreen() {

        createBackGroundStoryPanel();
        createBackGroundStoryArea();

        backGroundStoryPanel.add(backGroundTextArea);
        gameContainer.add(backGroundStoryPanel);
    }

    private void createBackGroundStoryPanel() {
        backGroundStoryPanel = new JPanel();
        backGroundStoryPanel.setBounds(50, 400, 500, 250);
        backGroundStoryPanel.setBackground(Color.BLACK);
    }

    private void createBackGroundStoryArea() {
        String storySplash = display.displayGUI("text/splash2.txt");
        backGroundTextArea = new JTextArea(storySplash);
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

    public void setLocationInfo(Location location) {
        locationNameLabel.setText(location.getName());
        mainTextArea.setText(location.getDescription());
        int buttonIdx = 0;

        for (var directionPair : location.getDirections().entrySet()) {
            String direction = directionPair.getKey();
            String directionName = directionPair.getValue();

            String str = "Go " + direction + " to " + directionName;
            choiceButtons[buttonIdx].setText(str);
            choiceButtons[buttonIdx].setVisible(true);
            buttonIdx++;
        }

        // add challenge button
        if (location.getPuzzle() && !location.getTypePuzzle().isSolved()) {
            challengeButton.setText("Do " + location.getTypePuzzle().getName());
            challengeButton.setVisible(true);
        } else {
            challengeButton.setVisible(false);
        }
        if (location.getName().equals("Gym")){
            hitTheGym.setVisible(true);
        }
        else{
            hitTheGym.setVisible(false);
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
        hitTheGym.addActionListener(listener);
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
        /*
         * Creates Player HUD which encompasses HEALTH, BONE, GAME OPTION, and TIMER
         */

        // Colors the progress bar percentage number Green
        UIManager.put("ProgressBar.selectionForeground", Color.GREEN);

        // Create and place Player Panel
        playerPanel = new JPanel();
        playerPanel.setBounds(120, 15, 600, 100);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(3, 2));
        gameContainer.add(playerPanel);

        // Create player items
        hpLabel = newPlayerPanelLabels("HP: ");
        progressBarHealth = newJProgressBar(0, 100, 100);
        inventoryLabel = newPlayerPanelLabels("Inventory: ");
        gameMenu = new JButton();
        gameMenu.setText("Game Menu");
        timeLeft = newPlayerPanelLabels("Time: 5:00");
        JLabel oxygenLabel = newPlayerPanelLabels("Bone Density: ");
        progressBarOxygen = newJProgressBar(0, 100, 100);

        // add created items Panel, 3 items in row and 3 columns
        // row 1
        playerPanel.add(hpLabel);
        playerPanel.add(oxygenLabel);

        // row 2
        playerPanel.add(progressBarHealth);
        playerPanel.add(progressBarOxygen);

        // row 3
        playerPanel.add(timeLeft);
        playerPanel.add(gameMenu);

    }

    private void createItemPanel() {
        itemPanel = new JPanel();
        itemPanel.setBackground(Color.decode("#d6723b"));
        itemPanel.setBounds(12, 200, 175, 155);
        itemPanelLabel = new JLabel("Items seen:");
        itemPanelLabel.setFont(menuLabelFont);
        itemPanelLabel.setHorizontalAlignment(JLabel.CENTER);
//        itemPanelLabel.setSize(100, 10);
        itemPanel.add(itemPanelLabel);

        createItemButtonPanel();

        gameContainer.add(itemPanel);
    }

    private void createInventoryPanel() {
        /*
         * Inventory Panel holds items that user picks up
         */
        invetoryPanel = new JPanel();
        invetoryPanel.setBounds(800, 15, 175, 150);

        invetoryPanel.setBackground(Color.black);
        invetoryPanel.add(createInventoryLogoLabel());

        // Create items picked up
        createInventoryButtonPanel();

        // Add to main JFrame
        gameContainer.add(invetoryPanel);
    }

    private void createInventoryButtonPanel() {
        /*
         * Creates buttons for the Inventory items picks up
         */
        // Panel
        inventoryButtonPanel = new JPanel();
        inventoryButtonPanel.setBounds(350, 450, 200, 300);
        inventoryButtonPanel.setBackground(Color.BLACK);
        inventoryButtonPanel.setLayout(new GridLayout(4, 1));

        // Buttons
        inventoryButton1 = newInventoryButton();
        inventoryButton2 = newInventoryButton();

        inventoryButtonPanel.add(inventoryButton1);
        inventoryButtonPanel.add(inventoryButton2);

        // Add Buttons to inventory Panel
        invetoryPanel.add(inventoryButtonPanel);

        inventoryButtons = new JButton[]{inventoryButton1, inventoryButton2};

    }

    private void createItemButtonPanel() {
        itemButtonPanel = new JPanel();
        itemButtonPanel.setBounds(350, 450, 300, 400);
        itemButtonPanel.setBackground(Color.BLACK);
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
        /*
         * Go through buttons and once that have items set them visisble
         */
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
        /*
         * Pop up used to inform player
         */
        JOptionPane.showMessageDialog(
                gameContainer,
                errorMessage,
                "mars",
                0,
                imageIcon
        );
    }

    public void popUpImage() {
        ImageIcon imageIcon =
                ResourceUtils.getImageIconScaledToLabelSizePopUp("images/mappymap.jpg");
        JOptionPane.showMessageDialog(
                gameContainer,
                imageIcon
        );
    }

    public int popUpInventory(String item, String description){
        /*
         * Create popUp that gives player options on what they can do with each item
         */

        // Options that are available to player
        Object[] options1 = { "cancel", "drop",
                "use" };

        // return index of Option picked by player. And Create pop up based off of below specs
        int result = JOptionPane.showOptionDialog(
                gameContainer,
                "Item: " + item + "\n" + "Description: " + description,
                "Item: " + item,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                imageIcon,
                options1,
                options1[2]);
        return result;
    }

    public int popUpGodMode(){
        /*
         * Asks user if they would like to use God Mode
         */
        Object[] options1 = {
                "Yes",
                "No"
        };
        int result = JOptionPane.showOptionDialog(
                gameContainer,
                "You are pumped, do you want to activate God mode?",
                "Steve B Secret",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                imageIcon,
                options1,
                options1[1]);
        return result;
    }

    public int popUpPlayAgain(){
        /*
         * Pop up used when player dies. To see if they would like to play again
         */
        Object[] options1 = { "Play Again!",
                "Quit" };

        int result = JOptionPane.showOptionDialog(
                gameContainer,
                "You Died \n Health or Bone Density hit 0 \n Blue Origin needs you please continue",
                "Game Over",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                imageIcon,
                options1,
                options1[0]);
        return result;
    }

    public int popUpGameOption(){
        /*
         * Player Option for the game
         */
        Object[] options1 = {
                "Cancel",
                "Quit",
                "Game Help",
                "Map",
                "Mission List",
                "Mute Audio"
        };

        int result = JOptionPane.showOptionDialog(
                gameContainer,
                "Game Options Menu:",
                "Game Option",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                imageIcon,
                options1,
                options1[0]);
        return result;
    }

    // Listeners for various events
    // TODO look into combining them
    public void setItemButtonListeners(ActionListener l) {
        for (JButton button : itemButtons) {
            button.addActionListener(l);
        }
    }

    public void setMainMenuButtonListeners(ActionListener j){
        gameMenu.addActionListener(j);
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
        /*
         * Progress bar used to visualize player Health and Bone
         */
        progressBar = new JProgressBar(min, max);
        progressBar.setStringPainted(true);
        progressBar.setValue(currentHealth);
        return progressBar;
    }


    public void playerSetup(Integer hp, Integer oxygen, String inventory, String time) {
        /*
         * Updates player information, as he moves through rooms
         */
        inventoryLabel.setText("Inventory: " + inventory);
        progressBarHealth.setValue(hp);
        progressBarOxygen.setValue(oxygen);
        timeLeft.setText(time);
    }

    private JLabel createInventoryLogoLabel() {
        JLabel inventoryLogoLabel = new JLabel() {{
            setSize(50, 50);
        }};
        ImageIcon imageIcon =
                ResourceUtils.getImageIconScaledToLabelSize("images/backpack.png", inventoryLogoLabel);
        inventoryLogoLabel.setIcon(imageIcon);
        inventoryLogoLabel.setBackground(Color.ORANGE);
        return inventoryLogoLabel;
//        mainTextPanel.add(inventoryLogoLabel);
    }

}