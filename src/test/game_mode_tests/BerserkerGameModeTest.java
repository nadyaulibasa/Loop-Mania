package test.game_mode_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item;
import unsw.loopmania.NewItemsGenerator;
import unsw.loopmania.defenses.Defense;
import unsw.loopmania.defenses.Helmet;
import unsw.loopmania.defenses.Shield;
import unsw.loopmania.weapons.Sword;
import unsw.loopmania.game_modes.BerserkerGameMode;
import unsw.loopmania.game_modes.GameModeStrategy;

public class BerserkerGameModeTest {

    @Test
    public void testConditionsSatisfiedTrue(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> purchasedItems = new ArrayList<Item>();
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));

        assertTrue(newGM.arePurchaseConditionsSatisfied(helmet, purchasedItems));
    }

    @Test
    public void testConditionsSatisfiedFalse(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> purchasedItems = new ArrayList<Item>();
        Shield shield = new Shield(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        
        
        purchasedItems.add(shield);

        assertFalse(newGM.arePurchaseConditionsSatisfied(helmet, purchasedItems));
    }

    @Test
    public void testConditionsSatisfiedNotDenfense(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> purchasedItems = new ArrayList<Item>();
        Shield shield = new Shield(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        Sword sword = new Sword(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        
        
        purchasedItems.add(shield);

        assertTrue(newGM.arePurchaseConditionsSatisfied(sword, purchasedItems));
    }


    // Test applyPuchaseItemsConditions

    @Test
    public void testCanBuyDefenseItem(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> availableItems = NewItemsGenerator.getNewListOfItems();
        List<Item> purchasedItems = NewItemsGenerator.getNewListOfAttackItems();
        
        newGM.applyPurchaseItemsConditions(availableItems, purchasedItems);
   
        for (Item item : availableItems){
            assertFalse(item.getIsNotPurchasable().get());
        }
    }

    @Test
    public void testCannotBuyDefenseItem(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> availableItems = NewItemsGenerator.getNewListOfItems();
        List<Item> purchasedItems = new ArrayList<Item>();

        Helmet helmet1 = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));

        purchasedItems.add(helmet1);

        newGM.applyPurchaseItemsConditions(availableItems, purchasedItems);

        for (Item item : availableItems){
            if (item instanceof Defense){
                assertTrue(item.getIsNotPurchasable().get());
            } else {
                assertFalse(item.getIsNotPurchasable().get());
            }
        }

    }

    @Test
    public void testApplyEquippedItemsCondition(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> itemsList = NewItemsGenerator.getNewListOfItems();

        assertEquals(itemsList, newGM.applyEquippedItemsConditions(itemsList));
    }
    
}
