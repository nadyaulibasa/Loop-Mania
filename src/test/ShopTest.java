package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item;
import unsw.loopmania.NewItemsGenerator;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Shop;
import unsw.loopmania.defenses.Armour;
import unsw.loopmania.defenses.Helmet;
import unsw.loopmania.defenses.Shield;
import unsw.loopmania.weapons.*;
import unsw.loopmania.game_modes.BerserkerGameMode;
import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.game_modes.StandardGameMode;
import unsw.loopmania.game_modes.SurvivalGameMode;
import unsw.loopmania.miscItems.HealthPotion;
import unsw.loopmania.Character;

public class ShopTest {
    
    public Character getCharacter(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        return new Character(pathPos);
    }
    // StandardMode --------------------------------------------------

    @Test
    public void testAddSelectItemDefenseStandardMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        List<Item> itemList = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield1.setIsSelected(true);
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield2.setIsSelected(true);
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        helmet1.setIsSelected(true);
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        armour1.setIsSelected(true);

        itemList.add(shield1);
        itemList.add(shield2);
        itemList.add(helmet1);
        itemList.add(armour1);

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);

        assertTrue(testShop.canSelectItem(shield2, itemList));
        assertTrue(testShop.canSelectItem(helmet1, itemList));
        assertTrue(testShop.canSelectItem(helmet1, itemList));
    }

    @Test
    public void testAddSelectItemHealthPotionStandardMode(){
        Character newCharacter = getCharacter();
        
        newCharacter.addGold(100);
        
        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();

        HealthPotion hp1 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion hp2 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        itemList.add(hp1);
        itemList.add(hp2);
        testShop.setPurchasableItemList(itemList);;
        testShop.addPurchasedItem(hp1);

        hp2.setIsSelected(true);
        assertTrue(testShop.canSelectItem(hp2, itemList));
    }

    @Test
    public void testAddSelectItemWeaponStandardMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();
        itemList.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        itemList.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        itemList.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));

        testShop.setPurchasableItemList(itemList);

        for (Item item : itemList){
            item.setIsSelected(true);
            assertTrue(testShop.canSelectItem(item, itemList));
        }

    }

    // // // Berserker Mode --------------------------------------------------

    @Test
    public void testAddSelectItemDefenseBerserkerMode(){
        Character newCharacter = getCharacter();
        newCharacter.addGold(100);

        GameModeStrategy berserkerMode = new BerserkerGameMode();
        Shop testShop = new Shop(berserkerMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        itemList.add(shield1);
        shield1.setIsSelected(true);
        itemList.add(shield2);
        shield2.setIsSelected(true);
        itemList.add(helmet1);
        helmet1.setIsSelected(true);
        itemList.add(armour1);
        armour1.setIsSelected(true);

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);

        assertFalse(testShop.canSelectItem(shield2, itemList));
        assertFalse(testShop.canSelectItem(helmet1, itemList));
        assertFalse(testShop.canSelectItem(helmet1, itemList));
    }

    @Test
    public void testAddSelectItemHealthPotionBerserkerMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy berserkerMode = new BerserkerGameMode();
        Shop testShop = new Shop(berserkerMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();

        HealthPotion hp1 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion hp2 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        itemList.add(hp1);
        itemList.add(hp2);
        testShop.setPurchasableItemList(itemList);;
        testShop.addPurchasedItem(hp1);
        hp2.setIsSelected(true);
        assertTrue(testShop.canSelectItem(hp2, itemList));
    }

    @Test
    public void testAddSelectItemWeaponBerserkerMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy berserkerMode = new BerserkerGameMode();
        Shop testShop = new Shop(berserkerMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();
        itemList.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        itemList.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        itemList.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));

        testShop.setPurchasableItemList(itemList);

        for (Item item : itemList){
            item.setIsSelected(true);
            assertTrue(testShop.canSelectItem(item, itemList));
        }

    }    

    // // // Survival Mode --------------------------------------------------

    @Test
    public void testAddSelectItemDefenseSurvivalMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy survivalMode = new SurvivalGameMode();
        Shop testShop = new Shop(survivalMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        
        itemList.add(shield1);
        shield1.setIsSelected(true);
        itemList.add(shield2);
        shield2.setIsSelected(true);
        itemList.add(helmet1);
        helmet1.setIsSelected(true);
        itemList.add(armour1);
        armour1.setIsSelected(true);

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);

        assertTrue(testShop.canSelectItem(shield2, itemList));
        assertTrue(testShop.canSelectItem(helmet1, itemList));
        assertTrue(testShop.canSelectItem(helmet1, itemList));
    }

    @Test
    public void testAddSelectItemHealthPotionSurvivalMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy survivalMode = new SurvivalGameMode();
        Shop testShop = new Shop(survivalMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();

        HealthPotion hp1 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion hp2 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        itemList.add(hp1);
        itemList.add(hp2);
        testShop.setPurchasableItemList(itemList);;
        testShop.addPurchasedItem(hp1);
        hp2.setIsSelected(true);

        assertFalse(testShop.canSelectItem(hp2, itemList));
    }

    @Test
    public void testAddSelectItemWeaponSurvivalMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy survivalMode = new SurvivalGameMode();
        Shop testShop = new Shop(survivalMode, newCharacter);
        List<Item> itemList = new ArrayList<Item>();
        itemList.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        itemList.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        itemList.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));

        testShop.setPurchasableItemList(itemList);

        for (Item item : itemList){
            item.setIsSelected(true);
            assertTrue(testShop.canSelectItem(item, itemList));
        }

    }

    // Other methods ///////////////////////////////


  @Test
    public void testGetTotalSell(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        List<Item> itemList = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield1.setIsSelected(true);
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield2.setIsSelected(true);
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        helmet1.setIsSelected(true);
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        armour1.setIsSelected(true);

        itemList.add(shield1);
        itemList.add(shield2);
        itemList.add(helmet1);
        itemList.add(armour1);

        double total = 0;

        for (Item item : itemList){
            total += item.getItemPrice();
        }

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);

        assertEquals(total,testShop.getTotalSell(itemList));
    }

    @Test
    public void testGetTotalPurchase(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        List<Item> itemList = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield1.setIsSelected(true);
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield2.setIsSelected(true);
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        helmet1.setIsSelected(true);
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        armour1.setIsSelected(true);

        itemList.add(shield1);
        itemList.add(shield2);
        itemList.add(helmet1);
        itemList.add(armour1);

        double total = 0;

        for (Item item : itemList){
            total += item.getItemPrice();
        }

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);
        testShop.setPurchasableItemList(itemList);
        assertEquals(total,testShop.getTotalPurchase());
    }

    @Test
    public void testSellInventory(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        List<Item> itemList = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield1.setIsSelected(true);
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield2.setIsSelected(true);
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        helmet1.setIsSelected(true);
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        armour1.setIsSelected(true);

        itemList.add(shield1);
        itemList.add(shield2);
        itemList.add(helmet1);
        itemList.add(armour1);

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);
        testShop.setPurchasableItemList(itemList);
        testShop.sellInventoryItems(itemList);
        assertEquals(0, itemList.size());
    }

    @Test
    public void testPurchaseItems(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        List<Item> itemList = new ArrayList<Item>();
        List<Item> inventory = new ArrayList<Item>();

        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield1.setIsSelected(true);
        Shield shield2 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        shield2.setIsSelected(true);
        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        helmet1.setIsSelected(true);
        Armour armour1 = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        armour1.setIsSelected(true);

        itemList.add(shield1);
        itemList.add(shield2);
        itemList.add(helmet1);
        itemList.add(armour1);

        testShop.setPurchasableItemList(itemList);
        testShop.addPurchasedItem(shield1);
        testShop.setPurchasableItemList(itemList);


        testShop.purchaseItems(inventory, null);


        assertEquals(4, inventory.size());
    }

    @Test
    public void testSetNewPurchasableItems(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        testShop.setNewPurchasableItemList();

        List<Item> itemList = NewItemsGenerator.getNewListOfPurchasableItems();


        assertEquals(itemList.size(), testShop.getPurchasabletemList().size());
    }

    @Test
    public void testClearPurchasedItems(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

    
        Shield shield1 = new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        testShop.addPurchasedItem(shield1);
        assertEquals(1, testShop.getPurchasedItemList().size());
        testShop.clearPurchasedItemList();
        assertEquals(0, testShop.getPurchasedItemList().size());
    }

    @Test
    public void testSetGameMode(){
        Character newCharacter = getCharacter();

        newCharacter.addGold(100);

        GameModeStrategy standardMode = new StandardGameMode();
        Shop testShop = new Shop(standardMode, newCharacter);

        assertEquals(standardMode, testShop.getGameMode());

        GameModeStrategy survivalMode = new SurvivalGameMode();
        testShop.setGameMode(survivalMode);

        assertEquals(survivalMode, testShop.getGameMode());
    }
}
