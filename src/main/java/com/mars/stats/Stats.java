package com.mars.stats;

import java.util.HashMap;


public class Stats {
    //fields
    HashMap<String,Integer> stats; //hashmap to sore k:v pairs for stats
    BoneDensity bD = new BoneDensity();
    Health playerHealth = new Health();

    //constructor
    public Stats(){
        stats = new HashMap<>();
    }
    //method to populate current stats with their respective values (currently hard coded)
    public HashMap<String,Integer> getStats(){

        stats.put("Bone Density", bD.getBoneDensity());
        stats.put("Health", playerHealth.getHealth());
        return stats;
    }
    public void updateCurrentBoneLoss(int boneLoss){
        bD.updateBoneDensityLoss(boneLoss);
    }
    public void updateCurrentBoneGain(int boneGain){
        bD.updateBoneDensityGain(boneGain);
    }

    public void updateCurrentHealthLoss(int healthLoss){
        playerHealth.updateHealthLoss(healthLoss);
    }

    public void updateCurrentHealthGain(int healthGain){
        playerHealth.updateHealthGain(healthGain);
    }

    //for help in debugging
    @Override
    public String toString() {
        return "Stats{" +
                "stats=" + stats +
                ", bD=" + bD +
                ", playerHealth=" + playerHealth +
                '}';
    }
}
