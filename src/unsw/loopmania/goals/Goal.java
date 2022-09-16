package unsw.loopmania.goals;

/**
 * An interface for a Goal of the human character to achieve in the game
 */
public abstract interface Goal {
    public boolean goalReached();
    public double goalProgress();    
}
