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
import unsw.loopmania.game_modes.ConfusingGameMode;
import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.rareItems.TheOneRing;
import unsw.loopmania.weapons.Sword;

public class ConfusingGameModeTest {
    @Test
    public void testConditionsSatisfied(){
        GameModeStrategy newGM = new ConfusingGameMode();

        assertTrue(newGM.arePurchaseConditionsSatisfied(null, null));
    }

    @Test
    public void testApplyPurchaseItemsCondition(){
        GameModeStrategy newGM = new ConfusingGameMode();
        List<Item> availableItems = NewItemsGenerator.getNewListOfItems();
        List<Item> purchasedItems = NewItemsGenerator.getNewListOfItems();

        newGM.applyPurchaseItemsConditions(availableItems, purchasedItems);
        
        for (Item item : availableItems){
            assertFalse(item.getIsNotPurchasable().get());
        }
    }

    @Test
    public void testApplyEquippedItemsConditionWithEquippedRareItem(){
        GameModeStrategy newGM = new ConfusingGameMode();
        List<Item> equippedItems = new ArrayList<Item>();
        TheOneRing tor = new TheOneRing(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        equippedItems.add(tor);

        assertEquals(2, newGM.applyEquippedItemsConditions(equippedItems).size());
    }

    @Test
    public void testApplyEquippedItemsConditionWithoutEquippedRareItem(){
        GameModeStrategy newGM = new ConfusingGameMode();
        List<Item> equippedItems = new ArrayList<Item>();
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        equippedItems.add(sword);

        assertEquals(1, newGM.applyEquippedItemsConditions(equippedItems).size());
    }
}
