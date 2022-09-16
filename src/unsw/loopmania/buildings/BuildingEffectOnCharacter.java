package unsw.loopmania.buildings;

import unsw.loopmania.Character;

public interface BuildingEffectOnCharacter extends BuildingEffect<Character, Character>{
    /**
     * Applies effect on The Character.
     * @param movingEntity : The Character.
     * @return : The Character.
     */
    public Character applyEffect(Character character);
}
