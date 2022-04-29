package com.mars.util;

import com.mars.objects.Location;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

        try(FileReader reader = new FileReader("data/rooms.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray locationList = (JSONArray) obj;
            //System.out.println(locationList);

            locationList.forEach(loc -> addLocation((JSONObject) loc));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void addLocation(JSONObject locations){
        JSONObject locationsObject = (JSONObject) locations.get("location");
        String name = (String) locationsObject.get("name");
        Map<String,String> directions = new HashMap<>();


        Object locArray = locationsObject.get("directions");

        String[] keyValue = locArray.toString().split(",");
        // setting directions
        for (int i = 0; i < keyValue.length; i++){
            keyValue[i] = keyValue[i].replaceAll("[{}\"]","");
            String[] holder = keyValue[i].split(":");
            directions.put(holder[0], holder[1]);
        }

        locArray = locationsObject.get("description");
        String description = (String) locArray;

        locArray = locationsObject.get("items");
        String items = locArray.toString().replaceAll("\\[", ""). replaceAll("\\]","");
        String[] itemsArr = items.split(",");
        for (int i = 0; i < itemsArr.length; i++){
            itemsArr[i] = itemsArr[i].replaceAll("[\"]","");
        }

        // might not need
        locArray = locationsObject.get("oxygen");

        locArray = locationsObject.get("asciiArt");
        String ascii = (String) locArray;

        locationMap.put(name, new Location(name,directions, description,itemsArr,false,ascii));

    }

}