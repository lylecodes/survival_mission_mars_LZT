package com.mars.util;
import com.mars.display.Display;
/*
 * Reads map for player
 */

public class MapReader {

    // BUSINESS METHODS
    public static void read(String location){
        Display display = new Display();
        display.readMap("ASCII/map", location);
    }
}
