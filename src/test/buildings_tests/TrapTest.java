package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.Trap;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.Health;

public class TrapTest {
    
    @Test
    public void characterPassingThroughVillage(){
        final double CURRENT_HEALTH = 50;
        final double FINAL_HEALTH = 35;

        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Enemy newEnemy = new Enemy(pathPos);

        Trap newTrap = new Trap(new SimpleIntegerProperty(4), new SimpleIntegerProperty(0));

        newEnemy.setHealth(new Health((int)CURRENT_HEALTH));
        
        for (int i = 0; i < 4; i++){
            newTrap.applyEffect(newEnemy);
            assertEquals(CURRENT_HEALTH, newEnemy.getCurrentHealth());
            newEnemy.moveDownPath();
        }

        newTrap.applyEffect(newEnemy);
        assertEquals(FINAL_HEALTH, newEnemy.getCurrentHealth());
    }
}
