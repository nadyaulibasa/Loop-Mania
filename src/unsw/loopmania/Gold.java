package unsw.loopmania;

/**
 * The Class for the amount of Gold the Character owns
 */
public class Gold {
    private double goldTotal;

    public Gold() {
        this.goldTotal = 30;
    }

    public double getGold() {
        return goldTotal;
    }

    public void addGold(double amount) {
        goldTotal += amount;
    }

    public void useGold(double amount) {
        goldTotal -= amount;
    }
}
