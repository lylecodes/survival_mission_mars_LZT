package com.mars.gui.alt;

import com.mars.controller.GameController;

import java.awt.*;
import javax.swing.*;

class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            GameFrame gameFrame = new GameFrame();
            gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            gameFrame.setVisible(true);
            GameController gameController = new GameController(gameFrame);
        });
    }
}