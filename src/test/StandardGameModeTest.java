package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.game_modes.StandardGameMode;

public class StandardGameModeTest {
    @Test
    public void testConditionsSatisfied(){
        GameModeStrategy newGM = new StandardGameMode();

        assertTrue(newGM.arePurchaseConditionsSatisfied(null, null));
    }

}
