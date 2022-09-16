
package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;
import unsw.loopmania.enemies.*;

public class Battle {
    private Character character;
    private List<Enemy> enemies;
    private List<Enemy> defeatedEnemies;
    private boolean allEnemiesAreDead;
    private Pair<Integer, Integer> tick;
    private String turnDetails;

    final private int BATTLE_CONTINUES = 0;
    final private int HERO_WIN = 1;
    final private int ENEMY_WIN = 2;


    public Battle(Character character, List<Enemy> enemies) {
        this.character = character;
        this.enemies = enemies;
        this.allEnemiesAreDead = false;
        this.defeatedEnemies = new ArrayList<>();
        tick = new Pair<>(0, 0);
        turnDetails = " ";
    }

    /**
     * Ticks the battle between Hero and Enemies. The battle is completed iteratively
     * in attacks. First, the Character attacks, then the allies, then the enemies.
     * 
     * @return  BATTLE_CONTINUES -> if the battle is not complete
     *          HERO_WIN         -> if all enemies are slain
     *          ENEMY_WIN        -> if the Character is slain
     */
    public int tickBattle() {
        Enemy e = getNextEnemy();
        switch(tick.getKey()) {
            // Character Attack
            case 0:   
                turnDetails = character.attack(e);
                break;
            // Ally attack
            case 1:
                Ally ally = character.getAllies().get(tick.getValue());
                turnDetails = ally.attack(e, character);
                break;
            // Enemy attack
            case 2:
                Enemy enemyAttack = enemies.get(tick.getValue());
                Enemy eReceive = getNextEnemy();
                turnDetails = enemyAttack.attack(eReceive, character);
                break;
            default:
                break;
        }
        getNextEnemy();
        removeDeadAllies();
        getNextTick();
        return battleTickResult();
    }

    /**
     * This returns a random enemy in the list. 
     * It removes any dead enemies first.
     * @return Enemy
     */
    public Enemy getNextEnemy() {
        removeDeadEnemies();
        if (enemies.isEmpty()) {
            this.allEnemiesAreDead = true;
            return null;
        } else {
            return enemies.get(new Random().nextInt(enemies.size()));
        }
    }

    /**
     * This returns if all the enemies that can be fought, are dead
     * @return boolean
     */ 
    public boolean allEnemiesAreDead() {
        return allEnemiesAreDead;
    }

    /**
     * Removes any Enemies that have died
     */
    public void removeDeadEnemies() {
        List<Enemy> remainingEnemies = new ArrayList<>();
        for (Enemy enemy: enemies) {
            if (!enemy.isDead()) {
                remainingEnemies.add(enemy);
            } else {
                turnDetails += enemy.getClass().getSimpleName() + " has been slain!\n";
                this.defeatedEnemies.add(enemy);
            }
        }
        this.enemies = remainingEnemies;
    }

    /**
     * Get all enemies that were slain in the battle
     * @return  : List of enemies
     */
    public List<Enemy> getDefeatedEnemies() {
        return defeatedEnemies;
    }

    /**
     * Gets next tick for the battle. It who is next to perform their attack
     */
    public void getNextTick() {
        switch(tick.getKey()) {
            case 0:
                if (character.hasAllies())  {
                    tick = new Pair<>(1,0);
                    break;
                } else {
                    tick = new Pair<>(2,0);
                    break;
                }
            case 1:
                if (tick.getValue() > character.getAllies().size() -2) {
                    tick = new Pair<>(2,0);
                    break;
                } else {
                    tick = new Pair<>(1, tick.getValue() + 1);
                    break;
                } 
            case 2:
                if (tick.getValue() > enemies.size() - 2) {
                    tick = new Pair<>(0,0);
                    break;
                } else {
                    tick = new Pair<>(2, tick.getValue() + 1);
                    break;
                }
            default:
                return;
        }
    }

    /**
     * Gets the current state of the battle. If it is completed or continuing
     * @return  : The state of the battle
     */
    public int battleTickResult() {
        if (character.isDead()) return ENEMY_WIN;
        else if (allEnemiesAreDead) {
            character.removeZombiedAllies();
            for (Enemy e : enemies) {
                this.defeatedEnemies.add(e);
            }
            return HERO_WIN;
        }
        return BATTLE_CONTINUES;
    }
    /**
     * Removes any allies that have been slain
     */
    public void removeDeadAllies() {
        ArrayList<Ally> remainingAllies = new ArrayList<>();
        for (Ally a : character.getAllies()) {
            if (!a.isDead()) {
                remainingAllies.add(a);
            } else {
                turnDetails += a.getClass().getSimpleName() + " has been slain!\n";
                a.destroy();
            }
        }
        character.setAllies(remainingAllies);
    }

    /**
     * Getters
     */
    public String getTurnDetails() { return turnDetails;}
    public List<Enemy> getEnemies() {return enemies;}
    
}


