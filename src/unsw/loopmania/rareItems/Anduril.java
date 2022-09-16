package unsw.loopmania.rareItems;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.weapons.*;
import unsw.loopmania.enemies.*;

/**
 * Rare item Anduril, Flame of the West.
 * A very high damage sword which causes triple damage against bosses.
 */
public class Anduril extends RareItem implements Weapon {
    private double baseDamage = 15;

    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/anduril_flame_of_the_west.png");
    }

    @Override
    public void applyWeaponEffect(Enemy enemy) {
        // Anduri does not have a special weapon effect.
    }

    /**
     * Returns the damage inflicted with using the weapon.
     * @param enemy
     * @return damage
     */
    @Override
    public double getDamage(Enemy enemy) {
        if (enemy instanceof Boss) {
            return baseDamage * 3;
        } else {
            return baseDamage;
        }
    }
}
