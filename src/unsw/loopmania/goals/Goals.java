package unsw.loopmania.goals;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.enemies.Boss;

/**
 * Contains all the goals currently being set in the Game
 * If all the goals are achieved, then the human character wins the game.
 */
public class Goals {
    private List<Goal> activeGoals;

    public Goals() {
        activeGoals = new ArrayList<>();
        activeGoals.add(new GoldGoal());
        activeGoals.add(new ExpGoal());
        activeGoals.add(new BossGoal());
    }

    /**
     * Calculates the progress of the gold goal being achieved
     * @return      : percentage of the progress made
     */
    public double getGoldProgress() {
        for (Goal g : activeGoals) {
            if (g instanceof GoldGoal) {
                return g.goalProgress();
            }
        }
        return 0;
    }

    /**
     * Calculates the progress of the exp goal being achieved
     * @return      : percentage of the progress made
     */
    public double getExpProgress() {
        for (Goal g : activeGoals) {
            if (g instanceof ExpGoal) {
                return g.goalProgress();
            }
        }
        return 0;
    }

    /**
     * Calculates the progress of the boss goal being achieved
     * @return      : percentage of the progress made
     */
    public double getBossProgress() {
        for (Goal g : activeGoals) {
            if (g instanceof BossGoal) {
                return g.goalProgress();
            }
        }
        return 0;
    }

    public void addToGoldGoal(double gold) {
        for (Goal g : activeGoals) {
            if (g instanceof GoldGoal) {
                ((GoldGoal) g).setGoldCount(gold);
            }
        }
    }
    public void addToExpGoal(int exp) {
        for (Goal g : activeGoals) {
            if (g instanceof ExpGoal) {
                ((ExpGoal) g).addToExpCount(exp);
            }
        }
    }
    public void addToBossGoal(Boss b) {
        for (Goal g : activeGoals) {
            if (g instanceof BossGoal) {
                ((BossGoal) g).addBoss(b);
            }
        }
    }
}
