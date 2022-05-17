package com.mars.puzzle;

import com.mars.gui.alt.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Dialogue {
    private static Container gameContainer = GameFrame.gameContainer;

    public static int popUpDialogue(String dialogue, String titleName){
        Object[] options = { "yes", "no", "cancel" };

        return JOptionPane.showOptionDialog(
                gameContainer,
                dialogue,
                titleName,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public static void popUpDialogueEnd(String dialogue, String titleName) {
        String[] options = { "ok" };

        JOptionPane.showOptionDialog(
                gameContainer,
                dialogue,
                titleName,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
}