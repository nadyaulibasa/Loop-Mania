package unsw.loopmania.battleStates;

import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.Character;

/**
The state of The Character that determines how it attacks in battle
 */
public interface CharacterBattleState {

    /**
     * The Character performs an attack
     * @param c     The Character attacking
     * @param e     The Enemy being attacked
     * @return
     */
    public String attack(Character c, Enemy e);
}
