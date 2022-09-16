package unsw.loopmania.buildings;

import unsw.loopmania.Character;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Village Class - backend with village logic
 */
public class Village extends Building implements BuildingEffect<Character, Character>{
    final private double HEALTH_INCREASE_FACTOR = 1.1; 

    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/village.png");
    }

    /**
     * Applies effect on Character: regains health by a health increase factor
     * @param character : in this case, the Character.
     * @return : The Character.
     */
    @Override
    public Character applyEffect(Character character) {

        // Building and The Character in the same location
        if (character.getX() == this.getX() && character.getY() == this.getY()){
            character.setCurrentHealth((int)(character.getCurrentHealth() * this.HEALTH_INCREASE_FACTOR));
        }
        
        return character;
    }
}
