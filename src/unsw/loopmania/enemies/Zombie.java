package unsw.loopmania.enemies;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.battleStates.NormalEnemyState;
import unsw.loopmania.battleStates.ZombifiedAllyState;

/**
 * The Zombie Enemy. They have lower health and have a chance of a 
 * critical attack. This causes the attack to turn an Ally into a Zombied
 * state.
 */
public class Zombie extends Enemy {
    private double criticalBiteChance;
    private static final int EXP_DROP = 3;

    public Zombie(PathPosition position) {
        super(position);
        super.setImageString("src/images/zombie.png");
        this.setBaseDamage(4);
        this.setBattleRadius(2);
        this.setSupportRadius(5);
        this.criticalBiteChance = 0.2;
        // setExpDrop(EXP_DROP);
    }
    
    /**
     * Resets the stats of the zombie
     */
    @Override
    public void resetEnemyStats() {
        setBaseDamage(4);
        criticalBiteChance = 0.2;
    }

    /**
     * The Zombie performs an attack
     * @param e     : the Enemy that the Zombie may attack
     * @param c     : the Character that the Zombie may attack
     */
    @Override
    public String attack(Enemy e, Character c) {
        String attackDetails = getEnemyDetails() + " [" + getStateDetails() + "] " + "is now attacking";
        c.applyDefenses(this);
        if (getState() instanceof NormalEnemyState && c.hasAllies()) {
            Ally ally = c.getFirstNormalAlly();
            double criticalValue = Math.random();
            if (criticalValue < criticalBiteChance) {
                attackDetails += "\nCritical Bite! Ally was Zombified!";
                ally.setState(new ZombifiedAllyState());
                return attackDetails;
            }
        }
        String damageDetails = getState().attack(this, e, c);
        resetEnemyStats();
        return attackDetails + "\n" + damageDetails;
    }
}

