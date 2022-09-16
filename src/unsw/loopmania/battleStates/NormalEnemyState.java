package unsw.loopmania.battleStates;

import unsw.loopmania.Ally;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;

/**
 * In this state, the Enemy attacks The Character or any allies if any
 */
public class NormalEnemyState implements EnemyBattleState {

    public NormalEnemyState() {
        //
    }

    @Override
    public String attack(Enemy eAttack, Enemy eReceive, Character c) {
        double dmg = eAttack.getBaseDamage();
        if (c.hasAllies()) {
            Ally ally = c.getFirstNormalAlly();
            String details = ally.takeDamage(dmg);
            if (ally.isDead()) {
                c.removeAlly(ally);
            }
            return details;
        }
        // Then if no allies, Character takes damage
        return c.takeDamage(dmg);
    }
}
