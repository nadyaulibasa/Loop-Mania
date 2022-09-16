package unsw.loopmania.miscItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.Item;


public class HealthPotion extends Item {
    private final double ITEM_PRICE = 15;
    
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/brilliant_blue_new.png");
    }

    /**
     * Restores Character's health to maximum full health. 
     * Once used, destroy the health potion.
     */
    public void use(Character character) {
        character.restoreFullHealth();
        this.destroy();
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
}
