package unsw.loopmania.buildings;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.RadiusChecker;

/**
 * Campfire class - backend
 */
public class Campfire extends Building implements BuildingEffectOnEnemies {
    private final double BATTLE_RADIUS = 5;
    private final double ATTACK_FACTOR = 2;
    private double characterAttack; 

    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/campfire.png");
        this.characterAttack = 0;
    }

    /**
     * Setter - sets the attack value from The Character
     * @param characterAttack
     */
    public void setCharacterAttack(double characterAttack) {
        this.characterAttack = characterAttack;
    }

    /**
     * Applies effect on enemy: during battle if campfire is within battle radius, 
     * it will double the damage the enemies will take from Character.
     * @param movingEntity : an enemy
     * @return : the passed enemy.
     */
    @Override
    public Enemy applyEffect(Enemy enemy) {
        if (RadiusChecker.isWithinRadius(this, enemy, this.BATTLE_RADIUS)){
            enemy.takeDamage(this.characterAttack * this.ATTACK_FACTOR);
        }
        return enemy;
    }

    /**
     * Applies effect on a list of enemies: during battle, if campfire is within battle radius, 
     * it will double the damage the enemies will take from Character.
     * @param movingEntityList : list of enemies.
     * @return : the passed list of enemies.
     */
    @Override
    public List<Enemy> applyEffect(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList){
            applyEffect(enemy);
        }
        return enemyList;
    }
}
