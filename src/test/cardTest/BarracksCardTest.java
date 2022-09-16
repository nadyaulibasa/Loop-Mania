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

public class BarracksCardTest {


    @Test
    public void TestPlacement() {
        // Create ordered path
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        orderedPath.add(new Pair<Integer, Integer>(1,2));

        // Create card
        // Barracks card spawns on a non-path tile adjacent to the path
        Card card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Passes a non-path tile, must fail
        boolean f = card.correctPlacement(3, 3, orderedPath);
        assertEquals(f, false);

        // Passes a path tile, returns true
        boolean t = card.correctPlacement(1, 1, orderedPath);
        assertEquals(t, true);
    }

    @Test
    public void TestSpawnBuilding() {
        // Create card
        // Barracks card spawns on a non-path tile adjacent to the path
        Card card = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Assert the building spawned is type Barracks
        Building b = card.spawnBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertTrue(b instanceof Barracks);
    }
}
