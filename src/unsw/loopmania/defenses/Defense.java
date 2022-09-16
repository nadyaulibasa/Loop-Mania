package unsw.loopmania.defenses;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.enemies.Enemy;

/**
 * An interface that applies the effects of a Defence item
 */
public interface Defense {
    public void applyDefenseEffect(MovingEntity enemy);
    public double getDefense(Enemy enemy);
}
