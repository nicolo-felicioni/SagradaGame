package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.DiceBag;
import it.polimi.se2018.view.View;

public class DiceBagUpdater implements ViewUpdaterInterface {

    /**
     * The dice bag.
     */
    private DiceBag diceBag;

    /**
     * Constructor.
     * @param diceBag the dice bag.
     */
    public DiceBagUpdater(DiceBag diceBag) {
        this.diceBag = diceBag.cloneDiceBag();
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        //TODO view update
    }
}
