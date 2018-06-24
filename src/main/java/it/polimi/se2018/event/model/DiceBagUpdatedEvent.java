package it.polimi.se2018.event.model;

import it.polimi.se2018.model.DiceBag;

/**
 * @author Davide Yi Xian Hu
 */
public class DiceBagUpdatedEvent {

    /**
     * The dice bag.
     */
    private DiceBag diceBag;

    /**
     * Constructor.
     * @param diceBag the dice bag.
     */
    public DiceBagUpdatedEvent(DiceBag diceBag) {
        this.diceBag = diceBag.cloneDiceBag();
    }

    /**
     * Dice bag getter.
     * @return the dice bag.
     */
    public DiceBag getDiceBag() {
        return diceBag.cloneDiceBag();
    }
}
