package unsw.loopmania.defenses;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.enemies.Enemy;


/**
 * An equippable item of the Character that reduces the 
 * damage of the Character by a certain amount but reduces the damage the Character
 * takes
 */
public class Helmet extends Item implements Defense {
    private final double ITEM_PRICE = 7;
    private int baseDefense = 5;

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/helmet.png");
    }   

    /**
     * Gets the amount of defense it provides
     * @return : The baseDefense value
     */
    public double getDefense(Enemy enemy) {
        return baseDefense;
    }
    
    /**
     * The Helmet reduces the amount of damage of the Character
     * @param e : The Character getting their damage reduced
     */
    @Override
    public void applyDefenseEffect(MovingEntity e) {
        if (e instanceof Character) {
            ((Character) e).setBaseDamage(((Character) e).getBaseDamage()*0.6);
        }
        // Enemy attacks are reduced by a scalar value
        // The damage inflicted by the Character against enemies is reduced 
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
}