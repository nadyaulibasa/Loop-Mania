package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.CharacterCycleCounter;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.ZombiePit;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;

public class ZombiePitTest {

    public List<Pair<Integer,Integer>> getOrderedPath(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        return orderedPath;
    }
    public Character getNewCharacter(List<Pair<Integer,Integer>> orderedPath){
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        return new Character(pathPos);
    }

    @Test
    public void spawnVampireTest1Cycle(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));
        
        charCounter.attachObserver(zombiePit);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);

    }

    @Test
    public void spawnVampireTest3Cycles(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));
        
        charCounter.attachObserver(zombiePit);

        for (int j = 0; j < 3; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);
        }
    }


    @Test
    public void spawnVampireTest5Cycles(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));
        
        charCounter.attachObserver(zombiePit);

        for (int j = 0; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);
        }
    }

    // Tests with zombie pit in different locations --------------------------------

    @Test
    public void spawnVampireTest1CycleXplus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));
        
        charCounter.attachObserver(zombiePit);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);

    }


    @Test
    public void spawnVampireTest1CycleYminus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(0), new SimpleIntegerProperty(-1));
        
        charCounter.attachObserver(zombiePit);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);

    }


    @Test
    public void spawnVampireTest1CycleYplus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        
        charCounter.attachObserver(zombiePit);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);

    }

    @Test
    public void spawnVampireTest1CycleXandYplus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        
        charCounter.attachObserver(zombiePit);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);
    }

    @Test
    public void spawnVampireTest1CycleXandYminus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);
        
        ZombiePit zombiePit = new ZombiePit (new SimpleIntegerProperty(-1), new SimpleIntegerProperty(-1));
        
        charCounter.attachObserver(zombiePit);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(zombiePit.spawnEnemy(orderedPath) instanceof Zombie);
    }

}
