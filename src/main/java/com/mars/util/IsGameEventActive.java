package com.mars.util;
/*
 * Hanldes all the
 */

import com.mars.controller.GameController;
import com.mars.gui.alt.GameFrame;
import com.mars.objects.Inventory;
import com.mars.objects.Location;
import com.mars.stats.Stats;

import java.util.Map;

public final class IsGameEventActive {
    public static void playerAtGym(
            GameFrame gui,
            Stats playerStats,
            Location currentLocation,
            Map<String, Location> locationMap,
            Inventory inventory,
            String dieTime,
            int minutesToCompleteGame
    ) {
        if (currentLocation.equals(locationMap.get("Gym"))) {
            playerStats.updateCurrentBoneGain(120);
            gui.playerSetup(
                    playerStats.getStats().get("Health"),
                    playerStats.getStats().get("Bone Density"),
                    inventory.getInventory().toString(),
                    TimeCalc.findDifferenceGUI(dieTime, minutesToCompleteGame)
            );
            gui.popUp("You just hit the gym, which restored your bone density");
        }
    }
    public static void playerAtGreenHouse(
            Location currentLocation,
            Audio audio
    ) {
        if ("Green House".equals(currentLocation.getName())) {
            audio.play("lobby.wav");
        }
    }
    public static void playerAtMiddleBuilding(
            Location currentLocation,
            Audio audio
    ){
        if ("Middle Building".equals(currentLocation.getName())) {
            audio.play("lobby.wav");
        }
    }
    public static void playerActivatesGodMode(Stats playerStats, boolean isGodMode){
        if (isGodMode) {
            GameController.setBoneLoss(0);
            GameController.setHealthLoss(0);
            GameController.setGodMode(true);
            playerStats.updateCurrentBoneGain(120);
            playerStats.updateCurrentHealthGain(120);
            GameController.setDieTime(TimerSetUp.timeRun(999));
//            audio.play("developers_song.wav");
        }
    }
}
