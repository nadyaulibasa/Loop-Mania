package unsw.loopmania.buildings;

import java.util.List;
import unsw.loopmania.enemies.Enemy;

public interface BuildingEffectOnEnemies extends BuildingEffect<Enemy, Enemy> {
    /**
     * Applies effect on the passed list of enemies.
     * @param enemyList : list of enemies.
     * @return : list of enemies.
     */
    public List<Enemy> applyEffect(List<Enemy> enemyList);
}
