package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.subject_observer.CharacterCyclesObserver;
import unsw.loopmania.subject_observer.CharacterCyclesSubject;

public class CharacterCycleCounter implements CharacterCyclesSubject {

    private int cycleCounter;
    private List<CharacterCyclesObserver> observersList;
    private Character theCharacter;

    public CharacterCycleCounter(Character theCharacter) {
        this.cycleCounter = 0;
        this.observersList = new ArrayList<CharacterCyclesObserver>();
        this.theCharacter = theCharacter;
    }

    public Character getTheCharacter(){
        return this.theCharacter;
    }

    public void checkCycle(){
        int indexInPath = this.theCharacter.getIndexInPath();

        if (indexInPath == 0){
            cycleCounter++;
            if (cycleCounter > 0){
                this.notifyObservers();
            }
        }

    }

    @Override
    public void attachObserver(CharacterCyclesObserver observer) {
        observersList.add(observer);
    }

    @Override
    public void detachObserver(CharacterCyclesObserver observer) {
        observersList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (CharacterCyclesObserver observer : observersList){
            observer.updateCycles(this);
        }
    }

    @Override
    public int getCharacterNumberOfCycles() {
        return this.cycleCounter;
    }

    public List<CharacterCyclesObserver> getObserversList() {
        return observersList;
    }
}
