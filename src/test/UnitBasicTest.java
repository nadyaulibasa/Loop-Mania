package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;



public class UnitBasicTest {

    @Test
    public void testBasicUnits() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        Slug slug = new Slug(position);
        Zombie zombie = new Zombie(position);
        Vampire vampire = new Vampire(position);

        assertTrue(
            slug.getMaxHealth() == 10 &&
            zombie.getMaxHealth() == 10 &&
            vampire.getMaxHealth() == 15
        );

        Character c = new Character(position);
        assertTrue(
            c.getBaseDamage() == 5
        );
      
        // Test getting health 

        // Test getting radius

        // Test getting 
    }
}
