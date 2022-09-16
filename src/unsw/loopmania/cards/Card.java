package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.buildings.*;
import unsw.loopmania.weapons.*;
import unsw.loopmania.defenses.*;
import unsw.loopmania.miscItems.HealthPotion;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.javatuples.Pair;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity {

    /**
     * Strategy to check if the placement of the card to the tiles to spawn buildings
     * is in the correct placement
     */
    public CardPlacementStrategy cardPlacementStrategy;

    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    /**
     * Spawns a new building according to card type
     */
    public abstract Building spawnBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);

    /**
     * Setter for the Card Placement strategy
     */
    public void setStrategy(CardPlacementStrategy s) {
        cardPlacementStrategy = s;
    }
    
    /**
     * Check if nodeX,nodeY card placement is correct according to its strategy
     * @param nodeX
     * @param nodeY
     * @param orderedPath
     * @return  true if in correct placement
     *          false if not
     */
    public boolean correctPlacement(int nodeX, int nodeY, List<Pair<Integer, Integer>> orderedPath) {
        return cardPlacementStrategy.cardPlacement(nodeX, nodeY, orderedPath);
    }

    /**
     * Returns a random card type
     */
    public static Card randomCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new BarracksCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));
        cards.add(new CampfireCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));
        cards.add(new TowerCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));
        cards.add(new TrapCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));
        cards.add(new VampireCastleCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));
        cards.add(new VillageCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));
        cards.add(new ZombiePitCard(new SimpleIntegerProperty(), new SimpleIntegerProperty()));

        Random rand = new Random();
        Card randomCard = cards.get(rand.nextInt(cards.size()));

        return randomCard;
    }

    /**
     * // When a card is destroyed, the character 
        // receives a random choice of gold/health potions/equipments
     * @param character
     */
    public static void destroyCardGain(Character character) {
        Random r1 = new Random();
        int randInt = r1.nextInt(2);

        if (randInt == 0) {         
            // Character gains 10 gold
            character.addGold(10);
        } else if (randInt == 1) {
            // Character gains a health potion
            HealthPotion hp = new HealthPotion(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            character.equipItem(hp);
        } else {
            // Character gains a random weapon or defense equipment
            List<Item> equipmentList = new ArrayList<>();
            equipmentList.add(new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            equipmentList.add(new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            equipmentList.add(new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            equipmentList.add(new Armour(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            equipmentList.add(new Shield(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            equipmentList.add(new Helmet(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
            
            Random r2 = new Random();
            Item equipment = equipmentList.get(r2.nextInt(equipmentList.size()));
            character.equipItem(equipment);
        }
    }

}
