package unsw.loopmania.enemies;

import unsw.loopmania.*;
import unsw.loopmania.Character;

/**
 * Elan Muske, a boss enemy in LoopMania
 * Has the ability to heal other enemy NPCs. 
 * Causes the price of DoggieCoin to increase drastically when appears, and plummets the price when defeated.
 */
public class ElanMuske extends Boss {
    private static final int EXP_DROP = 20;

    private final int HEALTH = 100;
    private final int BATTLE_RADIUS = 2;
    private final int SUPPORT_RADIUS = 2;

    public ElanMuske(PathPosition position) {
        super(position);
        super.setImageString("src/images/ElanMuske.png");
        super.setHealth(new Health(HEALTH));
        super.setBattleRadius(BATTLE_RADIUS);
        super.setSupportRadius(SUPPORT_RADIUS);
    }

    /**
     * Elan Muske attacks The Character. In his attack, he heals
     * all support enemies to max health.
     * @param e     The Enemy that Elan might attack
     * @param c     The Charact that might be attacked
     */
    @Override
    public String attack(Enemy e, Character c) {
        // Heal other enemy NPCs
        String healDetails = getEnemyDetails() + " healed ";
        for (Enemy support : getSupportEnemies()) {
            if (!support.equals(this)) support.setMaxHealth();
            healDetails += "\t" + support.getEnemyDetails() + "\n";
        }
        healDetails += "back to full health!\n";

        // Attack character
        String attackDetails = getEnemyDetails() + " [" + getStateDetails() + "] " + "is now attacking";
        c.applyDefenses(this);
        String damageDetails = getState().attack(this, e, c);
        return healDetails + attackDetails + "\n" + damageDetails;
    }
}
