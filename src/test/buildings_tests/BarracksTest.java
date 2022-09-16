package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.Barracks;
import unsw.loopmania.Ally;
import unsw.loopmania.Character;

public class BarracksTest {

    @Test
    public void characterPassingThroughVillage(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Character newCharacter = new Character(pathPos);

        Barracks newBarrack = new Barracks(new SimpleIntegerProperty(4), new SimpleIntegerProperty(0));

        newCharacter.setCurrentHealth(50);
        
        for (int i = 0; i < 4; i++){
            assertFalse(newBarrack.applyEffect(newCharacter) instanceof Ally);
            newCharacter.moveDownPath();
        }


        assertTrue(newBarrack.applyEffect(newCharacter) instanceof Ally);
    }
    
    
}
