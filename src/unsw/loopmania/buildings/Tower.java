package unsw.loopmania.buildings;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.RadiusChecker;

/**
 * Tower class - backend
 */
public class Tower extends Building implements BuildingEffectOnEnemies {
    private final double SHOOTING_RADIUS = 5;
    private final int SHOOTING_DAMAGE = 4;

    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/tower.png");
    }

    /**
     * Applies effect on enemy: during battle, enemy takes damage from tower if it is within radius.
     * @param enemy : an enemy.
     * @return : same passed enemy.
     */
    @Override
    public Enemy applyEffect(Enemy enemy) {

        if (RadiusChecker.isWithinRadius(this, enemy, this.SHOOTING_RADIUS)){
            enemy.takeDamage(this.SHOOTING_DAMAGE);
        }

        return enemy;
    }

    /**
     * Applies effect on a list of enemies: during battle, enemies takes damage from tower if they atr within radius.
     * @param enemyList : a list of enemies.
     * @return : lsame passed list of enemies.
     */
    @Override
    public List<Enemy> applyEffect(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList){
            applyEffect(enemy);
        }
        return enemyList;
    }

}
