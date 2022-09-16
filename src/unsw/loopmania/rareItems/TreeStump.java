package unsw.loopmania.rareItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.defenses.*;
import unsw.loopmania.enemies.*;

/**
 * Rare item Tree Stump
 */
public class TreeStump extends RareItem implements Defense {
    private final double baseDefense = 5;

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/tree_stump.png");
    }

    @Override
    public void applyDefenseEffect(MovingEntity enemy) {
        // Tree Stump does not have special defense effects.
    }

    @Override
    public double getDefense(Enemy enemy) {
        if (enemy instanceof Boss) {
            return baseDefense * 3;
        } else {
            return baseDefense;
        }
    }
}