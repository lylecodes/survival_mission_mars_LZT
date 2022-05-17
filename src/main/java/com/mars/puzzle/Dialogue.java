package com.mars.puzzle;

import javax.swing.*;

public class Dialogue {
    public static int popUpDialogue(String dialogue, String titleName){
        Object[] options = { "yes", "no", "cancel" };

        return JOptionPane.showOptionDialog(
                null,
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
                null,
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