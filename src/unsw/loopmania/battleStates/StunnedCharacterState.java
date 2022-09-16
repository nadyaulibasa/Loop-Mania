package unsw.loopmania.battleStates;

import unsw.loopmania.Character;
import unsw.loopmania.enemies.Enemy;

/**
 * In this State, the Character cannot attack for 2 rounds;
 */
public class StunnedCharacterState implements CharacterBattleState {
    static final int ROUNDS_EFFECTIVE = 2;
    private int rounds_completed;

    public StunnedCharacterState(){
        rounds_completed = 0;
    }

    @Override
    public String attack(Character c, Enemy e) {
        if (rounds_completed < ROUNDS_EFFECTIVE) {
            rounds_completed++;
        } else {
            c.setBattleState(new NormalCharacterState());
        }
        return "Character is Stunned. Did  not attack\n";
    }
}
