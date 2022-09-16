package unsw.loopmania.battleStates;

import unsw.loopmania.Ally;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;

/**
 * In this state, the Ally attack Enemies
 */
public class NormalAllyState implements AllyBattleState {

    public NormalAllyState(){
        //
    }

    @Override
    public String attack(Ally a, Enemy e, Character c) {
        double damage = a.getBaseDamage();
        return e.takeDamage(damage);
    }
}
