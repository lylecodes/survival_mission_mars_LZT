package com.mars.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/*
 * Tests basic functionality of inventory
 */

public class InventoryTest {

    Inventory inventoryTest;
    Item itemTest;
    @Before
    public void setUp(){
        inventoryTest = Inventory.getInstance();
        itemTest = new Item("itemTest","itemDescTest");

    }

    @Test
    public void add() {
        inventoryTest.add(itemTest);
        assertEquals("[itemTest]", inventoryTest.lookItem().toString());
    }

    @Test
    public void getItemDescription() {
        inventoryTest.add(itemTest);
        assertEquals("itemDescTest", inventoryTest.getItemDescription("itemTest"));
    }

    @Test
    public void drop() {
        inventoryTest.add(itemTest);
        inventoryTest.drop("itemTest");
        assertEquals("[itemTest]", inventoryTest.getInventory().toString());
    }
}