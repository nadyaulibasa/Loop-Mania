package unsw.loopmania.battleStates;

import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;

/**
The state of an Enemy that determines how it attacks in battle
 */
public interface EnemyBattleState {

    /**
     * The enemy performs an attack
     * 
     * @param eAttack   The enemy attacking
     * @param eReceive  The enemy that might receive an attack
     * @param c         The Character that might receive an attack
     * @return          String -> Details of what happened in the attack
     */
    public String attack(Enemy eAttack, Enemy eReceive, Character c);
}
