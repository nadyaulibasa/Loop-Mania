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
import unsw.loopmania.weapons.Sword;
import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.game_modes.SurvivalGameMode;
import unsw.loopmania.miscItems.HealthPotion;

public class SurvivalGameModeTest {

    @Test
    public void testConditionsSatisfiedHealthPotionTrue(){
        GameModeStrategy newGM = new SurvivalGameMode();
        List<Item> itemList = new ArrayList<Item>();
        HealthPotion hp1 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assertTrue(newGM.arePurchaseConditionsSatisfied(hp1, itemList));
    }

    public void testConditionsSatisfiedHealthPotionFalse(){
        GameModeStrategy newGM = new SurvivalGameMode();
        List<Item> itemList = new ArrayList<Item>();
        HealthPotion hp1 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        HealthPotion hp2 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        itemList.add(sword);
        itemList.add(hp1);
        
        assertFalse(newGM.arePurchaseConditionsSatisfied(hp2, itemList));
    }

    public void testConditionsSatisfiedNotHealthPotion(){
        GameModeStrategy newGM = new SurvivalGameMode();
        List<Item> itemList = new ArrayList<Item>();
        HealthPotion hp1 = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        itemList.add(hp1);
        
        assertFalse(newGM.arePurchaseConditionsSatisfied(sword, itemList));
    }

    // Test applyPuchaseItemsConditions

    @Test
    public void testCanBuyHealthPotion(){
        GameModeStrategy newGM = new SurvivalGameMode();
        List<Item> availableItems = NewItemsGenerator.getNewListOfItems();
        List<Item> purchasedItems = NewItemsGenerator.getNewListOfAttackItems();
        
        newGM.applyPurchaseItemsConditions(availableItems, purchasedItems);
    
        for (Item item : availableItems){
            assertFalse(item.getIsNotPurchasable().get());
        }
    }

    @Test
    public void testCannotBuyHealthPotion(){
        GameModeStrategy newGM = new SurvivalGameMode();
        List<Item> availableItems = NewItemsGenerator.getNewListOfItems();
        List<Item> purchasedItems = NewItemsGenerator.getNewListOfDefenseItems();

        HealthPotion hp = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        purchasedItems.add(sword);
        purchasedItems.add(hp);

        newGM.applyPurchaseItemsConditions(availableItems, purchasedItems);

        for (Item item : availableItems){
            if (item instanceof HealthPotion){
                assertTrue(item.getIsNotPurchasable().get());
            } else {
                assertFalse(item.getIsNotPurchasable().get());
            }
        }

    }

    @Test
    public void testApplyEquippedItemsCondition(){
        GameModeStrategy newGM = new SurvivalGameMode();
        List<Item> itemsList = NewItemsGenerator.getNewListOfItems();

        assertEquals(itemsList, newGM.applyEquippedItemsConditions(itemsList));
    }

}
