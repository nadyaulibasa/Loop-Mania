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



public class BattleTest {
    final private int BATTLE_CONTINUES = 0;
    final private int HERO_WIN = 1;
    final private int ENEMY_WIN = 2;

    public PathPosition setupPosition() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        return position;
    }

    @Test
    public void testVsSlug() {
        PathPosition position = setupPosition();
        System.err.println("creating enemies\n");
        List<Enemy> enemies = new ArrayList<>();
        System.err.println("creating slug\n");
        Slug slug = new Slug(position);
        enemies.add(slug);

        System.err.println("creating charaacter\n");
        Character c = new Character(position);

        System.err.println("creating battle\n");
        Battle slugBattle = new Battle(c, enemies);

        assertEquals(slugBattle.tickBattle(), BATTLE_CONTINUES);
        assertEquals(slug.getCurrentHealth(), slug.getMaxHealth() - c.getBaseDamage());

        assertEquals(slugBattle.tickBattle(), BATTLE_CONTINUES);
        assertEquals(c.getCurrentHealth(), c.getMaxHealth() - slug.getBaseDamage());

        assertEquals(slugBattle.tickBattle(), BATTLE_CONTINUES);
        assertEquals(slug.getCurrentHealth(), slug.getMaxHealth() - 2*c.getBaseDamage());

        assertEquals(slugBattle.tickBattle(), BATTLE_CONTINUES);
        assertEquals(c.getCurrentHealth(), c.getMaxHealth() - 2*slug.getBaseDamage());
        
        assertEquals(slugBattle.tickBattle(), BATTLE_CONTINUES);
        assertEquals(slugBattle.tickBattle(), BATTLE_CONTINUES);
        assertEquals(slugBattle.tickBattle(), HERO_WIN);
    }

    @Test
    public void testVSSlugs() {
        PathPosition position = setupPosition();
        Slug slug1 = new Slug(position);
        Slug slug2 = new Slug(position);
        List<Enemy> slugEnemy = new ArrayList<>();
        slugEnemy.add(slug1);
        slugEnemy.add(slug2);

        Character c = new Character(position);
        
        Battle slugBattle = new Battle(c, slugEnemy);
        int result = BATTLE_CONTINUES;
        while (result == BATTLE_CONTINUES) {
            result = slugBattle.tickBattle();
        }
        assertEquals(slugBattle.getDefeatedEnemies().size(), 2);
    }

    @Test

    public void testVsDifferenetEnemies() {
        PathPosition position = setupPosition();

        Slug slug = new Slug(position);
        Zombie zombie = new Zombie(position);
        Vampire vampire = new Vampire(position);

        List<Enemy> enemies = new ArrayList<>();

        Character c = new Character(position);

        enemies.add(slug);
        enemies.add(zombie);
        enemies.add(vampire);

        Battle enemiesBattle = new Battle(c, enemies);
        
        int result = BATTLE_CONTINUES;
        while (result == BATTLE_CONTINUES) {
            result = enemiesBattle.tickBattle();
        }
        
        assertEquals(enemiesBattle.getDefeatedEnemies().size(), 3);
    }

    @Test

    public void testLosingBattle() {
        PathPosition position = setupPosition();

        Zombie zombie1 = new Zombie(position);
        Zombie zombie2 = new Zombie(position);
        Zombie zombie3 = new Zombie(position);
        Vampire vampire1 = new Vampire(position);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(zombie1);
        enemies.add(zombie2);
        enemies.add(zombie3);
        enemies.add(vampire1);

        Character c = new Character(position);
        c.setCurrentHealth(1);

        Battle enemiesBattle = new Battle(c, enemies);
        enemiesBattle.tickBattle();
        
        assertEquals(enemiesBattle.tickBattle(), ENEMY_WIN);
    }

    @Test
    public void testWithAllies() {
        PathPosition position = setupPosition();
        
        Slug slug = new Slug(position);
        double slugHP = slug.getMaxHealth();
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(slug);

        Character c = new Character(position);
        double cDmg =c.getBaseDamage();
        Ally ally1 = new Ally();
        Ally ally2 = new Ally();
        double aDmg = ally1.getBaseDamage();
        c.addAlly(ally1);
        c.addAlly(ally2);

        Battle enemiesBattle = new Battle(c, enemies);
        
        enemiesBattle.tickBattle();
        assertEquals(slug.getCurrentHealth(), slugHP - cDmg);

        enemiesBattle.tickBattle();
        assertEquals(slug.getCurrentHealth(), slugHP - cDmg - aDmg);

        enemiesBattle.tickBattle();
        assertEquals(slug.getCurrentHealth(), slugHP - cDmg - 2*aDmg);
        
        enemiesBattle.tickBattle();
        assertEquals(enemiesBattle.tickBattle(), HERO_WIN);
    }
    @Test

    public void testWithZombiesandAllies() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(1,1));
        PathPosition position = new PathPosition(0, orderedPath);

        Zombie zombie1 = new Zombie(position);
        Zombie zombie2 = new Zombie(position);
        Zombie zombie3 = new Zombie(position);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(zombie1);
        enemies.add(zombie2);
        enemies.add(zombie3);

        Character c = new Character(position);
        Ally ally1 = new Ally();
        Ally ally2 = new Ally();
        c.addAlly(ally1);
        c.addAlly(ally2);
        Battle enemiesBattle = new Battle(c, enemies);
        int result = BATTLE_CONTINUES;
        while (result == BATTLE_CONTINUES) {
            result = enemiesBattle.tickBattle();
        }
        assertEquals(result, HERO_WIN);
    }

    @Test

    public void TestExpDrop() {
        PathPosition position = setupPosition();
        Slug slug = new Slug(position);
        Vampire vampire = new Vampire(position);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(slug);
        enemies.add(vampire);

        Character c = new Character(position);
        Battle enemiesBattle = new Battle(c, enemies);
        
        while(enemiesBattle.tickBattle() == BATTLE_CONTINUES) {
        }

        for (Enemy e : enemiesBattle.getDefeatedEnemies()) {
            c.rewardExp(e.getExpReward());
        }
        assertEquals(c.getExpCount(), slug.getExpReward() + vampire.getExpReward());
    }
}
