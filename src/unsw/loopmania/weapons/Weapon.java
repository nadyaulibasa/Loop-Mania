package unsw.loopmania.weapons;

import unsw.loopmania.enemies.Enemy;

/**
 * Interface to apply weaponn's different effects.
 */
public interface Weapon {
    public void applyWeaponEffect(Enemy enemy);
    public double getDamage(Enemy enemy);
}
