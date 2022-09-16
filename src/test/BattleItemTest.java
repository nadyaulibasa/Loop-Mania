package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.defenses.*;
import unsw.loopmania.enemies.*;
import unsw.loopmania.weapons.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;



public class BattleItemTest {

    @Test
    public void testVsSlugwithSword() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        Slug slug = new Slug(position);
        List<Enemy> slugEnemy = new ArrayList<>();
        slugEnemy.add(slug);

        Character c = new Character(position);
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        c.equipItem(sword);
        
        Battle slugBattle = new Battle(c, slugEnemy);
        //assertTrue(slugBattle.doBattle());
    }

    @Test
    public void testStakewithVampiresAndSlug() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        Slug slug1 = new Slug(position);
        Vampire vampire = new Vampire(position);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(slug1);
        enemies.add(vampire);

        Character c = new Character(position);
        Stake Stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        c.equipItem(Stake);
        
        Battle slugBattle = new Battle(c, enemies);
        //assertTrue(slugBattle.doBattle());
    }

    @Test

    public void testArmour() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        Slug slug = new Slug(position);
        Slug slug2 = new Slug(position);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(slug);
        enemies.add(slug2);

        Character c = new Character(position);
        Armour Armour = new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        c.equipItem(Armour);

        Battle enemiesBattle = new Battle(c, enemies);
        //assertTrue(enemiesBattle.doBattle());
    }

    @Test

    public void testHelmet() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        Zombie zombie1 = new Zombie(position);
        Zombie zombie2 = new Zombie(position);
        Zombie zombie3 = new Zombie(position);
        Vampire vampire1 = new Vampire(position);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(zombie1);
        enemies.add(zombie2);
        enemies.add(zombie3);

        Character c = new Character(position);
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        c.equipItem(helmet);

        Battle enemiesBattle = new Battle(c, enemies);
        //assertFalse(enemiesBattle.doBattle());
    }
}
