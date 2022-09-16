package unsw.loopmania.buildings;

import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.subject_observer.CharacterCyclesObserver;
import unsw.loopmania.subject_observer.CharacterCyclesSubject;

import java.util.List;
import org.javatuples.Pair;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Vampire Castle class -> building that spwans vampires every 5 cycles of the character
 */
public class VampireCastle extends Building implements EnemySpawner, CharacterCyclesObserver{
    final private int SPAWN_CYCLES = 5;
    private int cycleCounter;
    private boolean spawnNewEnemy;


    /**
     * Constructor that accepts the following parameters:
     * @param x
     * @param y
     */
    public VampireCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/vampire_castle_building_purple_background.png");
        this.cycleCounter = 0;
        this.spawnNewEnemy = false;
        super.setImageString("src/images/vampire_castle_card.png");
    }

    /**
     * Method to try to spawn an enemy, it does if the conditions are satisfied, null if not.
     * @param orderedPath
     * @return : new vampire if conditions are met, or null if they aren't.
     */
    @Override
    public Enemy spawnEnemy(List<Pair<Integer, Integer>> orderedPath) {
        
        if (this.spawnNewEnemy){
            if (Math.floorMod(cycleCounter, this.SPAWN_CYCLES) == 0){
                PathPosition newPathPos = spawnEnemyPathPosition(orderedPath);
                if (newPathPos != null){
                    this.spawnNewEnemy = false;
                    return new Vampire(newPathPos);
                }
            }
        }

        return null;
    }

    /**
     * Gets the enemy path position
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
     * Updates the cycle counter
     * @param subject
     */
    @Override
    public void updateCycles(CharacterCyclesSubject subject) {
        this.spawnNewEnemy = true;
        this.cycleCounter = subject.getCharacterNumberOfCycles(); 
    }

}
