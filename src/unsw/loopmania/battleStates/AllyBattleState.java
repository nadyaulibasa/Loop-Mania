package unsw.loopmania.battleStates;

import unsw.loopmania.Ally;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.Character;

/**
The state of an Ally that determines how it attacks in battle
 */
public interface AllyBattleState {

    /**
     * The Ally performs an attack
     * @param a     The Ally attacking
     * @param e     The Enemy that might receive the attack
     * @param c     The Character that might receive the attack
     * @return
     */
    public String attack(Ally a, Enemy e, Character c);
}
