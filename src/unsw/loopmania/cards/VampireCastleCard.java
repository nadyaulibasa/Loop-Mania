package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.*;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/vampire_castle_card.png");
        setStrategy(new NonPathTilesAdjacent());
    }

    @Override
    public Building spawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new VampireCastle(x, y);
    }
}
