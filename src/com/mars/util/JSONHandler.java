package com.mars.util;

import com.mars.objects.Item;
import com.mars.objects.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        JSONParser jsonParser = new JSONParser();
        String s = "json/rooms.json";

        InputStream inputJSON = getFileFromResourceAsStream(s);

       try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputJSON,"UTF-8"))) {
           Object obj = jsonParser.parse(reader);
           JSONArray locationList = (JSONArray) obj;
           locationList.forEach(loc -> addLocation((JSONObject) loc));
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ParseException e) {
           e.printStackTrace();
       }

//        // Read from json file
//        try(FileReader reader = new FileReader("data/rooms.json")) {
//            // Parse json file
//            Object obj = jsonParser.parse(reader);
//
//            // Cast to JSONArray to make obj iterable
//            JSONArray locationList = (JSONArray) obj;
//
//            // Iterate through each item in locationList and pass item to addLocation()
//            locationList.forEach(loc -> addLocation((JSONObject) loc));
//
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
    }

    private void addLocation(JSONObject locations){
        // Get everything inside location
        JSONObject locationsObject = (JSONObject) locations.get("location");

        String name = (String) locationsObject.get("name");

        // Map to hold directions
        Map<String,String> directions = new HashMap<>();
//        Map<String,String> lookitems = new HashMap<>();
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
//        System.out.println(locArray);
        String[] itemsKVPair = locArray.toString().split(",");
        for (int i = 0; i <itemsKVPair.length; i++) {
            itemsKVPair[i] = itemsKVPair[i].replaceAll("[{}\"]","");
//            System.out.println(itemsKVPair[i]);
            String[] holder = itemsKVPair[i].split(":");
           // lookitems.put(holder[0], holder[1]);
            lookitems.add(new Item(holder[0],holder[1]));
//            System.out.println(Arrays.toString(holder));
        }

        locArray = locationsObject.get("oxygen");
        String oxygenHolder = (String) locArray;
        boolean oxygen = oxygenHolder.equals("true");

        // Get  ascii art
        locArray = locationsObject.get("asciiArt");
        String ascii = (String) locArray;

        locArray = locationsObject.get("puzzle");
        String puzzleHolder = (String) locArray;
        boolean puzzle = puzzleHolder.equals("true");

        // Update locationMap with new location
        // Key = name of location, Value = new Location with values parsed from locationsObject
        locationMap.put(name, new Location(name, directions, description, lookitems,oxygen,ascii, puzzle));

    }
    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = JSONHandler.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }


}