package unsw.loopmania.rareItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;


/**
 * Rare item The One Ring
 */
public class TheOneRing extends RareItem {
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
        this.destroy();
    }
}
