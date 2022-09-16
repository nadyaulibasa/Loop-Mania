package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.*;


public class BarracksCard extends Card {

    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setImageString("src/images/barracks_card.png");
        setStrategy(new PathTiles());
    }

    @Override
    public Building spawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new Barracks(x, y);
    }
}
