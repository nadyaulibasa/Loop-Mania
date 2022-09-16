package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.*;

/**
 * A card that can spawn a Tower Building
 */
public class TowerCard extends Card {
    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/tower_card.png");
        setStrategy(new NonPathTilesAdjacent());
    }

    @Override
    public Building spawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new Tower(x, y);
    }
}
