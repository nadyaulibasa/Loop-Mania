package unsw.loopmania.game_modes;

import java.util.List;

import unsw.loopmania.Item;
import unsw.loopmania.defenses.Defense;

/**
 * Berserker game mode class
 */
public class BerserkerGameMode implements GameModeStrategy{
    
    private final int MAX_PURCHASED_DEFENSE_GEAR = 1;

    /**
     * Constructor
     */
    public BerserkerGameMode() {
        super();
    }

    /**
     * Check the purchasing conditions.
     * @param selectedItem
     * @param itemList
     * @return : true is they met the conditions, false otherwise
     */
    @Override
    public boolean arePurchaseConditionsSatisfied(Item selectedItem, List<Item> itemList) {
        if (!(selectedItem instanceof Defense)){
            return true;
        } else {
            int counter = 0;

            for (Item item : itemList) {
                if (item instanceof Defense){
                    counter++;
                }
            }
            
            if (counter < MAX_PURCHASED_DEFENSE_GEAR){
                return true;
            }
        }

        return false;
    }

    /**
     * Counts the number items of type defense are in the list
     * @param itemList
     * @return : the number items of type defense are in the list
     */
    private int numberOfDefenseItemsInList(List<Item> itemList){
        int counter = 0;

        for (Item item : itemList) {
            if (item instanceof Defense){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Applies purchasing conditions to list
     * @param availableItems
     * @param purchasedItems
     * @return : item list with conditions applied
     */
    @Override
    public List<Item> applyPurchaseItemsConditions(List<Item> availableItems, List<Item> purchasedItems) {
        
        if (this.numberOfDefenseItemsInList(purchasedItems) >= this.MAX_PURCHASED_DEFENSE_GEAR){
            for (Item item : availableItems) {
                if (item instanceof Defense){
                    item.setIsNotPurchasable(true);
                }
            }
        }

        return availableItems;
    }

    /**
     * Applies equipped items conditions to list
     * @param itemList
     * @return : item list with conditions applied, in this case, the same equipped item list
     */
    @Override
    public List<Item> applyEquippedItemsConditions(List<Item> itemList) {
        return itemList;
    }  
}
