package unsw.loopmania;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Item class
 */
public abstract class Item extends StaticEntity {
    private BooleanProperty inInventory;
    private BooleanProperty isNotPurchasable;
    private BooleanProperty isSelected;
    private String itemName;

    /**
     * Constructor accepts the following SimpleIntegerProperty parameters:
     * @param x
     * @param y
     */
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.inInventory = new SimpleBooleanProperty(false);
        this.isNotPurchasable = new SimpleBooleanProperty(false);
        this.isSelected = new SimpleBooleanProperty(false);
    }

    public String getItemName(){
        return this.getClass().getSimpleName();
    }

    public BooleanProperty getIsSelected() {
        return isSelected;
    }



    public void setIsSelected(boolean isSelected) {
        this.isSelected.set(isSelected);;
    }


    /**
     * Sets inVentory variable to true
     */
    public void pickUpItem() {
        inInventory.setValue(true);
    }

    /**
     * Setter - sets if item is purchasable
     * @param value
     */
    public void setIsNotPurchasable(boolean value){
        this.isNotPurchasable.set(value);
    }

    /**
     * Getter
     * @return : returns BooleanProperty
     */
    public BooleanProperty getIsNotPurchasable(){
        return this.isNotPurchasable;
    }

    /**
     * Getter
     * @return : returns true if item is purchasable, false if not
     */
    public boolean getIsNotPurchasableValue(){
        return this.isNotPurchasable.get();
    }

    /**
     * Getter
     * @return : return item price
     */
    public abstract double getItemPrice();

}
