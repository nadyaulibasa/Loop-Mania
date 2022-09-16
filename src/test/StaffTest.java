package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import java.util.List;
import java.util.ArrayList;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.*;
import unsw.loopmania.weapons.*;

public class StaffTest {
    @Test
    /**
     * Tests the equipItem method
     */
    public void testEquip() {
        // Create character
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        Character character = new Character(position);

        // Create staff
        Staff staff = new Staff(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        // Equip character with staff
        character.equipItem(staff);
        
        assertEquals(character.getInventory().getEquippedWeapon(), staff);
    }

    @Test
    /**
     * Tests the applyWeaponEffect method
     */
    public void testDamage() {
        // Create character
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        Character character = new Character(position);

        // Create enemy
        Enemy enemy = new Enemy(position);

        // Create staff
        Staff staff = new Staff(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));

        // Use staff
        staff.applyWeaponEffect(enemy);
    }
}