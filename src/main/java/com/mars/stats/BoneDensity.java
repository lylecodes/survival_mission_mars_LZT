package com.mars.stats;
public class BoneDensity {

    //fields
    int boneDensity = 100; //meant to be understood as a %, currently hard coded for now



    //accessor methods
    public int getBoneDensity() {
        return boneDensity; //return current bone density value
    }

    public void updateBoneDensityLoss(int subtractValue) {
        if (boneDensity - subtractValue <= 0){
            this.boneDensity = 0;
        }
        else{
            this.boneDensity = boneDensity - subtractValue;
        }
    }

    public void updateBoneDensityGain(int addValue) {
        if (this.boneDensity + addValue >= 100){
            this.boneDensity = 100;
        }
        else{
            this.boneDensity = boneDensity + addValue;
        }
    }

    @Override
    public String toString() {
        return "BoneDensity{" +
                "boneDensity=" + boneDensity +
                '}';
    }
}