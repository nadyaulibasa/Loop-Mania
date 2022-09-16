package unsw.loopmania.goals;

import unsw.loopmania.enemies.*;

/**
 * A goal of the Game to defeat all the Bosses on the map
 */
;public class BossGoal implements Goal {
    private int bossCountGoal;
    private int bossCount;
    private boolean elanMuskeSlain;
    private boolean doggieSlain;
    
    public BossGoal() {
        bossCountGoal = 2;
        bossCount = 0;
        elanMuskeSlain = false;
        doggieSlain = false;
    }

    /**
     * Checks to if the goal has been reached
     * @return      : boolean if the goal has been reached.
    */
    @Override
    public boolean goalReached() {
        if (bossCount >= bossCountGoal) return true;
        return false;
    }

    /**
     * Calculates the progress of the goal being achieved
     * @return      : percentage of the progress made
     */
    @Override
    public double goalProgress() {
        return (bossCount > bossCountGoal) ? 1 : ((double)bossCount/bossCountGoal);
    }
    public void addBoss(Boss b) {
        if (b instanceof ElanMuske && !elanMuskeSlain) {
            bossCount++;
            elanMuskeSlain = true;
        } else if (b instanceof Doggie && !doggieSlain) {
            bossCount++;
            doggieSlain = true;
        }
    }
}
