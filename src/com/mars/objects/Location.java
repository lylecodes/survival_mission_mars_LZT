package com.mars.objects;

import java.util.Map;

public class Location {
    private String name;
    private Map<String, String> directions;
    private String description;
    private Map<String, String> items;
    private Boolean oxygen;
    private String asciiArt;

    public Location(String name, Map<String, String> directions, String description, Map<String, String> items, Boolean oxygen, String asciiArt) {
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

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
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
