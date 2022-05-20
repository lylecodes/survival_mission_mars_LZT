package com.mars.util;
/*
 *
 */

public class TimerSetUp {
    public static String timeRun(int minutes){
        long delay = minutes * 60000L;                          // change when done testing    --  sets the length of delay
        long start = System.currentTimeMillis()/1000;       // marks start time of game, reduces from millisecs to secs
        long markDelay = delay/1000;                        // creates variable for delay, reduces from millisecs to secs
        long endTime = start + markDelay;                   // marks endTime of game
        String currentTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (start*1000));   // formats currentTime to SDF
        String dieTime = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (endTime*1000));     // formats endTime to SDF
        return dieTime;
    }
}
