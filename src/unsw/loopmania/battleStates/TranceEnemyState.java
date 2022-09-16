package unsw.loopmania.battleStates;

import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;

/**
 * In this state, the Enemy attacks other enemies
 */
public class TranceEnemyState implements EnemyBattleState {
    public TranceEnemyState(){
        //
    }

    @Override
    public String attack(Enemy eAttack, Enemy eReceive, Character c) {
        double dmg = eAttack.getBaseDamage();
        return eReceive.takeDamage(dmg);
    }
}
