package unsw.loopmania.buildings;

import unsw.loopmania.Ally;
import unsw.loopmania.Character;

public interface BuildingEffectCreateAlly extends BuildingEffect<Ally, Character>{
    /**
     * Creates a new ally.
     * @param character : The Character.
     * @return : a new Ally.
     */
    public Ally applyEffect(Character character);
}
