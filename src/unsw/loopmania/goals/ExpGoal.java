package unsw.loopmania.goals;

import java.util.concurrent.ThreadLocalRandom;

;public class ExpGoal implements Goal {
    private final static int EXP_MAX = 150;
    private final static int EXP_MIN = 100;
    private int expCountGoal;
    private int expCount;
    
    public ExpGoal() {
        expCountGoal = ThreadLocalRandom.current().nextInt(EXP_MIN, EXP_MAX + 1);
        expCount = 0;
    }

    /**
     * Checks to if the goal has been reached
     * @return      : boolean if the goal has been reached.
    */
    @Override
    public boolean goalReached() {
        if (expCount > expCountGoal) return true;
        return false;
    }

    @Override
    public double goalProgress() {
        return (expCount > expCountGoal) ? 1 : ((double)expCount/expCountGoal);
    }

    public void addToExpCount(int exp) {
        expCount += exp;
    }
}
