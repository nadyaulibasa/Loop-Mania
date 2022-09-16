package unsw.loopmania.miscItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.Item;

/**
 * A rare item. If the character dies, then this item can restore the Character
 * to full health. Gets destroyed once used.
 */
public class TheOneRing extends Item {
    private final double ITEM_PRICE = 15;

    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/the_one_ring.png");
    }

    /**
     * Restores Character's health to maximum full health. 
     * Once used, destroy the health potion.
     */
    public void use(Character character) {
        character.restoreFullHealth();
        character.getInventory().unequipItem(this);
        this.destroy();
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
}
