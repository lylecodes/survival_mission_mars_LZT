package com.mars.stats;
public class BoneDensity {

    //fields
    Integer boneDensity = 100; //meant to be understood as a %, currently hard coded for now



    //accessor methods
    public int getBoneDensity() {
        return boneDensity; //return current bone density value
    }

    @Override
    public String toString() {
        return "BoneDensity{" +
                "boneDensity=" + boneDensity +
                '}';
    }
}