package com.mars.objects;

public class Item {
    private String name;
    private String description;

    public Item(String name, String description){
        setDescription(description);
        setName(name);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
