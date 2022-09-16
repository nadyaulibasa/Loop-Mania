package unsw.loopmania.goals;

import java.util.concurrent.ThreadLocalRandom;

;public class GoldGoal implements Goal {
    private final static int GOLD_MAX = 200;
    private final static int GOLD_MIN = 100;
    private int goldCountGoal;
    private double goldCount;
    
    public GoldGoal() {
        goldCountGoal = ThreadLocalRandom.current().nextInt(GOLD_MIN, GOLD_MAX + 1);
        goldCount = 0;
    }

    /**
     * Checks to if the goal has been reached
     * @return      : boolean if the goal has been reached.
    */
    @Override
    public boolean goalReached() {
        if (goldCount > goldCountGoal) return true;
        return false;
    }

    /**
     * Calculates the progress of the goal being achieved
     * @return      : percentage of the progress made
     */
    @Override
    public double goalProgress() {
        return (goldCount > goldCountGoal) ? 1 : ((double)goldCount/goldCountGoal);
    }

    public void setGoldCount(double gold) {
        goldCount = gold;
    }
}
