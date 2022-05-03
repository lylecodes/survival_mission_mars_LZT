package com.mars.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private Map<String, String> directions;
    private String description;
    private List<Item> items;
    private Boolean oxygen;



    private String asciiArt;

    public Location(String name, Map<String, String> directions, String description, List<Item> items, Boolean oxygen, String asciiArt) {
        setName(name);
        setDirections(directions);
        setDescription(description);
        setItems(items);
        setOxygen(oxygen);
        setAsciiArt(asciiArt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public void setDirections(Map<String, String> directions) {
        this.directions = directions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }

    // removing item from location, but enabling description to port along with it
    public Item removeItem(String itemName) {
        int nameIndex = getItemIndex(itemName);
        return items.remove(nameIndex);
    }
    // adding a dropped item into the room where dropped, while retaining item properties
    public void addItem(Item addDropped) {
        items.add(addDropped);
    }

    public String getItemName(String name){
        int index = getItemIndex(name);
        return items.get(index).getName();
    }

    public String getItemDescription(String name){
        int index = getItemIndex(name);

        return items.get(index).getDescription();
    }

    private int getItemIndex(String itemName){
        int index = 0;
        int counter = 0;
        for(Item item: items){
            if (item.getName().equals(itemName)){
                index = counter;
            }
            counter++;
        }
        return index;
    }

    public List<String>  getItemNames(){
        List<String> names = new ArrayList<>();
        for(Item item : items){
            names.add(item.getName());
        }
        return names;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getOxygen() {
        return oxygen;
    }

    public void setOxygen(Boolean oxygen) {
        this.oxygen = oxygen;
    }

    public String getAsciiArt() {
        return asciiArt;
    }

    public void setAsciiArt(String asciiArt) {
        this.asciiArt = asciiArt;
    }
}
