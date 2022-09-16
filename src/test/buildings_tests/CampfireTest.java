package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.Campfire;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.Character;
import unsw.loopmania.Health;

public class CampfireTest {
    @Test
    public void characterPassingThroughVillage(){
        final double CURRENT_HEALTH = 50;
        final double CAMPFIRE_DMG_FACTOR = 2; 
        final double CHARACTER_ATTACK = 8;

        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 20; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        
        Character newCharacter = new Character(pathPos);
        newCharacter.setCurrentHealth((int)CURRENT_HEALTH);

        Enemy newEnemy = new Enemy(pathPos);
        newEnemy.setHealth(new Health((int)CURRENT_HEALTH));

        Campfire campfire = new Campfire(new SimpleIntegerProperty(9), new SimpleIntegerProperty(0));
        
        campfire.setCharacterAttack(CHARACTER_ATTACK);

        // Outside shooting radius
        for (int i = 0; i < 4; i++){
            campfire.applyEffect(newEnemy);
            assertEquals(CURRENT_HEALTH, newEnemy.getCurrentHealth());
            newEnemy.moveDownPath();
        }

        // Inside shooting radius
        for (int i = 1; i <= 2; i++){
            campfire.applyEffect(newEnemy);
            assertEquals(CURRENT_HEALTH - (CHARACTER_ATTACK * CAMPFIRE_DMG_FACTOR * i), newEnemy.getCurrentHealth());
            newEnemy.moveDownPath();
        }
 
    }
}
