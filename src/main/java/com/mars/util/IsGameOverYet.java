package com.mars.util;
/*
 *
 */

import com.mars.gui.alt.GameFrame;
import com.mars.puzzle.GhPuzzle;
import com.mars.puzzle.ReactorPuzzle;
import com.mars.puzzle.SolarPuzzle;
import com.mars.stats.Stats;

public final class IsGameOverYet {

    public static void timeUp(GameFrame gui, String deathTime) {
        if (deathTime.equals("gameOver")) {
            gui.popUp("you ran out of time");
            System.out.println("gameOver");
        } else {
            System.out.println("player still has time: " + deathTime);
        }
    }

    public static void puzzlesSolved(GameFrame gui, Audio audio) {
        if (GhPuzzle.isSolved && SolarPuzzle.isSolved && ReactorPuzzle.isSolved) {
            System.out.println("All puzzles have been solved");
            audio.play("hellyes.wav");
            gui.popUp("You completed all of the puzzles! Amazing!");
            System.exit(0);
        } else {
            System.out.println("not all puzzles have been solved");
        }
    }

    public static void statsAtZero(GameFrame gui, Stats playerStats) {
        if (playerStats.getStats().get("Health") <= 0 || playerStats.getStats().get("Bone Density") <= 0) {
            playAgainPopUp(gui, playerStats);
        }
        else{
            System.out.println("Stats are not at zero");
        }
    }
    // helper method that asks player if he would like to try again
    public static void playAgainPopUp(GameFrame gui, Stats playerStats){
        int response = gui.popUpPlayAgain();

        if (response == 0) {
            playerStats.updateCurrentHealthGain(120);
            playerStats.updateCurrentBoneGain(120);
        } else {
            System.exit(0);
        }
    }
}
