package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.defenses.*;
import unsw.loopmania.enemies.*;
import unsw.loopmania.game_modes.GameModeStrategy;
import unsw.loopmania.game_modes.StandardGameMode;
import unsw.loopmania.goals.Goals;
import unsw.loopmania.miscItems.*;
import unsw.loopmania.rareItems.*;
import unsw.loopmania.rareItems.TheOneRing;
import unsw.loopmania.weapons.*;
import unsw.loopmania.cards.*;
import unsw.loopmania.buildings.*;
import unsw.loopmania.subject_observer.*;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;
    public static final int equippedInventoryWidth = 3;
    public static final int equippedInventoryHeight = 1;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;
    private Shop shop;
    private Battle currentBattle;
    private List<Enemy> enemies;
    private List<Card> cardEntities;
    private List<Building> buildingEntities;
    private Goals goals;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        this.character = new Character(new PathPosition(0, orderedPath));
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        this.shop = new Shop(new StandardGameMode(), character);
        goals = new Goals();
    }

    public Character getCharacter() {
        return character;
    }

    public Shop getShop() {
        return shop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
        this.shop = new Shop(this.shop.getGameMode(), character);
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        nonSpecifiedEntities.add(entity);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    public List<Enemy> possiblySpawnEnemies(){
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<Enemy> spawningEnemies = new ArrayList<>();
        if (pos != null){
            int indexInPath = orderedPath.indexOf(pos);
            Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }

        for (Enemy e : checkEnemySpawners()) {
            enemies.add(e);
            spawningEnemies.add(e);
        }
        return spawningEnemies;
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private void killEnemy(Enemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
        System.err.println("removing enemy..\n");
    }

    /**
     * run the expected battles in the world, based on current world state
     * @return list of enemies which have been killed
     */
    public boolean runBattles() {
        Enemy mainEnemy = null;
        List<Enemy> battlingEnemies = new ArrayList<Enemy>();
        for (Enemy e: enemies){
            // check for battle radius
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            double distanceToCharacter = getDistancetoCharacter(e);
            if (distanceToCharacter <= e.getBattleRadius()){
                // add to fight
                mainEnemy = e;
                battlingEnemies.add(e);
            }
        }

        // if no enemies within their battle radius
        if (battlingEnemies.isEmpty()) return false;
        
        for (Enemy e: enemies) {
            double distanceToCharacter = getDistancetoCharacter(e);
            if ((!battlingEnemies.contains(e)) && distanceToCharacter <= e.getSupportRadius()){
                battlingEnemies.add(e);
            }
        }
        mainEnemy.setSupportEnemies(battlingEnemies);

        if (battlingEnemies.isEmpty()) {
            return false;
        }

        System.err.print("===========\n BATTLE STARTING\n");
        for (Enemy e : battlingEnemies) {
            System.err.print(e.getClass().getSimpleName() + "\n");
        }
        currentBattle = new Battle(character, battlingEnemies);
        return true;
    }
    public void completeBattle() {
        List<Enemy> defeatedEnemies = currentBattle.getDefeatedEnemies();
        for (Enemy e: defeatedEnemies){
            // If Doggie Boss is defeated, spawn a Doggie Coin
            if (e instanceof Doggie) addDoggieCoin();
            // If Elan Muske Boss is defeated, reduce Doggie Coin value
            DoggieCoin.setItemPrice(30);

            character.rewardExp(e.getExpReward());
            goals.addToExpGoal(e.getExpReward());
            if (e instanceof Boss) {
                goals.addToBossGoal((Boss) e);
            } 
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        goals.addToGoldGoal((int)character.getTotalGold());
        currentBattle = null;
    }

    public Card addRandomCard() {
        if (cardEntities.size() >= getWidth()){
            removeCard(0);
        }
        int random = new Random().nextInt(7);
        Card card;
        if (random < 1) {
            card = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        } else if (random < 2) {
            card = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        } else if (random < 3) {
            card = new TowerCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        } else if (random < 4) {
            card = new TrapCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        } else if (random < 5) {
            card = new VillageCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        } else if (random < 6) {
            card = new ZombiePitCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));

        } else {
            card = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        cardEntities.add(card);
        return card;
    } 

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * Adds a new doggie coin to the inventory.
     * @return DoggieCoin
     */
    public DoggieCoin addDoggieCoin() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        // now we insert the new sword, as we know we have at least made a slot available...
        DoggieCoin dg = new DoggieCoin(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        getUnequippedItemList().add(dg);
        return dg;
    }

    public Weapon addEquippedWeapon() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        // now we insert the new sword, as we know we have at least made a slot available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        getEquippedItemList().add(sword);
        return sword;
    }

    public Item addRandomUnequippedItem() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        int random = new Random().nextInt(25);
        if (random < 1) {
            TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(theOneRing);
            return theOneRing;
        } else if (random < 3) {
            HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(healthPotion);
            return healthPotion;
        } else if (random < 5) {
            Stake stake = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(stake);
            return stake;
        } else if (random < 7) {
            Staff staff = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(staff);
            return staff;
        } else if (random < 9) {
            Armour armour = new Armour(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(armour);
            return armour;
        } else if (random < 11) {
            Helmet helmet = new Helmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(helmet);
            return helmet;
        } else if (random < 13) {
            Shield shield = new Shield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(shield);
            return shield;
        } else if (random < 14) {
            Anduril anduril = new Anduril(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(anduril);
            return anduril;
        } else if (random < 15) {
            TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(treeStump);
            return treeStump;
        } else {
            Sword Sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            getUnequippedItemList().add(Sword);
            return Sword;
        }
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        character.moveDownPath();
        moveBasicEnemies();
        // if (getRoundCounter() == 20 || getRoundCounter() == 40) spawnBoss();
    }

    private List<Enemy> checkEnemySpawners() {
        List<Enemy> spawningEnemies = new ArrayList<>(); 
        CharacterCycleCounter counter = character.getCycleCounter();
        for (CharacterCyclesObserver o : counter.getObserversList()) {
            if (o instanceof EnemySpawner) {
                Enemy e = ((EnemySpawner) o).spawnEnemy(orderedPath);
                if (e != null) spawningEnemies.add(e);
            }
        }
        return spawningEnemies;
    }

    /**
     * A method that spawns bosses according to their conditions
     * Spawns Doggie after 20 cycles
     * Spawns Elan Muske after 40 cycles
     * @param item
     */
    public Enemy spawnBoss() {
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        int indexInPath = orderedPath.indexOf(pos);
        int round = getRoundCounter();

        if (round == 20) {
            // Spawns Doggie
            Doggie doggie = new Doggie(new PathPosition(indexInPath, orderedPath));
            enemies.add(doggie);
            return doggie;
        } else if (round == 40) { // && experience > 10000
            // Spawns Elan Muske
            ElanMuske elanMuske = new ElanMuske(new PathPosition(indexInPath, orderedPath));
            enemies.add(elanMuske);
            // Increase price of Doggie Coin
            DoggieCoin.setItemPrice(75);
            return elanMuske;
        }
        return null;
    }

    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        getUnequippedItemList().remove(item);
    }


    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    public Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Entity e: getUnequippedItemList()){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Entity item = getUnequippedItemList().get(index);
        item.destroy();
        getUnequippedItemList().remove(index);
    }

    
    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    public Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        for (Enemy e: enemies){
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition(){
        
        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemies.size() < 2)){
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
            int endNotAllowed = (indexPosition + 3)%orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    public Card getCardByCoordinates(int cardNodeX, int cardNodeY) {
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }
        return card;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = getCardByCoordinates(cardNodeX, cardNodeY);

        // check if card is placed in the correct building placement 
        // if so, return a new copy card back to old slot
        if (!card.correctPlacement(buildingNodeX, buildingNodeY, orderedPath)) return null;

        // now spawn building
        Building newBuilding = card.spawnBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        buildingEntities.add(newBuilding);
        CharacterCycleCounter counter = character.getCycleCounter();
        if (newBuilding instanceof CharacterCyclesObserver) {
            counter.attachObserver((CharacterCyclesObserver) newBuilding);
        }

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);
        return newBuilding;
    }

    public Item convertUnequipToEquipByCoordinates(int x, int y) {
        // start by getting item
        Item item = (Item) getUnequippedInventoryItemEntityByCoordinates(x, y);
        character.equipItem(item);
        removeUnequippedInventoryItem(item);
        return item;
    }

    /** 
     *  Update the gamemode 
     */
    public void setGameMode(GameModeStrategy strat)
    {
        shop.setGameMode(strat);
        character.getInventory().setGameMode(strat);
    }

    public double getDistancetoCharacter(Entity e) {
        return Math.sqrt(Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2));
    }
    
    public List<Item> getUnequippedItemList() {
        return character.getInventory().getUnequippedItemList();
    }
    public List<Item> getEquippedItemList() {
        return character.getInventory().getEquippedItemList();
    }

    public Pair<Integer, Integer> getFirstOrderedPath() {
        return orderedPath.get(0);
    }
    public int getRoundCounter() {
        return character.getCycleCounter().getCharacterNumberOfCycles();
    }
    public Battle getCurrentBattle() {
        return currentBattle;
    }
    public Goals getGoals() {
        return goals;
    }


    public void setItemXY(Item item){
        Pair<Integer,Integer> itemLocation = this.getFirstAvailableSlotForItem();
        item.x().set(itemLocation.getValue0());
        item.y().set(itemLocation.getValue1());
    }
}
