package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.defenses.Armour;
import unsw.loopmania.defenses.Defense;
import unsw.loopmania.defenses.Helmet;
import unsw.loopmania.defenses.Shield;
import unsw.loopmania.miscItems.HealthPotion;
import unsw.loopmania.rareItems.Anduril;
import unsw.loopmania.rareItems.TheOneRing;
import unsw.loopmania.rareItems.TreeStump;
import unsw.loopmania.rareItems.RareItem;
import unsw.loopmania.weapons.Staff;
import unsw.loopmania.weapons.Weapon;
import unsw.loopmania.weapons.Stake;
import unsw.loopmania.weapons.Sword;

public class NewItemsGenerator {

    // List<Item> itemsList;

    // public NewItemsGenerator() {
    //     this.itemsList = new ArrayList<Item>();
    // }

    public static List<Item> getNewListOfItems(){
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);

        List<Item> itemsList = new ArrayList<Item>();
        itemsList.add(new Sword(x, y));
        itemsList.add(new Staff(x, y));
        itemsList.add(new Stake(x, y));
        itemsList.add(new Armour(x, y));
        itemsList.add(new Helmet(x, y));
        itemsList.add(new Shield(x, y));
        itemsList.add(new HealthPotion(x, y));
        itemsList.add(new Anduril(x, y));
        itemsList.add(new TheOneRing(x, y));
        itemsList.add(new TreeStump(x, y));

        return itemsList;
    }


    public static List<Item> getNewListOfPurchasableItems(){
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);

        List<Item> itemsList = new ArrayList<Item>();
        itemsList.add(new Sword(x, y));
        itemsList.add(new Staff(x, y));
        itemsList.add(new Stake(x, y));
        itemsList.add(new Armour(x, y));
        itemsList.add(new Helmet(x, y));
        itemsList.add(new Shield(x, y));
        itemsList.add(new HealthPotion(x, y));

        return itemsList;
    }

    public static List<Item> getNewListOfDefenseItems(){
        List<Item> itemsList = new ArrayList<Item>();

        for (Item item : getNewListOfItems()){
            if (item instanceof Defense){
                itemsList.add(item);
            }
        }

        return itemsList;
    }
    
    public static List<Item> getNewListOfAttackItems(){
        List<Item> itemsList = new ArrayList<Item>();

        for (Item item : getNewListOfItems()){
            if (item instanceof Weapon){
                itemsList.add(item);
            }
        }

        return itemsList;
    }

    public static List<Item> getNewListOfRareItems(){
        List<Item> itemsList = new ArrayList<Item>();

        for (Item item : getNewListOfItems()){
            if (item instanceof RareItem){
                itemsList.add(item);
            }
        }

        return itemsList;
    }
    
    public static Item getNewItem(Class<?> c, SimpleIntegerProperty x, SimpleIntegerProperty y){

        if (c == Sword.class){
            return new Sword(x, y);

        } else if (c == Staff.class){
            return new Staff(x, y);

        } else if (c == Stake.class){
            return new Stake(x, y);

        } else if (c == Armour.class){
            return new Armour(x, y);

        } else if (c == Helmet.class){
            return new Helmet(x, y);

        } else if (c == Shield.class){
            return new Shield(x, y);

        } else if (c == HealthPotion.class){
            return new HealthPotion(x, y);

        } else if (c == Anduril.class){
            return new Anduril(x, y); 

        } else if (c == TheOneRing.class){
            return new TheOneRing(x, y);

        } else if (c == TreeStump.class){
            return new TreeStump(x, y);
        }

        return null;
    }
}
