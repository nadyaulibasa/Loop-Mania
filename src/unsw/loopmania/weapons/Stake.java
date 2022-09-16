package unsw.loopmania.weapons;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.*;
import unsw.loopmania.enemies.*;

/**
 * Represents an equipped or unequipped stake in the backend world.
 */
public class Stake extends Item implements Weapon {
    private final double ITEM_PRICE = 8;
    private double baseDamage = 5;

    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/stake.png");
    }   

    @Override
    public void applyWeaponEffect(Enemy enemy) {
        // Stake doesn't have a special weapon effect.
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
    
    /**
     * Returns the damage inflicted with using the weapon.
     * The damage is doubled if the enemy is of type Vampire.
     * @param enemy
     * @return damage
     */
    @Override
    public double getDamage(Enemy enemy) {
        if (enemy instanceof Vampire) {
            return baseDamage * 2;
        } else {
            return baseDamage;
        }
    }

}
