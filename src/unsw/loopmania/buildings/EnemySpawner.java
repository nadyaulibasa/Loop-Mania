package unsw.loopmania.buildings;

import java.util.List;

import org.javatuples.Pair;
import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Enemy;

public interface EnemySpawner{
    /**
     * Spawns an enemy if conditions are satisfied
     * @param orderedPath
     * @return
     */
    public Enemy spawnEnemy(List<Pair<Integer, Integer>> orderedPath);

    /**
     * Gets the enemy spawn position
     * @param orderedPath
     * @return
     */
    public PathPosition spawnEnemyPathPosition(List<Pair<Integer, Integer>> orderedPath);
}
