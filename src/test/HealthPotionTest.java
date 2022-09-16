package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import java.util.List;
import java.util.ArrayList;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.miscItems.HealthPotion;

public class HealthPotionTest {
    @Test
    public void testEquip() {
        // Create character
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        Character character = new Character(position);

        // Create health potion
        HealthPotion hp = new HealthPotion(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        
        assertEquals(character.getCurrentHealth(), character.getMaxHealth());
        character.setCurrentHealth(0);
        
        // Use health potion
        hp.use(character);

        // After used, charachter's health should be max.
        assertEquals(character.getCurrentHealth(), character.getMaxHealth());
    }
}
