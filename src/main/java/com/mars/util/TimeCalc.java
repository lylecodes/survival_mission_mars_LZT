package com.mars.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// time calculation method
public class TimeCalc {
    public static void findDifference(String dieTime) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
        // Try Block
        try {
            Date d1 = sdf.parse(timeStamp);
            Date d2 = sdf.parse(dieTime);
            long difference_In_Time = d2.getTime() - d1.getTime();
            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
            long difference_In_Seconds = (difference_In_Time / 1000) % 60;
            System.out.println("Time remaining: " + difference_In_Hours + " hrs, " +
                    difference_In_Minutes + " mins, " +
                    difference_In_Seconds + " secs");
        }
        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String findDifferenceGUI(String dieTime, int timelimit) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(Calendar.getInstance().getTime());
        // Try Block
        StringBuilder sb = new StringBuilder();
        try {
            Date d1 = sdf.parse(timeStamp);
            Date d2 = sdf.parse(dieTime);
            long difference_In_Time = d2.getTime() - d1.getTime();
            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
            long difference_In_Seconds = (difference_In_Time / 1000) % 60;
            if (difference_In_Minutes <= 0 && difference_In_Seconds <= 0){
                sb.append("gameOver");
            }
            else{
                sb.append(difference_In_Minutes + ":" +difference_In_Seconds);
            }
        }
        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}