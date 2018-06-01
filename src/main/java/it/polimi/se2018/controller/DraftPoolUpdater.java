package it.polimi.se2018.controller;

import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.view.View;

public class DraftPoolUpdater implements ViewUpdaterInterface {

    DraftPool draftPool;

    public DraftPoolUpdater(DraftPool draftPool){

        //TODO - MANCA IL CLONE DELLA DRAFTPOOL
        this.draftPool=draftPool;
    }

    @Override
    public void update(View view) {
        view.updateDraftPool(draftPool);
    }
}
