package unsw.loopmania;

import java.util.Random;

/**
 * A class for the amount of Doge Coin the character has
 */
public class Dogecoin {
    private int total;
    private double itemPrice;
    
    public Dogecoin() {
        this.total = 0;
    }

    public int getCoin() {
        return total;
    }

    public void addCoin(int amount) {
        total += amount;
    }

    public void useCoin(int amount) {
        total -= amount;
    }

    public double getItemPrice() {
        double rand = (new Random()).nextDouble();
        if (rand < 0.2) {
            return itemPrice / 30;
        } else if (rand < 0.4) {
            return itemPrice * 5;
        } else if (rand < 0.6) {
            return itemPrice / 5;
        } else if (rand < 0.8) {
            return itemPrice * 15;
        } else {
            return itemPrice;
        }
    }

    public void setItemPrice(double price) {
        this.itemPrice = price;
    }
}
