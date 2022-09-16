package unsw.loopmania.game_modes;

import java.util.List;

import unsw.loopmania.Item;

/**
 * Standard game mode class
 */
public class StandardGameMode implements GameModeStrategy {

    /**
     * Constructor
     */
    public StandardGameMode() {
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
     * Applies equipped items conditions to list
     * @param itemList
     * @return : item list with conditions applied, in this case, same equipped item list
     */
    @Override
    public List<Item> applyEquippedItemsConditions(List<Item> itemList) {
        return itemList;
    }  
}
