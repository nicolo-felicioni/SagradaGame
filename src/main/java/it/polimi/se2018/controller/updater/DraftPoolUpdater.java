package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.view.View;

public class DraftPoolUpdater implements ViewUpdaterInterface {

    /**
     * The draft pool.
     */
    private DraftPool draftPool;

    /**
     * Constructor.
     * @param draftPool the draft pool.
     */
    public DraftPoolUpdater(DraftPool draftPool){
        this.draftPool = draftPool.cloneDraftPool();
    }


    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updateDraftPool(draftPool.cloneDraftPool());
    }
}
