package unsw.loopmania.miscItems;

import javafx.beans.property.SimpleIntegerProperty;
import java.util.Random;
import unsw.loopmania.Item;

/**
 * DoggieCoin class
 * A revolutionary asset type, which randomly fluctuates in sellable 
 * price to an extraordinary extent.
 */
public class DoggieCoin extends Item {
    private static double itemPrice = 30;

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/doggiecoin.png");
    }
    
    @Override
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

    public static void setItemPrice(double price) {
        itemPrice = price;
    }
}
