package unsw.loopmania.enemies;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.battleStates.EnemyBattleState;
import unsw.loopmania.battleStates.NormalEnemyState;

/**
 * An enemy entity that The Character must defeat as they traverse
 * through the map. Each enemy has different stats and characteristics.
 */
public class Enemy extends MovingEntity {
    private Health health;
    private double baseDamage;
    private int supportRadius;
    private int battleRadius;
    private EnemyBattleState state;
    private List<Enemy> supportEnemies;
    private int expDrop;

    public Enemy(PathPosition position) {
        super(position);
        this.health = new Health(10);
        this.baseDamage = 2;
        this.supportRadius = 5;
        this.battleRadius = 1;
        this.state = new NormalEnemyState();
        this.supportEnemies = new ArrayList<Enemy>();
        this.expDrop = 1;
    }
    
    /**
     * Reset stats of Enemies in case of buffed/nerfed stats
     */
    public void resetEnemyStats() {
        baseDamage = 2;
    }

    /**
     * Finds the damage that the enemy will deal
     * @return int -> value of damage
     */
    public String attack(Enemy e, Character c) {
        String attackDetails = getEnemyDetails() + " [" + getStateDetails() + "] " + "is now attacking";
        c.applyDefenses(this);
        String damageDetails = state.attack(this, e, c);
        resetEnemyStats();
        return attackDetails + "\n" + damageDetails;
    }

    /**
     * Moves the enemy through the map
     */
    public void move(){
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

    /**
     * The enemy takes damage
     * 
     * @param dmg -> the amount of damage being taken
     */
    public String takeDamage(double dmg) {
        health.decreaseCurrentHealth(dmg);
        return getClass().getSimpleName() + " took " + dmg + " damage. Now has hp : " + getHealthDetails() + "\n";
    }

    /**
     * Returns if the enemy's health has fallen below 1
     * @return -> true if enemy is dead
     */
    public boolean isDead() {
        if (getCurrentHealth() < 1) return true;
        return false;
    }
    
    /**
     * Getters
     */
    public double getMaxHealth() {return health.getMaxHealth();}
    public double getCurrentHealth() {return health.getCurrentHealth();}
    public double getBaseDamage() {return baseDamage;}
    public int getSupportRadius() {return supportRadius;}
    public int getBattleRadius() {return battleRadius;}
    public EnemyBattleState getState() {return state;}
    public List<Enemy> getSupportEnemies() {return supportEnemies;}
    public double getPercentageHealth() {
        return getCurrentHealth()/getMaxHealth();
    }
    public int getExpReward() {return expDrop;}


    /**
     * Setters
     */
    public void setHealth(Health health) {this.health = health;}
    public void setMaxHealth() {health.setCurrentHealth(health.getMaxHealth());}
    public void setBaseDamage(double baseDamage) {this.baseDamage = baseDamage;}
    public void setSupportRadius(int supportRadius) {this.supportRadius = supportRadius;}
    public void setBattleRadius(int battleRadius) {this.battleRadius = battleRadius;}
    public void setState(EnemyBattleState state) {this.state = state;}    
    public void setSupportEnemies(List<Enemy> supportEnemies) {this.supportEnemies = supportEnemies;}
    public void setExpDrop(int exp) {this.expDrop = exp;}
    public String getEnemyDetails() {
        return this.getClass().getSimpleName() + getHealthDetails() +  " :";
    }

    public String getHealthDetails() {
        return " (" + this.getCurrentHealth() + "/" + getMaxHealth() + ")";
    }
    public String getStateDetails() {
        return this.getState().getClass().getSimpleName();
    }
}
