package unsw.loopmania.enemies;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import java.util.Random;

/**
 * The Vampire Enemy. This enemy is weak to the Stake weapon
 * and has a change of a critical attack. This causes the Vampire to deal
 * random additional damage
 */
public class Vampire extends Enemy {
    private double criticalBiteChance;
    private static final int EXP_DROP = 4;

    public Vampire(PathPosition position) {
        super(position);
        super.setImageString("src/images/vampire.png");
        setHealth(new Health(15));
        setBaseDamage(4);
        this.criticalBiteChance = 0.3;
        // setExpDrop(EXP_DROP);
    }

    /**
     * Resets the stats of the vampire
     */
    @Override
    public void resetEnemyStats() {
        setBaseDamage(4);
        criticalBiteChance = 4;
    }

    /**
     * The vampire performs an attack
     * @param e     : the Enemy that the Vampire may attack
     * @param c     : the Character that the Vampire may attack
     */
    @Override
    public String attack(Enemy e, Character c) {
        String attackDetails = getEnemyDetails() + " [" + getStateDetails() + "] " + "is now attacking";
        c.applyDefenses(this);
        
        // Get damage of Vampire
        double dmg = getBaseDamage();

        // Check if CriticalBite event happens
        double criticalValue = Math.random();
        if (criticalValue < criticalBiteChance) {
            int extraDamage = new Random().nextInt(4) + 1;
            setBaseDamage(dmg + extraDamage);
            attackDetails += "\n Critical Bite! Doing extra Damage!";
        }
        String damageDetails = getState().attack(this, e, c);
        resetEnemyStats();
        return attackDetails + "\n" + damageDetails;
    }    

    public void setCriticalBiteChance(double chance) {
        this.criticalBiteChance = chance;
    }
    public double getCriticalBiteChance() {
        return criticalBiteChance;
    }
}
