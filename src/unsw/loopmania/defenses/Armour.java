package unsw.loopmania.defenses;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.enemies.Enemy;

/**
 * An equippable item of the Character that reduces the 
 * damage of an enemy by half.
 */
public class Armour extends Item implements Defense {
    private final double ITEM_PRICE = 7;
    private double baseDefense = 5;

    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/armour.png");
    }   
    
    /**
     * Gets the amount of defense it provides
     * @return : The baseDefense value
     */
    public double getDefense(Enemy enemy) {
        return baseDefense;
    }
    
    /**
     * The Armour reduces the amount of damage of an enemy
     * @param e : The enemy getting their damage reduced
     */
    @Override
    public void applyDefenseEffect(MovingEntity e) {
        if (e instanceof Enemy) {
            ((Enemy) e) .setBaseDamage(((Enemy) e).getBaseDamage()*0.5);
        }
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
}
