package unsw.loopmania.game_modes;

import java.util.List;

import unsw.loopmania.Item;

public interface GameModeStrategy {
    /**
     * Checks the purchasing conditions
     * @param selectedItem
     * @param itemList
     * @return : true if conditions are satisfied, false otherwise
     */
    public boolean arePurchaseConditionsSatisfied (Item selectedItem, List<Item> itemList);

    /**
     * Applies purchasing conditions to list
     * @param availableItems
     * @param purchasedItems
     * @return : item list with conditions applied
     */
    public List<Item> applyPurchaseItemsConditions(List<Item> availableItems, List<Item> purchasedItems);

    /**
     * Applies equipped items conditions to list
     * @param itemList
     * @return : item list with conditions applied
     */
    public List<Item> applyEquippedItemsConditions(List<Item> itemList);
}
