package unsw.loopmania.enemies;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.battleStates.StunnedCharacterState;

/*
 * Doggie, a Boss Enemy in LoopMania
 * A special boss which spawns the DoggieCoin upon defeat, which randomly fluctuates 
 * in sellable price to an extraordinary extent. 
 * Can stun the character, which prevents the character from making an attack temporarily. 
*/
public class Doggie extends Boss {

    // private final int SPAWN_CYCLE = 20;
    private final int HEALTH = 80;
    private final int BATTLE_RADIUS = 2;
    private final int SUPPORT_RADIUS = 2;
    private double stunChance;

    public Doggie(PathPosition position) {
        super(position);
        super.setImageString("src/images/doggie.png");
        super.setHealth(new Health(HEALTH));
        super.setBattleRadius(BATTLE_RADIUS);
        super.setSupportRadius(SUPPORT_RADIUS);
        stunChance = 0.3;
    }

    /**
     * The Doggie attacks The Character. It has a chance of stunning the Character
     * so that it can no longer attack
     * @param e     The Enemy that the Doggie might attack
     * @param c     The Character that the Doggie might attack
     */
    @Override
    public String attack(Enemy e, Character c) {
        String attackDetails = getEnemyDetails() + " [" + getStateDetails() + "] " + "is now attacking";
        c.applyDefenses(this);
        // check if Stun event happens
        double stunValue = Math.random();
        if (stunValue < stunChance) {
            // stun character
            c.setBattleState(new StunnedCharacterState());
            attackDetails += "\n Critical Attack! Character is now stunned!";
        }
        String damageDetails = getState().attack(this, e, c);
        return attackDetails + "\n" + damageDetails;
    }

    public void setStunChance(double chance) {
        this.stunChance = chance;
    }

    public double getStunChance() {
        return stunChance;
    }
}

