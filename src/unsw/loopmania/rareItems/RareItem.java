package unsw.loopmania.rareItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item;

/**
 * A backend rare item entity in the world.
 */
public abstract class RareItem extends Item {
    public RareItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public double getItemPrice() {
        return 0;
    }
}
