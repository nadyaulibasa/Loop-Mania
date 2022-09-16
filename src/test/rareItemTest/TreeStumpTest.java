package test.rareItemTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import java.util.List;
import java.util.ArrayList;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.rareItems.TreeStump;

public class TreeStumpTest {
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

        // Create Tree Stump
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Equip character with Anduril
        character.equipItem(treeStump);
        
        assertEquals(character.getInventory().getEquippedDefense(), treeStump);
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

        // Create Tree Stump
        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        // Use Tree Stump
        treeStump.applyDefenseEffect(enemy);
    }
}
