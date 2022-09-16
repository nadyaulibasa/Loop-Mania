package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Gold;
import unsw.loopmania.Inventory;
import unsw.loopmania.Item;
import unsw.loopmania.defenses.Shield;
import unsw.loopmania.weapons.Sword;


public class InventoryTest {
    @Test
    public void selectItemTest(){
        Inventory inventory = new Inventory();
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Item> itemList = new ArrayList<Item>();
        
        itemList.add(shield);

        inventory.selectItem(shield);
    
        assertEquals(itemList, inventory.getSelectedItemList());
    }

    @Test
    public void deselectItemTest(){
        Inventory inventory = new Inventory();
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Item> itemList = new ArrayList<Item>();
        
        itemList.add(shield);

        inventory.selectItem(shield);
        
        assertEquals(itemList, inventory.getSelectedItemList());

        inventory.deselectItem(shield);
        itemList.remove(shield);

        assertEquals(itemList, inventory.getSelectedItemList());
    }

    @Test
    public void equipItemTest(){
        Inventory inventory = new Inventory();
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Item> itemList = new ArrayList<Item>();
        
        itemList.add(shield);

        inventory.equipItem(shield);
    
        assertEquals(itemList, inventory.getEquippedItemList());
    }

    @Test
    public void unequipItemTest(){
        Inventory inventory = new Inventory();
        Shield shield = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        List<Item> itemList = new ArrayList<Item>();
        
        itemList.add(shield);

        inventory.unequipItem(shield);
    
        assertEquals(itemList, inventory.getUnequippedItemList());
    }

    @Test
    public void sellItemTest(){
        Inventory inventory = new Inventory();
        Sword sword= new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Gold gold = new Gold();
        
        double shieldValue = sword.getItemPrice();
        
        inventory.selectItem(sword);

        inventory.sellItem(gold);
    
        assertEquals(shieldValue, gold.getGold());
    }

}
