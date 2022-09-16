package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PrinterURI;

import unsw.loopmania.battleStates.*;
import unsw.loopmania.defenses.*;
import unsw.loopmania.enemies.*;
import unsw.loopmania.miscItems.HealthPotion;
/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private String name;
    private Health health;
    private double baseDamage;
    private Inventory inventory;
    private List<Ally> allies;
    private Gold gold;
    private Dogecoin coin;
    private double totalDefense;
    private CharacterCycleCounter charCounter;
    private CharacterBattleState battleState;
    private int expCount;

    public Character(PathPosition position) {
        super(position);
        this.name = "Hero";
        this.health = new Health(400);
        this.baseDamage = 3;
        this.allies = new ArrayList<>();
        this.inventory = new Inventory();
        this.gold = new Gold();
        this.coin = new Dogecoin();
        this.totalDefense = 0;
        this.charCounter = new CharacterCycleCounter(this);
        this.battleState = new NormalCharacterState();
        this.expCount = 0;
    }

    /**
     * Given an enemy, the Character deal damage based on its based damage
     * 
     * @param enemy -> the enemy being attacked
     */
    public String attack(Enemy enemy) {
        return battleState.attack(this, enemy);
    }

    /**
     * Resets the stats of the character
     */
    public void resetCharacterStats() {
        baseDamage = 3;
        totalDefense = 0;
    }

    /**
     * The character receives a given amount of damage, decreasing their health
     * @param dmg
     */
    public String takeDamage(double dmg) {
        double damageTaken = ((dmg - totalDefense) > 0) ? (dmg-totalDefense) : 0;
        health.decreaseCurrentHealth(damageTaken);
        return getClass().getSimpleName() + " took " + damageTaken + " damage. Now has hp: " + getHealthDetails() + "\n";
    }

    /**
     * The character receives the effects of the defence items that is equipped
     * @param enemy : The enemy attacking the Character
     */
    public void applyDefenses(MovingEntity enemy) {
        List<Defense> defenses = inventory.getEquippedDefense();
        double defenseStat = 0;
        for (Defense defense : defenses) {
            defense.applyDefenseEffect(enemy);
        }
        totalDefense = defenseStat;
    }

    /**
     * Checks if the Characters health has fallen below 1
     * @return true if the Character is dead
     */
    public boolean isDead() {
        if (getCurrentHealth() < 1) return true;
        return false;
    }

    /**
     * Checks if Character has any allies
     * 
     * @return true if the Character has allies
     */
    public boolean hasAllies() {
        return !allies.isEmpty();
    }

    /**
     * Adds ally to Character's allies
     * @param ally -> the ally being added
     */
    public void addAlly(Ally ally) {
        allies.add(ally);
    }

    /**
     * Removes ally from Character's allies
     * @param ally -> the ally being removed
     */
    public void removeAlly(Ally ally) {
        allies.remove(ally);
    }

    /**
     * Removes any allies in a zombified battle state
     */
    public void removeZombiedAllies() {
        List<Ally> nonZombieAllies = new ArrayList<>();
        for (Ally ally : allies) {
            if (!(ally.getState() instanceof ZombifiedAllyState)) {
                nonZombieAllies.add(ally);
            }
        }
        this.allies = nonZombieAllies;
    }
    
    /**
     * Restore character to full health
     */
    public void restoreFullHealth() {
        health.setCurrentHealth(health.getMaxHealth());
    }

    /**
     * Equips an item
     * @param item  : the item being equiped
     */
    public void equipItem(Item item) {
        inventory.equipItem(item);
    }

    /**
     * Adds gold to character 
     * @param amount    : amount of gold being added
     */
    public void addGold(int amount) {
        gold.addGold(amount);
    }

    /**
     * Adds coin to character
     * @param amount    : amount of coin being added
     */
    public void addCoin(int amount) {
        coin.addCoin(amount);
    }

    /**
     * Removes gold from character
     * @param amount    : amount of gold being removed
     */
    public void subtractGold(int amount) {
        gold.useGold(amount);
    }

    /**
     * Removes coin from character
     * @param amount    : amount of coin being removed
     */
    public void subtractCoin(int amount) {
        coin.useCoin(amount);
    }

    /**
     * Adds item to unequipped inventory
     * @param item      : item being added
     */
    public void addItemToInventory(Item item) {
        inventory.addToUnequippedItemList(item);
    }

    @Override
    public void moveDownPath() {
        super.moveDownPath();
        this.charCounter.checkCycle();
    }

    @Override
    public void moveUpPath() {
        super.moveUpPath();
        this.charCounter.checkCycle();
    }

    /**
     * Character consumes a potion
     */
    public void consumePotion() {
        HealthPotion potion = inventory.getPotion();
        if (potion != null) {
            potion.use(this);
        }
    }
    
    /**
     * Gives character experience
     * @param exp   : amount being rewarded
     */
    public void rewardExp(int exp) {
        expCount += exp;
    }

    /**
     * Getters
     */
    public String getName() {return name;}
    public double getMaxHealth() {return health.getMaxHealth();}
    public double getCurrentHealth() {return health.getCurrentHealth();}
    public double getBaseDamage() {return baseDamage;}
    public List<Ally> getAllies() {return allies;}
    public Inventory getInventory() {return inventory;}
    public double getTotalGold(){ return this.gold.getGold();}
    public int getTotalCoin(){ return this.coin.getCoin();}
    public CharacterCycleCounter getCycleCounter(){return charCounter;}
    public double getPercentageHealth() {return health.getPercentageHealth();} 
    public String getEnemyDetails() { return this.getClass().getSimpleName() + getHealthDetails() +  " :";}
    public String getHealthDetails() {return " (" + this.getCurrentHealth() + "/" + getMaxHealth() + ")";}
    public CharacterBattleState getBattleState() {return battleState;}
    public int getExpCount() { return expCount;}
    public Ally getFirstNormalAlly() {
        for (Ally ally : allies) {
            if (ally.getState() instanceof NormalAllyState) {
                return ally;
            }
        }
        return null;
    }
    public List<Ally> getNormalAllies() {
        List<Ally> normalAllies = new ArrayList<>();
        for (Ally ally : allies) {
            if (ally.getState() instanceof NormalAllyState) {
                normalAllies.add(ally);
            }
        }
        return normalAllies;
    }
    public List<Ally> getZombiedAllies() {
        List<Ally> zombiedAllies = new ArrayList<>();
        for (Ally ally : allies) {
            if (ally.getState() instanceof NormalAllyState) {
                zombiedAllies.add(ally);
            }
        }
        return zombiedAllies;
    }
    public int getIndexInPath() {
        return super.getPosition().getCurrentPositionInPath();
    }


    /**
     * Setters
     */
    public void setAllies(List<Ally> allies) {
        this.allies = allies;
    }
    public void setCurrentHealth(int currHealth) {
        health.setCurrentHealth(currHealth);
    }

    public void subtractGold(double amount) {
        gold.useGold(amount);
    }


    public void setBaseDamage(double dmg) {
        baseDamage = dmg;
    }
    public void setBattleState(CharacterBattleState state) {
        battleState = state;
    }


    
    
}
