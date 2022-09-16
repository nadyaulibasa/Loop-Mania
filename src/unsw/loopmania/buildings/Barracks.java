package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Ally;
import unsw.loopmania.Character;

/**
 * Barracks Class - backend with barracks logic
 */
public class Barracks extends Building implements BuildingEffect<Ally,Character> {

    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/barracks.png");
    }

    /**
     * Applies effect if the character passes through building : produces new Ally
     * @param movingEntity : in this case, the Character
     * @return : Ally if character passes through the barrack, null if it doesn't.
     */
    @Override
    public Ally applyEffect(Character character) {
        if (character.getX() == this.getX() && character.getY() == this.getY()){
            return new Ally();
        }

        return null;
    }
}
