package unsw.loopmania.weapons;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.*;
import unsw.loopmania.enemies.Enemy;


/**
 * Represents an equipped or unequipped sword in the backend world.
 */
public class Sword extends Item implements Weapon {
    private final double ITEM_PRICE = 5;
    private double baseDamage = 7;

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/basic_sword.png");
    }   

    @Override
    public void applyWeaponEffect(Enemy enemy) {
        // Sword doesn't have a special weapon effect
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
    
    /**
     * Returns the damage inflicted with using the weapon.
     * @param enemy
     * @return damage
     */
    @Override
    public double getDamage(Enemy enemy) {
        return baseDamage;
    }
}
