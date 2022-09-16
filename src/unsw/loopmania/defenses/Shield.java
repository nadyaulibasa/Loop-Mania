package unsw.loopmania.defenses;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.enemies.*;

/**
 * An equippable item of the Character that reduces the 
 * chance of a critical strike from a Vampire
 */
public class Shield extends Item implements Defense {
    private final double ITEM_PRICE = 7;
    private double baseDefense = 5;
   
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/shield.png");
    }
    
    public double getDefense(Enemy enemy) {
        return baseDefense;
    }

    /**
     * Reduces the chance of a critical strike from a vampire
     * @param e : The vampire that gets the chance reduced
     */
    @Override
    public void applyDefenseEffect(MovingEntity e) {
        // Critical vampire attacks have a 60% lower chance of occurring
        if (e instanceof Vampire) {
            ((Vampire) e).setCriticalBiteChance(1-((Vampire) e).getCriticalBiteChance()*0.6);
        }
    }

    @Override
    public double getItemPrice() {
        return this.ITEM_PRICE;
    }
}