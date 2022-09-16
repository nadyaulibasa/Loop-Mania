package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.defenses.Defense;
import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.miscItems.HealthPotion;
import unsw.loopmania.weapons.*;

/**
 * Inventory class
 */
public class Inventory {

    private List<Item> equippedItemList;
    private List<Item> unequippedItemList;
    private List<Item> selectedItemList;
    private GameModeStrategy gameMode;
    
    /**
     * Constructor
     */
    public Inventory() {
        this.equippedItemList = new ArrayList<Item>();
        this.unequippedItemList = new ArrayList<Item>();
        this.selectedItemList = new ArrayList<Item>();
        this.gameMode = null;
    }
    
    /**
     * Getter
     * @return : game mode
     */
    public GameModeStrategy getGameMode() {
        return gameMode;
    }

    /**
     * Setter - sets game mode
     * @param gameMode
     */
    public void setGameMode(GameModeStrategy gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Getter
     * @return : the equipped item list
     */
    public List<Item> getEquippedItemList() {
        List<Item> newequippedItemList= new ArrayList<Item>();

        for (Item item : this.equippedItemList){
            newequippedItemList.add(item);
        }

        if (gameMode != null) {
            gameMode.applyEquippedItemsConditions(newequippedItemList);
        }

        return newequippedItemList;
    }

    /**
     * Getter
     * @return : the unequipped item list
     */
    public List<Item> getUnequippedItemList() {
        return this.unequippedItemList;
    }

    /**
     * Getter
     * @return : the select item list
     */
    public List<Item> getSelectedItemList() {
        return this.selectedItemList;
    }

    /**
     * Adds item to purchased item list
     * @return : the select item list
     */
    public void addPurchasedItem(Item item){
        this.unequippedItemList.add(item);
    }

    /**
     * Adds an item to selected item list
     * @param item
     */
    public void selectItem(Item item){
        this.selectedItemList.add(item);
    }

    /**
     * Removes an item from selected item list
     * @param item
     */
    public void deselectItem(Item item){
        this.selectedItemList.remove(item);
    }

    /**
     * Adds an item to equipped item list
     * @param item
     */
    public void equipItem(Item item){
        this.equippedItemList.add(item);
        this.unequippedItemList.remove(item);
        if(this.selectedItemList.contains(item)){
            this.selectedItemList.remove(item);
        }
    }

    /**
     * Removes an item from equipped item list
     * @param item
     */
    public void unequipItem(Item item){
        this.equippedItemList.remove(item);
        this.unequippedItemList.add(item);
        if(this.selectedItemList.contains(item)){
            this.selectedItemList.remove(item);
        }
    }
    
    /**
     * Sells item: removes card from inventory and adds gold to character 
     * @param gold
     */
    public void sellItem(Gold gold){
        for (Item item : this.selectedItemList){
            gold.addGold((int)item.getItemPrice());
            selectedItemList.remove(item);
            unequippedItemList.remove(item);
        }
        this.selectedItemList.clear();
    }

    /**
     * Getter
     * @return : returns equipped weapon
     */
    public List<Weapon> getEquippedWeapon() {
        List<Weapon> weapons = new ArrayList<Weapon>();
        for (Item i : this.getEquippedItemList()) {
            if (i instanceof Weapon) {
                weapons.add((Weapon)i);
            }
        }
        return weapons;
    }


    /**
     * Getter
     * @return : returns equipped defense
     */
    public List<Defense> getEquippedDefense() {
        List<Defense> defenses = new ArrayList<Defense>();
        for (Item i : this.getEquippedItemList()) {
            if (i instanceof Defense) {
                defenses.add((Defense)i);
            }
        }
        return defenses;
    }

    /**
     * Adds item to the unequipped item list
     * @param item
     */
    public void addToUnequippedItemList(Item item) {
        unequippedItemList.add(item);
    }

    /**
     * Getter
     * @return : returns HealthPotion item
     */
    public HealthPotion getPotion() {
        for (Item e : unequippedItemList) {
            if (e instanceof HealthPotion) {
                return ((HealthPotion) e);
            }
        }
        return null;
    }
}
