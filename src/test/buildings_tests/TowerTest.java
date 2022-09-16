package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Health;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.Tower;
import unsw.loopmania.enemies.Enemy;

public class TowerTest {
    @Test
    public void enemyOutandInTowerRadius(){
        final double CURRENT_HEALTH = 50;
        final int TOWER_DMG = 4;

        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 20; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Enemy newEnemy = new Enemy(pathPos);

        Tower newTower= new Tower(new SimpleIntegerProperty(9), new SimpleIntegerProperty(0));

        newEnemy.setHealth(new Health((int)CURRENT_HEALTH));
        
        // Outside shooting radius
        for (int i = 0; i < 4; i++){
            newTower.applyEffect(newEnemy);
            assertEquals(CURRENT_HEALTH, newEnemy.getCurrentHealth());
            newEnemy.moveDownPath();
        }

        // Inside shooting radius
        for (int i = 1; i <= 2; i++){
            newTower.applyEffect(newEnemy);
            assertEquals(CURRENT_HEALTH - (TOWER_DMG * i), newEnemy.getCurrentHealth());
            newEnemy.moveDownPath();
        }

    }
    
}
