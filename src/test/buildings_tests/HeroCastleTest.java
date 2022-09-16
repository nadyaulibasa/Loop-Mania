package test.buildings_tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.CharacterCycleCounter;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.HeroCastle;
import unsw.loopmania.Character;

public class HeroCastleTest {

    public Character getNewCharacter(){
        List<Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();

        for (int i = 0; i < 10; i++){
            orderedPath.add(new Pair<Integer,Integer>(i,0)); 
        }
        
        int indexInPath = orderedPath.indexOf(new Pair<Integer,Integer> (0,0));
        PathPosition pathPos = new PathPosition(indexInPath, orderedPath);
        return new Character(pathPos);
    }

    @Test
    public void openShopTest0CycleFalse(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 9; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertFalse(hc.openShop());
    }

    @Test
    public void openShopTest1CycleTrue(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 10; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertTrue(hc.openShop());
    }

    @Test
    public void openShopTest2CyclesFalse(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 20; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertFalse(hc.openShop());
    }

    @Test
    public void openShopTest3CyclesTrue(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 30; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }
        assertTrue(hc.openShop());
    }

    @Test
    public void openShopTest4CyclesFalse(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 40; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }
        assertFalse(hc.openShop());
    }

    @Test
    public void openShopTest5CyclesFalse(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 50; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertFalse(hc.openShop());
    }


    @Test
    public void openShopTest6CyclesTrue(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 60; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertTrue(hc.openShop());
    }

    @Test
    public void openShopTest10CyclesTrue(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 100; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertTrue(hc.openShop());
    }

    @Test
    public void openShopTest15CyclesTrue(){
        Character newCharacter = getNewCharacter();

        CharacterCycleCounter cycleCounter = new CharacterCycleCounter(newCharacter);

        HeroCastle hc = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        cycleCounter.attachObserver(hc);

        for (int i = 0; i < 150; i++){
            newCharacter.moveDownPath();
            cycleCounter.checkCycle(); 
        }

        assertTrue(hc.openShop());
    }
}
