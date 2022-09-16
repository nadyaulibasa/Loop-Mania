package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.CharacterCycleCounter;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;

public class CharacterCycleCounterTest {
    
    @Test
    public void cycleCounter0CycleTest(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Character newCharacter = new Character(pathPos);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        newCharacter.moveDownPath();

        charCounter.checkCycle();

        assertEquals(0, charCounter.getCharacterNumberOfCycles());
    }

    @Test
    public void cycleCounter1CycleTest(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Character newCharacter = new Character(pathPos);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertEquals(1, charCounter.getCharacterNumberOfCycles());
    }

    @Test
    public void cycleCounter5CycleTest(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Character newCharacter = new Character(pathPos);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        for (int i = 0; i < 50; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertEquals(5, charCounter.getCharacterNumberOfCycles());
    }

    @Test
    public void cycleCounter100CycleTest(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        Character newCharacter = new Character(pathPos);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        for (int i = 0; i < 100; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertEquals(10, charCounter.getCharacterNumberOfCycles());
    }
}
