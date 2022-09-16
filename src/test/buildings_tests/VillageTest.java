package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.Village;
import unsw.loopmania.Character;

public class VillageTest {
    
    @Test
    public void characterPassingThroughVillage(){
        final double CURRENT_HEALTH = 50;
        final double FINAL_HEALTH = 55;

        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Character newCharacter = new Character(pathPos);

        Village newVillage = new Village(new SimpleIntegerProperty(4), new SimpleIntegerProperty(0));

        newCharacter.setCurrentHealth(50);
        
        for (int i = 0; i < 4; i++){
            newVillage.applyEffect(newCharacter);
            assertEquals(CURRENT_HEALTH, newCharacter.getCurrentHealth());
            newCharacter.moveDownPath();
        }

        newVillage.applyEffect(newCharacter);
        assertEquals(FINAL_HEALTH, newCharacter.getCurrentHealth());
    }
}
