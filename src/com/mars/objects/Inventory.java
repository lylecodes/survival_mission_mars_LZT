package com.mars.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private static Inventory single_instance = null;
    private List<String> inventory;

    private Inventory(){
        inventory = new ArrayList<>();
    }

    public static Inventory getInstance(){
        if(single_instance == null){
            single_instance = new Inventory();
        }
        return single_instance;
    }

    public void add(String item){
        inventory.add(item);
    }

    public String drop(String item){
        int index = inventory.indexOf(item);
        return inventory.remove(index);
    }

    public void use(String item){
        System.out.println("You used " + item);
    }
    public List<String> getInventory() {
        return inventory;
    }

}
