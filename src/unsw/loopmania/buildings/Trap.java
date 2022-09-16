package unsw.loopmania.buildings;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.Enemy;

/**
 * Trap Class - backend with trap logic
 */
public class Trap extends Building implements BuildingEffectOnEnemies {
    private final double TRAP_DAMAGE = 15;

    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/trap.png");
    }

    /**
     * Applies effect to enemies: reduces enemies' health.
     * @param movingEntity : an enemy.
     * @return : the passed enemy.
     */
    @Override
    public Enemy applyEffect(Enemy enemy) {
        // Building and The enemy in the same location
        if (enemy.getX() == this.getX() && enemy.getY() == this.getY()){
            enemy.takeDamage(this.TRAP_DAMAGE);
        }

        return enemy;
    }

    /**
     * Applies effect on list of enemies: reduces enemies' health.
     * @param movingEntityList : list of enemies.
     * @return : same passed list of enemies.
     */
    @Override
    public List<Enemy> applyEffect(List<Enemy> enemyList) {
        for (Enemy enemy : enemyList){
            applyEffect(enemy);
        }
        return enemyList;
    }
}
