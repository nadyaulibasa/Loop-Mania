package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.*;

/**
 * A card that can spawn a Village Building
 */
public class VillageCard extends Card {
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/village_card.png");
        setStrategy(new PathTiles());
    }

    @Override
    public Building spawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new Village(x, y);
    }
}
