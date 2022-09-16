package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;

import unsw.loopmania.CharacterCycleCounter;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.VampireCastle;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.Character;

public class VampireCastleTest {

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

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));

        charCounter.attachObserver(vCastle);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertEquals(null, vCastle.spawnEnemy(orderedPath));
    }

    @Test
    public void spawnVampireTest5Cycles(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));

        charCounter.attachObserver(vCastle);

        for(int j = 1; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertEquals(null, vCastle.spawnEnemy(orderedPath));
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);

    }

    @Test
    public void spawnVampireTest10Cycles(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));

        charCounter.attachObserver(vCastle);

        for(int w = 0; w < 2; w++){
            for(int j = 1; j < 5; j++){
                for (int i = 0; i < 10; i++){
                    newCharacter.moveDownPath();
                    charCounter.checkCycle();
                }
                assertEquals(null, vCastle.spawnEnemy(orderedPath));
            }
            
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }

            assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);
        }
    }

    @Test
    public void spawnVampireTest11Cycles(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(-1), new SimpleIntegerProperty(0));

        charCounter.attachObserver(vCastle);

        for(int w = 0; w < 2; w++){
            for(int j = 1; j < 5; j++){
                for (int i = 0; i < 10; i++){
                    newCharacter.moveDownPath();
                    charCounter.checkCycle();
                }
                assertEquals(null, vCastle.spawnEnemy(orderedPath));
            }
            
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }

            assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertEquals(null, vCastle.spawnEnemy(orderedPath));
    }


    // Tests with different Castle location --------------------------------------------------

    @Test
    public void spawnVampireTest5CyclesXPlus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));

        charCounter.attachObserver(vCastle);

        for(int j = 1; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertEquals(null, vCastle.spawnEnemy(orderedPath));
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);
    }

    @Test
    public void spawnVampireTest5CyclesYMinus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(-1));

        charCounter.attachObserver(vCastle);

        for(int j = 1; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertEquals(null, vCastle.spawnEnemy(orderedPath));
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);

    }

    @Test
    public void spawnVampireTest5CyclesYPlus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));

        charCounter.attachObserver(vCastle);

        for(int j = 1; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertEquals(null, vCastle.spawnEnemy(orderedPath));
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);

    }

    @Test
    public void spawnVampireTest5CyclesXandYMinus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(-1), new SimpleIntegerProperty(-1));

        charCounter.attachObserver(vCastle);

        for(int j = 1; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertEquals(null, vCastle.spawnEnemy(orderedPath));
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);
    }

    @Test
    public void spawnVampireTest5CyclesXandYPlus1(){
        List<Pair<Integer,Integer>> orderedPath = getOrderedPath();
        Character newCharacter = getNewCharacter(orderedPath);

        CharacterCycleCounter charCounter = new CharacterCycleCounter(newCharacter);

        VampireCastle vCastle = new VampireCastle(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        charCounter.attachObserver(vCastle);

        for(int j = 1; j < 5; j++){
            for (int i = 0; i < 10; i++){
                newCharacter.moveDownPath();
                charCounter.checkCycle();
            }
            assertEquals(null, vCastle.spawnEnemy(orderedPath));
        }

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            charCounter.checkCycle();
        }

        assertTrue(vCastle.spawnEnemy(orderedPath) instanceof Vampire);

    }
}
