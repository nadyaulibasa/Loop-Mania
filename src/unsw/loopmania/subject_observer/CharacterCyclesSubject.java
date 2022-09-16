package unsw.loopmania.subject_observer;

public interface CharacterCyclesSubject {
    /**
     * Attaches an observer to the subject list of observers
     * @param observer
     */
    public void attachObserver(CharacterCyclesObserver observer);

    /**
     * Detaches an observer to the subject list of observers
     * @param observer
     */
    public void detachObserver(CharacterCyclesObserver observer);

    /**
     * Notifies observers
     */
    public void notifyObservers();

    /**
     * Gets the character number of completed cycles
     * @return
     */
    public int getCharacterNumberOfCycles();
}
