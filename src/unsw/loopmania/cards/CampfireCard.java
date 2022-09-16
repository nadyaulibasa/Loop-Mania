package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.*;

/**
 * A card that can spawn a Campfire Building
 */
public class CampfireCard extends Card {
    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/campfire_card.png");
        setStrategy(new NonPathTiles());
    }

    @Override
    public Building spawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new Campfire(x, y);
    }
}
