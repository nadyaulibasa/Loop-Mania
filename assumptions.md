**Battle**

* The battle will sequence in a turn-based moves. 

* The Character will have the first attack and they will target the nearest enemy. Then, any allies of the Character, will attack with the same logic. 

    * If there are multiple enemies with the same distance, then the character will attack the enemy with the lowest health 

* Then each enemy will attack, with the nearest enemy attacking first. 

    * If there are multiple enemies with the same distance, then the enemy with the lowest health will attack. 

* The battle will end if every enemy/ the character is defeated. 
* During the battle, the Character cannot change its equipped items. 
* Any zombified allies at the end of the battle will be removed
* Any tranced enemies at the end of the battle will be removed
* The battle round happen automatically without input from user
* Elan Muske:
    - during Elan's attack, it will heal all other enemies back to full health
* Doggie:
    - the doggie has a random chance of stunning the character
    - this stun prevents the character from attacking (not allies) for 3 rounds


**Potion** 

* The potion can be consumed any time when moving or during a battle 
* The potion cannot surpass the max health of the Character 
* Once the potion is consumed, it is destroyed. 
* The potion can be consumed if it is in the unequipped inventory

 

**Experience/ Character level** 

* The experience gained by the Character will accumulate and upon reaching certain milestones, will gain a level. 

* With each level, the max health and base damage of the Character will increase linearly. 

* There is no maximum level. 

 

**Capacities** 

* The capacity of the inventory is 20 

* The capacity of cards is 10 

* The capacity of allies is 7 



**Equipping Items**

* The Character can only equip one of each type of armour 
    * I.e one armour, one helmet, one shield 
* The Character can only equip one weapon 
* Each item in the unequipped inventory takes up one space (they do not stack) 

 

**Allied Soldier** 

* Each allied soldier has the same health and damage. 

**Goals**
* Within the creation of the world the goals are created
* The goal and exp goal is randomly generated within a range of values

**Shop**
* There is an option to open the shop when the character passes the Hero's base 
* The Human character can select and purchase all the items they would like if they have the sufficient
* You can purchase rare items

**EnemySpawners**
* Enemy spawners trigger once the Character passes the Hero Castle
* It spawn one enemy of the respective spawner

**Critical Attacks**
* Vampire : 30%
* Zombie : 20%
* Doggie : 30%

