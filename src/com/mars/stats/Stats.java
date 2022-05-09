package com.mars.stats;

import java.util.HashMap;


public class Stats {
    //fields
    HashMap<String,Integer> stats;
    BoneDensity bD = new BoneDensity();
    Health playerHealth = new Health();


    public Stats(){
        stats = new HashMap<>();
    }
    //method to populate current stats
    public HashMap<String,Integer> getStats(){

        stats.put("Bone Density", bD.getBoneDensity());
        stats.put("Health", playerHealth.getHealth());
        return stats;
    }
    @Override
    public String toString() {
        return "Stats{" +
                "stats=" + stats +
                ", bD=" + bD +
                ", playerHealth=" + playerHealth +
                '}';
    }
}
