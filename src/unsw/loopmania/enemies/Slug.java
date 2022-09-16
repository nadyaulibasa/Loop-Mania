package unsw.loopmania.enemies;

import unsw.loopmania.*;

/**
 * The Slug Enemy. It is the standard form of enemy
 */
public class Slug extends Enemy {

    private final int BATTLE_RADIUS = 2;
    private final int SUPPORT_RADIUS = 2;

    public Slug(PathPosition position) {
        super(position);
        super.setImageString("src/images/slug.png");
    }

}
