package unsw.loopmania.battleStates;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;
import unsw.loopmania.weapons.Weapon;

/**
 * In this state, the Character attacks Enemies
 */
public class NormalCharacterState implements CharacterBattleState {

    public NormalCharacterState(){
        //
    }

    @Override
    public String attack(Character c, Enemy e) {
        List<Weapon> weapons = c.getInventory().getEquippedWeapon();
        double damage = c.getBaseDamage();
        for (Weapon weapon: weapons) {
            damage += weapon.getDamage(e);
            weapon.applyWeaponEffect(e);
        }
        c.applyDefenses(c);
        String damageDetails = e.takeDamage(damage);
        c.resetCharacterStats();
        String attackDetails = c.getEnemyDetails() + "is now attacking";
        return attackDetails + "\n" + damageDetails;
    }
}
