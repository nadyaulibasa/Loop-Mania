// package unsw.loopmania;

// import java.util.ArrayList;
// import java.util.List;

// import unsw.loopmania.game_modes.GameModeStrategy;

// /**
//  * Shop to buy or sell items in the Hero Castle
//  */
// public class ShopOriginal {

//     List<Item> purchasableItemList;
//     List<Item> purchasedItemList;
//     List<Item> selectedItemList;
//     GameModeStrategy gameMode;
//     Character theCharacter;
//     int totalSelectedItemsCost;

//     /**
//      * Constructor of the class tha accepts the following parameters:
//      * @param gameMode
//      * @param theCharacter
//      */
//     public ShopOriginal(GameModeStrategy gameMode, Character theCharacter) {
//         this.gameMode = gameMode;
//         this.theCharacter = theCharacter;
//         this.totalSelectedItemsCost = 0;
//         this.selectedItemList = new ArrayList<Item>();
//         this.purchasedItemList = new ArrayList<Item>();
//     }

//     /**
//      * Setter -> sets a list of purchasable items
//      * @param itemList
//      */
//     public void setPurchasableItemList(List<Item> itemList){
//         this.purchasableItemList = itemList;
//     }

//     /**
//      * Getter -> gets a list of purchasable items
//      * @return
//      */
//     public List<Item> getPurchasabletemList() {
//         return purchasableItemList;
//     }


//     /**
//      * Getter -> gets a list of purchased items
//      * @return
//      */
//     public List<Item> getPurchasedItemList() {
//         return purchasedItemList;
//     }

//     /**
//      * Getter -> gets a list of selected items
//      * @return
//      */
//     public List<Item> getSelectedItemList() {
//         return selectedItemList;
//     }

//     /**
//      * Getter -> gets the games mode
//      * @return
//      */
//     public GameModeStrategy getGameMode() {
//         return gameMode;
//     }

//     /**
//      * Setter -> sets the game mode
//      */
//     public void setGameMode(GameModeStrategy gameMode) {
//         this.gameMode = gameMode;
//     }

//     /**
//      * Adds selected item to the selectedItemList
//      * @param item
//      * @return
//      */
//     public boolean addSelectedItem(Item item) {
//         if ((theCharacter.getTotalGold() >= item.getItemPrice()) && (theCharacter.getTotalGold() >= this.totalSelectedItemsCost)){
//             if (gameMode.arePurchaseConditionsSatisfied(item, this.purchasedItemList)){
                
//                 selectedItemList.add(item);
//                 this.totalSelectedItemsCost += item.getItemPrice();
//                 return true;
//             }
//         }

//         return false;
//     }

//     /**
//      * Removes selected item from the selectedItemList
//      * @param item
//      */
//     public void removeSelectedItem(Item item) {
//         selectedItemList.remove(item);
//         this.totalSelectedItemsCost -= item.getItemPrice();
//     }

//     /**
//      * Adds purchased item to the purchasedItemList
//      * @param item
//      */
//     public void addPurchasedItem(Item item) {
//         purchasedItemList.add(item);
//     }

//     /**
//      * Clears the list of selected items
//      */
//     public void clearSelectedItemList(){
//         this.selectedItemList.clear();
//         this.totalSelectedItemsCost = 0;
//     }

//     /**
//      * Clears the list of purchased items
//      */
//     public void clearPurchasedItemList(){
//         this.purchasableItemList.clear();
//     }

//     /**
//      * Purchase select items: it goes through the selected items list, adds item to purchased item list,
//      * then updayed purchasable item status, add to character inventory and deduct gold. 
//      * @return : true if it can complete the purchasing
//      */
//     public boolean purchaseItems() {
//         int totalCost = 0;
//         if (theCharacter.getTotalGold() >= this.totalSelectedItemsCost){
//             for (Item item : this.selectedItemList){
//                 totalCost += item.getItemPrice();
//                 this.addPurchasedItem(item);
//                 theCharacter.addItemToInventory(item);
//             }
//             this.updatePurchasableItems();
//             theCharacter.subtractGold(totalCost);
//             this.clearSelectedItemList();
//             return true;
//         }
//         return false;
//     }

//     /**
//      * Updates purchasable items list
//      */
//     public void updatePurchasableItems(){
//         for (Item item : this.purchasedItemList){
//             item.setIsPurchasable(gameMode.arePurchaseConditionsSatisfied(item, this.purchasedItemList) && 
//                                     theCharacter.getTotalGold() >= item.getItemPrice());
//         }
//     }

// }
