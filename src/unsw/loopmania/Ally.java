package unsw.loopmania;

import unsw.loopmania.battleStates.AllyBattleState;
import unsw.loopmania.battleStates.NormalAllyState;
import unsw.loopmania.enemies.*;

public class Ally extends MovingEntity {
    private int baseDamage;
    private Health health;
    private AllyBattleState state;

    public Ally() {
        super(null);
        super.setImageString("src/images/ally.png");
        this.baseDamage = 2;
        this.health = new Health(4);
        this.state = new NormalAllyState();
    }

    /**
     * The ally takes damage
     * @param dmg -> the amount of damage being taken
     */
    public String takeDamage(double dmg) {
        health.decreaseCurrentHealth(dmg);
        return getClass().getSimpleName() + " took " + dmg + " damage. Now has hp : " + getHealthDetails() + "\n";
    }

    /**
     * Checks to see if the ally's health has fallen below 1
     * @return true -> if dead
     */
    public boolean isDead() {
        if (getCurrentHealth() < 1) return true;
        return false;
    }



    /**
     * Ally applies their damage to a given enemy
     * @param e -> the enemy being attacked
     */
    public String attack(Enemy e, Character c) {
        String attackDetails = getEnemyDetails() + " [" + getStateDetails() + "] " + "is now attacking";
        String damageDetails = state.attack(this, e, c);
  
        return attackDetails + "\n" + damageDetails;
    }

    /**
     * Getters
     */
    public double getCurrentHealth() {return health.getCurrentHealth();}
    public double getMaxHealth() {return health.getMaxHealth();}
    public int getBaseDamage() {return baseDamage;}
    public AllyBattleState getState() {return state;}
    public String getEnemyDetails() {
        return this.getClass().getSimpleName() + getHealthDetails() +  " :";
    }
    public String getHealthDetails() {
        return " (" + this.getCurrentHealth() + "/" + getMaxHealth() + ")";
    }
    public String getStateDetails() {return this.getState().getClass().getSimpleName();}
    public double getPercentageHealth() {return getCurrentHealth()/getMaxHealth();}

    /**
     * Setters
     * @param state
     */
    public void setState(AllyBattleState state) {
        this.state = state;
    }
    
}
