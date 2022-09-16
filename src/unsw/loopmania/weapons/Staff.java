package unsw.loopmania.weapons;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.*;
import unsw.loopmania.battleStates.TranceEnemyState;
import unsw.loopmania.enemies.Enemy;

import java.util.Random;

/**
 * Represents an equipped or unequipped staff in the backend world.
 */
public class Staff extends Item implements Weapon {
    private final double ITEM_PRICE = 10;
    private double baseDamage = 3;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/staff.png");
    }   

    @Override
    public void applyWeaponEffect(Enemy enemy) {
        int tranceChoice = (new Random()).nextInt(1);
        if (tranceChoice == 0) {
            // Transform the enemy into an allied soldier temporarily
            enemy.setState(new TranceEnemyState());
            // If the trance ends during the fight, the tranced enemy reverts back to acting as an enemy
            // If the fight ends while the enemy is in a trance, the enemy die
        }
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
