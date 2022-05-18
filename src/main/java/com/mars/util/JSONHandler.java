package com.mars.util;

import com.mars.objects.Item;
import com.mars.objects.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class JSONHandler {

    private Map<String, Location> locationMap = new HashMap<>();

    public Map<String,Location> loadLocationMap(){

        try {
            populateLocations();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return locationMap;
    }

    private void populateLocations() throws FileNotFoundException {
        // json simple JSON parser
        JSONParser jsonParser = new JSONParser();

        // Hold location of json file
        String s = "json/rooms.json";

        InputStream inputJSON = getFileFromResourceAsStream(s);

       try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputJSON,"UTF-8"))) {
           Object obj = jsonParser.parse(reader);
           JSONArray locationList = (JSONArray) obj;

           // Iterate through locationList
           locationList.forEach(loc -> addLocation((JSONObject) loc));
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ParseException e) {
           e.printStackTrace();
       }

    }

    private void addLocation(JSONObject locations){
        // Get everything inside location
        JSONObject locationsObject = (JSONObject) locations.get("location");

        // Get name from locationsObject
        String name = (String) locationsObject.get("name");

        // Map to hold directions
        Map<String,String> directions = new HashMap<>();

        // List to hold items in current room
        List<Item> lookitems = new ArrayList<>();

        // Get directions from locationsObject
        Object locArray = locationsObject.get("directions");

        // Cast locArray to a String and Get key and value from locArray
        String[] keyValue = locArray.toString().split(",");

        // set directions
        for (int i = 0; i < keyValue.length; i++){
            // Remove special characters { } " from string
            keyValue[i] = keyValue[i].replaceAll("[{}\"]","");
            // Split key and value
            String[] holder = keyValue[i].split(":");
            // Store key and value in map
            directions.put(holder[0], holder[1]);
        }

        // Get description
        locArray = locationsObject.get("description");
        String description = (String) locArray;

        // Get items and store them in String[]
        locArray = locationsObject.get("items");

        String[] itemsKVPair = locArray.toString().split(",");
        for (int i = 0; i <itemsKVPair.length; i++) {
            itemsKVPair[i] = itemsKVPair[i].replaceAll("[{}\"]","");
            String[] holder = itemsKVPair[i].split(":");
            lookitems.add(new Item(holder[0],holder[1]));
        }

        locArray = locationsObject.get("oxygen");
        String oxygenHolder = (String) locArray;
        boolean oxygen = oxygenHolder.equals("true");

        // Get  ascii art
        locArray = locationsObject.get("asciiArt");
        String ascii = (String) locArray;

        // Get puzzle boolean
        locArray = locationsObject.get("puzzle");
        String puzzleHolder = (String) locArray;
        boolean puzzle = puzzleHolder.equals("true");

        // Update locationMap with new location
        // Key = name of location, Value = new Location with values parsed from locationsObject
        locationMap.put(name, new Location(name, directions, description, lookitems,oxygen,ascii, puzzle));

    }
    public static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = JSONHandler.class.getClassLoader();
        InputStream inputStream = new BufferedInputStream(classLoader.getResourceAsStream(fileName));
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static String getFileContentAsString(String fileName) {
        InputStream inputStream = getFileFromResourceAsStream(fileName);
        String text = null;

        try {
            text = new String(inputStream.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

}