package unsw.loopmania;

public class Health {
    /**
     * represents the Health statistics of a unit
     */
    private double maxHealth;
    private double currentHealth;

    public Health(int health) {
        this.maxHealth = health;
        this.currentHealth = health;
    }

    /**
     * Getters
     */
    public double getMaxHealth() {return maxHealth;}
    public double getCurrentHealth() {return currentHealth;}

    /**
     * Decreases the current health by some given value
     * @param dmg -> the amount currentHealth is being subtracted by
     */
    public void decreaseCurrentHealth(double dmg) {
        this.currentHealth = currentHealth - dmg;
    }

    /**
     * Checks to see if the currentHealth has fallen below 1
     * @return true -> if the currentHealth is below 1
     */
    public boolean isDead() {
        if (currentHealth < 1) {return true;}
        return false;
    }

    public void setCurrentHealth(double health) {
        this.currentHealth = health;
    }
    public double getPercentageHealth() {
        return currentHealth/maxHealth;
    }
}
