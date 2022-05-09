package com.mars.stats;
public class BoneDensity {

    //fields
    Integer boneDensity = 100; //meant to be understood as a %



    //accessor methods
    public int getBoneDensity() {
        return boneDensity;
    }

    @Override
    public String toString() {
        return "BoneDensity{" +
                "boneDensity=" + boneDensity +
                '}';
    }
}