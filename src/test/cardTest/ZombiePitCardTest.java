package test.cardTest;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.cards.*;
import unsw.loopmania.buildings.*;

public class ZombiePitCardTest {
    @Test
    public void TestPlacement() {
        // Create ordered path
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        orderedPath.add(new Pair<Integer, Integer>(1,2));

        // Create card
        // Zombie Pit card spawns on a non-path tile adjacent to the path
        Card card = new ZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        
        // Passes a path tile, must fail
        boolean f1 = card.correctPlacement(1, 1, orderedPath);
        assertEquals(f1, false);

        // Passes a non-adjacent non-path tile, must fail
        boolean f2 = card.correctPlacement(3, 3, orderedPath);
        assertEquals(f2, false);

        // Passes an adjacent non-path tile, returns true
        boolean t = card.correctPlacement(1, 3, orderedPath);
        assertEquals(t, true);
    }

    @Test
    public void TestSpawnBuilding() {
        // Create card
        // Zombie pit card spawns on a non-path tile adjacent to the path
        Card card = new ZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Assert the building spawned is a Zombie Pit
        Building b = card.spawnBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(b instanceof ZombiePit);
    }
}
