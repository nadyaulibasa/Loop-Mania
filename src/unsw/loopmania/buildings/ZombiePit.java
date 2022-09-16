package unsw.loopmania.buildings;

import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.subject_observer.CharacterCyclesObserver;
import unsw.loopmania.subject_observer.CharacterCyclesSubject;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;

/**
 * Class of the Zombie Pit, the building that spwans the zombies every character cycle
 */
public class ZombiePit extends Building implements EnemySpawner, CharacterCyclesObserver {

    private boolean spawnNewEnemy;

    /**
     * Constructor that accepts the following parameters
     * @param x
     * @param y
     */
    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/zombie_pit.png");
        this.spawnNewEnemy = false;
        super.setImageString("src/images/zombie_pit_card.png");
    }

    /**
     * Spawns an zombie every character cycle
     * @param orderedPath
     * @return : Zombie if conditions are met (every character cycle), null if they aren't.
     */
    @Override
    public Enemy spawnEnemy(List<Pair<Integer, Integer>> orderedPath) {
        if (spawnNewEnemy){
            PathPosition newPathPos = spawnEnemyPathPosition(orderedPath);
            if (newPathPos != null){
                this.spawnNewEnemy = false;
                return new Zombie(newPathPos);
            }
        }
        return null;
    }

    /**
     * Gets the spawn enemy position
     * @param orderedPath
     * @return : PathPosition
     */
    @Override
    public PathPosition spawnEnemyPathPosition(List<Pair<Integer, Integer>> orderedPath) {
        int pathIndex = -1;

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                int pathIndexTemp = orderedPath.indexOf(new Pair<Integer,Integer> (this.getX() + i, this.getY() + j));
                if (pathIndexTemp > -1){
                    pathIndex = pathIndexTemp;
                }
            }
        }

        if (pathIndex == -1){
            return null;
        }

        return (new PathPosition(pathIndex, orderedPath));
    }

    /**
     * Updated the cycle counter from subject
     * @param subject
     */
    @Override
    public void updateCycles(CharacterCyclesSubject subject) {
        this.spawnNewEnemy = true;        
    }

}
