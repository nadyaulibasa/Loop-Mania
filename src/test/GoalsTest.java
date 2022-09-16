package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.PathPosition;
import unsw.loopmania.enemies.Doggie;
import unsw.loopmania.enemies.ElanMuske;
import unsw.loopmania.goals.Goals;


public class GoalsTest {

    @Test
    public void goldGoalTest() {
        Goals goals = new Goals();

        goals.addToGoldGoal(10000);
        assertEquals(goals.getGoldProgress(), 1);

        goals.addToExpGoal(10000);
        assertEquals(goals.getExpProgress(), 1);

        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        
        Doggie doggie1 = new Doggie(position);
        Doggie doggie2 = new Doggie(position);
        ElanMuske elan = new ElanMuske(position);

        goals.addToBossGoal(doggie1);
        assertEquals(goals.getBossProgress(), 0.5);

        goals.addToBossGoal(doggie2);
        assertEquals(goals.getBossProgress(), 0.5);

        goals.addToBossGoal(elan);
        assertEquals(goals.getBossProgress(), 1);
    }
}
