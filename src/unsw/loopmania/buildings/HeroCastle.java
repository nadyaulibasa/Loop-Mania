package unsw.loopmania.buildings;

import java.io.File;
import javafx.scene.image.Image;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.subject_observer.CharacterCyclesObserver;
import unsw.loopmania.subject_observer.CharacterCyclesSubject;

/**
 * Class of Hero's Castle
 */
public class HeroCastle extends Building implements CharacterCyclesObserver{

    private int cyclesCounter;
    private int nextCycleOpenShop;
    private int openShopCounter;
    private boolean isShopOpen;

    /**
     * HereCastle Constructor, it accepts the following SimpleIntegerProperty parameters: 
     * @param x
     * @param y
     */
    public HeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setImageString("src/images/heros_castle.png");
        this.cyclesCounter = 0;
        this.nextCycleOpenShop = 1;
        this.openShopCounter = 1;
        this.isShopOpen = false;
    }

    /**
     * Checks if can open Shop
     * @return : boolean value
     */
    public boolean openShop(){
        return this.isShopOpen;
    }

    /**
     * Updates cycle counter and calls to update isShopOpen boolean variable.
     */
    @Override
    public void updateCycles(CharacterCyclesSubject subject) {
        this.cyclesCounter = subject.getCharacterNumberOfCycles();
        this.updateIsShopOpen();
    }

    /**
     * Updates isShopOpen boolean variable
     */
    private void updateIsShopOpen(){
        if ((this.cyclesCounter > 0) && 
            Math.floorMod(cyclesCounter, nextCycleOpenShop) == 0){
            this.isShopOpen = true;
            nextCycleOpenShop += (++openShopCounter);
        } else { 
            this.isShopOpen = false;
        }
    }

}
