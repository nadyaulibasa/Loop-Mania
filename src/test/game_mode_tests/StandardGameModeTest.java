package test.game_mode_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Item;
import unsw.loopmania.NewItemsGenerator;
import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.game_modes.StandardGameMode;

public class StandardGameModeTest {
    @Test
    public void testConditionsSatisfied(){
        GameModeStrategy newGM = new StandardGameMode();

        assertTrue(newGM.arePurchaseConditionsSatisfied(null, null));
    }

    @Test
    public void testApplyPurchaseItemsCondition(){
        GameModeStrategy newGM = new StandardGameMode();
        List<Item> availableItems = NewItemsGenerator.getNewListOfItems();
        List<Item> purchasedItems = NewItemsGenerator.getNewListOfItems();

        newGM.applyPurchaseItemsConditions(availableItems, purchasedItems);
        
        for (Item item : availableItems){
            assertFalse(item.getIsNotPurchasable().get());
        }
    }

    @Test
    public void testApplyEquippedItemsCondition(){
        GameModeStrategy newGM = new StandardGameMode();
        List<Item> itemsList = NewItemsGenerator.getNewListOfItems();

        assertEquals(itemsList, newGM.applyEquippedItemsConditions(itemsList));
    }
}
