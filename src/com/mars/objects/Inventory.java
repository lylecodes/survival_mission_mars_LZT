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

    public boolean drop(Item item){
        return inventory.remove(item);
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
