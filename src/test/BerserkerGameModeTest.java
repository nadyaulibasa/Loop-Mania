package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Item;
import unsw.loopmania.defenses.Helmet;
import unsw.loopmania.defenses.Shield;
import unsw.loopmania.weapons.Sword;
import unsw.loopmania.game_modes.BerserkerGameMode;
import unsw.loopmania.game_modes.GameModeStrategy;

public class BerserkerGameModeTest {

    @Test
    public void testConditionsSatisfiedTrue(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> purchasedItems = new ArrayList<Item>();
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));

        assertTrue(newGM.arePurchaseConditionsSatisfied(helmet, purchasedItems));
    }

    @Test
    public void testConditionsSatisfiedFalse(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> purchasedItems = new ArrayList<Item>();
        Shield shield = new Shield(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        Helmet helmet = new Helmet(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        
        
        purchasedItems.add(shield);

        assertFalse(newGM.arePurchaseConditionsSatisfied(helmet, purchasedItems));
    }

    @Test
    public void testConditionsSatisfiedNotDenfense(){
        GameModeStrategy newGM = new BerserkerGameMode();
        List<Item> purchasedItems = new ArrayList<Item>();
        Shield shield = new Shield(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        Sword sword = new Sword(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        
        
        purchasedItems.add(shield);

        assertTrue(newGM.arePurchaseConditionsSatisfied(sword, purchasedItems));
    }



    
}
