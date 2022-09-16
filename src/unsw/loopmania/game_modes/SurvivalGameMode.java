package unsw.loopmania.game_modes;

import java.util.List;

import unsw.loopmania.Item;
import unsw.loopmania.miscItems.HealthPotion;

/**
 * Survival game mode class
 */
public class SurvivalGameMode implements GameModeStrategy{
    
    private final int MAX_PURCHASED_POTIONS = 1;
    
    /**
     * Constructor
     */
    public SurvivalGameMode() {
        super();
    }
    
    /**
     * Check the purchasing conditions.
     * @param selectedItem,itemList
     * @param itemList
     * @return : true is they met the conditions, false otherwise
     */
    @Override
    public boolean arePurchaseConditionsSatisfied(Item selectedItem, List<Item> itemList) {
        if (!(selectedItem instanceof HealthPotion)){
            return true;
        } else {
            int counter = 0;

            for (Item item : itemList) {
                if (item instanceof HealthPotion){
                    counter++;
                }
            }
            
            if (counter < MAX_PURCHASED_POTIONS){
                return true;
            }
        }

        return false;
    }

    /**
     * Counts the number of health potions in list
     * @param itemList
     * @return : the number of health potions in list
     */
    private int numberOfHealthPotionsInList(List<Item> itemList){
        int counter = 0;

        for (Item item : itemList) {
            if (item instanceof HealthPotion){
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
        
        if (this.numberOfHealthPotionsInList(purchasedItems) >= this.MAX_PURCHASED_POTIONS){
            for (Item item : availableItems) {
                if (item instanceof HealthPotion){
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
