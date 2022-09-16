package unsw.loopmania.buildings;

import unsw.loopmania.*;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The abstract class of a building that at a position on the map
 */
public abstract class Building extends StaticEntity {
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
    }
}
