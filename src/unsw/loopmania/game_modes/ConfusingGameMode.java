package unsw.loopmania.game_modes;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import unsw.loopmania.Item;
import unsw.loopmania.NewItemsGenerator;
import unsw.loopmania.rareItems.RareItem;

/**
 * Confusing game mode class
 */
public class ConfusingGameMode implements GameModeStrategy {

    /**
     * Constructor
     */
    public ConfusingGameMode() {
        super();
    }

    /**
     * Check the purchasing conditions.
     * @param selectedItem
     * @param itemList
     * @return : in this case, always true, as there is no conditions
     */
    @Override
    public boolean arePurchaseConditionsSatisfied(Item selectedItem, List<Item> itemList) {
        return true;
    }

    /**
     * Applies purchasing conditions to list
     * @param availableItems
     * @param purchasedItems
     * @return : item list with conditions applied, in this case, same available item list
     */
    @Override
    public List<Item> applyPurchaseItemsConditions(List<Item> availableItems, List<Item> purchasedItems) {
        return availableItems;
    }

    /**
     * Gets a random rare item different from the one already in list
     * @param rareItems
     * @param exceptItem
     * @return : the selected random rare item.
     */
    private RareItem getRandomRareItemWithException(List<Item> rareItems, RareItem exceptItem){
        Random rnd = new Random();
        RareItem rareItem = null;

            do{
                Collections.shuffle(rareItems);
                int value = rnd.nextInt(2);
                rareItem = (RareItem)rareItems.get(value);

            } while (!(exceptItem.getClass().equals(rareItem.getClass())));
        return rareItem;
    }

    /**
     * Applies equipped items conditions to list
     * @param itemList
     * @return : item list with conditions applied
     */
    @Override
    public List<Item> applyEquippedItemsConditions(List<Item> equippedItems){
        List<Item> rareItems = NewItemsGenerator.getNewListOfRareItems();
        RareItem rareItem = null;

        for (Item item : equippedItems){
            if (item instanceof RareItem){
                rareItem = (RareItem)item;
            }
        }

        RareItem rareItemAdd = null;
        if (rareItem != null){
            rareItemAdd = getRandomRareItemWithException(rareItems,rareItem);
            if (rareItemAdd != null){
                equippedItems.add(rareItemAdd);
            }
        }

        return equippedItems;
    }
}
