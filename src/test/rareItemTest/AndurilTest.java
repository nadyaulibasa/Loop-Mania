package test.rareItemTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import java.util.List;
import java.util.ArrayList;

import unsw.loopmania.*;
import unsw.loopmania.rareItems.Anduril;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.Character;

public class AndurilTest {
    /**
     * Test equip weapon
     */
    @Test
    public void testEquip() {
        // Create character
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        Character character = new Character(position);

        // Create Anduril
        Anduril anduril = new Anduril(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Equip character with Anduril
        character.equipItem(anduril);
        
        assertEquals(character.getInventory().getEquippedWeapon(), anduril);
    }

    /**
     * Tests the applyWeaponEffect method
     */
    @Test
    public void testDamage() {
        // Create character
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        // Create enemy
        Enemy enemy = new Enemy(position);

        // Create Anduril
        Anduril anduril = new Anduril(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Use Anduril
        anduril.applyWeaponEffect(enemy);
    }
}
