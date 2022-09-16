package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.game_modes.GameModeStrategy;

/**
 * Shop to buy or sell items in the Hero Castle
 */
public class Shop {

    private List<Item> purchasableItemList;
    private List<Item> purchasedItemList;

    private GameModeStrategy gameMode;
    private Character theCharacter;
    private double totalPurchase;
    private double totalSell;

    /**
     * Constructor of the class tha accepts the following parameters:
     * @param gameMode
     * @param theCharacter
     */
    public Shop(GameModeStrategy gameMode, Character theCharacter) {
        this.gameMode = gameMode;
        this.theCharacter = theCharacter;
        this.totalPurchase = 0;
        this.purchasedItemList = new ArrayList<Item>();
    }

    /**
     * Setter -> sets a list of purchasable items
     * @param itemList
     */
    public void setNewPurchasableItemList(){
        this.purchasableItemList = NewItemsGenerator.getNewListOfPurchasableItems();
    }

    /**
     * Getter -> gets a list of purchasable items
     * @return
     */
    public List<Item> getPurchasabletemList() {
        return purchasableItemList;
    }

    public void setPurchasableItemList(List<Item> itemList){
        this.purchasableItemList = itemList;
    }

    /**
     * Getter -> gets a list of purchased items
     * @return
     */
    public List<Item> getPurchasedItemList() {
        return purchasedItemList;
    }


    /**
     * Getter -> gets the games mode
     * @return
     */
    public GameModeStrategy getGameMode() {
        return gameMode;
    }

    /**
     * Setter -> sets the game mode
     */
    public void setGameMode(GameModeStrategy gameMode) {
        this.gameMode = gameMode;
    }


    /**
     * Adds purchased item to the purchasedItemList
     * @param item
     */
    public void addPurchasedItem(Item item) {
        purchasedItemList.add(item);
    }


    /**
     * Clears the list of purchased items
     */
    public void clearPurchasedItemList(){
        this.purchasedItemList.clear();
    }

    /**
     * Purchase select items: it goes through the list filtering the selected items, then adds item to purchased item list, and
     * adds to character inventory and deduct gold. 
     * @param inventory
     * @param loopManiaWorldController
     */
    public void purchaseItems(List<Item> inventory, LoopManiaWorldController loopManiaWorldController) {
        for (Item item : this.purchasableItemList){
            if (item.getIsSelected().get()){
                item.setIsSelected(false);
                if (loopManiaWorldController != null){
                    loopManiaWorldController.onloadInventory();
                }
                purchasedItemList.add(item);
                theCharacter.subtractGold(item.getItemPrice());
                inventory.add(item);
            }
        }
    }

    /**
     * 
     * @param inventory
     */
    public void sellInventoryItems(List<Item> inventory){
        List<Item> tempInventory = new ArrayList<Item>();
        for (Item item : inventory){
            tempInventory.add(item);
        }
        for (Item item : tempInventory){
            if (item.getIsSelected().get()){
                item.setIsSelected(false);
                theCharacter.addGold((int)item.getItemPrice());
                inventory.remove(item);
            }
        }
    }

    /**
     * Checks if can select an item in the shop to purchase
     * @param item
     * @param selectedItems
     * @return : true if can select, false if not
     */
    public boolean canSelectItem(Item item, List<Item> selectedItems){

        boolean condition = (gameMode.arePurchaseConditionsSatisfied(item, selectedItems) &&
                             gameMode.arePurchaseConditionsSatisfied(item, this.getPurchasedItemList()) &&
                                this.getTotalPurchase() <= this.getTotalGold() &&
                                (item.getIsSelected().get()));
        return condition;
    }

    /**
     * Gets the total value of gold if selling the selected items
     * @param inventoryList
     * @return : the total value of gold if selling the selected items
     */
    public double getTotalSell(List<Item> inventoryList){
        this.totalSell = 0;

        for (Item item : inventoryList){
            if (item.getIsSelected().get()){
                totalSell += item.getItemPrice();
            }
        }

        return this.totalSell;
    }

    /**
     * Gets the total value of gold if purchasing the selected items
     * @return : the total value of gold if purchasing the selected items
     */
    public double getTotalPurchase(){
        this.totalPurchase = 0;

        for (Item item : this.purchasableItemList){
            if (item.getIsSelected().get()){
                totalPurchase += item.getItemPrice();
            }
        }

        return this.totalPurchase;
    }

    /**
     * Getter - the total value gold available
     * @return : the total value gold available
     */
    public double getTotalGold(){
        return theCharacter.getTotalGold();
    }

}
