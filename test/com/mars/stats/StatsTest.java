package com.mars.stats;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
/*
 * Right Bicep
 *
Right – Are the results right ? Are the results correct?
B – are all the boundary conditions correct? Are all the boundary conditions correct?
I – can you check the inverse relationships? Can you check the inverse relationships?
C – can you cross-check results using other means? Can you cross-check results using other means?
E – can you force error conditions to happen? Can you force error conditions to happen?
P – are performance characteristics within bounds?
Does it meet the performance requirements?
 *
 */
public class StatsTest {

    public Stats statTest;
    @Before
    public void setUp(){
        statTest = new Stats();
    }

    @Test
    public void getStats() {
        int health = statTest.getStats().get("Health");
        int boneDensity = statTest.getStats().get("Bone Density");
        assertEquals(100 ,health);
        assertEquals(100 ,boneDensity);
    }

    @Test
    public void updateCurrentBoneLoss() {
        statTest.updateCurrentBoneLoss(30);
        int boneDensity = statTest.getStats().get("Bone Density");
        assertEquals(70 ,boneDensity);
    }

    @Test
    public void updateCurrentBoneLossShouldNotBeBelowZero() {
        statTest.updateCurrentBoneLoss(120);
        int boneDensity = statTest.getStats().get("Bone Density");
        assertEquals(0 ,boneDensity);
    }


    @Test
    public void updateCurrentBoneGain() {
        statTest.updateCurrentBoneLoss(30);
        statTest.updateCurrentBoneGain(10);
        int boneDensity = statTest.getStats().get("Bone Density");
        assertEquals(80 ,boneDensity);
    }

    @Test
    public void updateCurrentBoneGainShouldNotBeAbove100() {
        statTest.updateCurrentBoneLoss(30);
        statTest.updateCurrentBoneGain(130);
        int boneDensity = statTest.getStats().get("Bone Density");
        assertEquals(100 ,boneDensity);
    }

    @Test
    public void updateCurrentHealthLoss() {
        statTest.updateCurrentHealthLoss(40);
        int health = statTest.getStats().get("Health");
        assertEquals(60, health);
    }

    @Test
    public void updateCurrentHealthLossShouldNotGoBelow0() {
        statTest.updateCurrentHealthLoss(140);
        int health = statTest.getStats().get("Health");
        assertEquals(0, health);
    }

    @Test
    public void updateCurrentHealthGainShouldNotGoAbove100() {
        statTest.updateCurrentHealthLoss(40);
        statTest.updateCurrentHealthGain(130);
        int health = statTest.getStats().get("Health");
        assertEquals(100, health);
    }

}