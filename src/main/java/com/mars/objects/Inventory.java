package com.mars.objects;

import java.util.*;

public class Inventory {
    private static Inventory single_instance = null;
    private List<Item> inventory;

    private Inventory(){
        inventory = new ArrayList<>();
    }

    public static Inventory getInstance(){
        if(single_instance == null){
            single_instance = new Inventory();
        }
        return single_instance;
    }

    public void add(Item item){
        inventory.add(item);
    }

    // looking at item and returning description that followed to inventory from location
    public List<String> lookItem() {
        List<String> names = new ArrayList<>();
        for(Item item : inventory) {
            names.add(item.getName());
        }
        return names;
    }

    // retrieving properties from item in location, adding item & properties to inventory
    public String getItemDescription(String name){
        int index = getItemIndex(name);

        return inventory.get(index).getDescription();
    }

    // index'ing through Items for K,V to move to inventory
    private int getItemIndex(String itemName){
        int index = -1;
        int counter = 0;
        for(Item item: inventory){
            if (item.getName().equals(itemName)){
                index = counter;
            }
            counter++;
        }
        return index;
    }
    // dropping item and acquiring the correct item index to remove from inventory
    public Item drop(String item){
        int dropIndex = getItemIndex(item);
        if (dropIndex < 0) {
            System.out.println("Item does not exist");
            return null;
        }
        return inventory.remove(dropIndex);
    }
    public Item getItem(String item){
        int index = getItemIndex(item);
        if (index < 0) {
            System.out.println("Item does not exist");
            return null;
        }
        return inventory.get(index);
    }


    public void use(Item item){
        System.out.println("You used " + item.getName());
    }
    public List<String> getInventory() {
        List<String> holder = new ArrayList<>();
        for (Item item : inventory) {
            holder.add(item.getName());
        }
        return holder;
    }
}
