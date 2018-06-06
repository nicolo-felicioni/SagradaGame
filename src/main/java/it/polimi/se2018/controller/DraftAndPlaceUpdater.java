package it.polimi.se2018.controller;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;
import it.polimi.se2018.view.View;

public class DraftAndPlaceUpdater implements ViewUpdaterInterface {

    private Die draftedDie;
    private Point point;
    private String playerId;

    public DraftAndPlaceUpdater(Die draftedDie, Point point, String playerId){
        this.draftedDie= new Die(draftedDie);
        this.point=point;
        this.playerId=playerId;
    }

    @Override
    public void update(View view) {
        view.updateMoveDieFromDraftToWindow(point, draftedDie, playerId);
    }
}
