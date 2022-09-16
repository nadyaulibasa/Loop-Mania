package unsw.loopmania.battleStates;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;

/**
 * In this state, the Ally attacks the Character
 */
public class ZombifiedAllyState implements AllyBattleState {
    public ZombifiedAllyState() {
        //
    }

    @Override
    public String attack(Ally a, Enemy e, Character c) {
        double dmg = a.getBaseDamage();
        if (c.hasAllies()) {
            Ally ally = c.getFirstNormalAlly();
            String details = ally.takeDamage(dmg);
            if (ally.isDead()) {
                c.removeAlly(ally);
            }
            return details;
        }
        return c.takeDamage(dmg);
    }
}
