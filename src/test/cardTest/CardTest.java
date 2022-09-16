package test.cardTest;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.cards.*;
import unsw.loopmania.buildings.*;

public class CardTest {
    @Test
    /**
     * Tests if the randomCard method creates a new card
     */
    public void TestRandomCard() {
        Card randCard = Card.randomCard();
        assertTrue(randCard instanceof Card);
    }

    /**
     * Tests if the Character gains gold/a health potion/an equipment
     * after calling the destroyCardGain method
     */
    public void TestDestroyCardGain() {
        // Create character
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();
        orderedPath.add(new Pair<Integer, Integer>(1,1));
        PathPosition position = new PathPosition(0, orderedPath);
        Character character = new Character(position);

        // Calls method
        Card.destroyCardGain(character);

        //
    }
}
