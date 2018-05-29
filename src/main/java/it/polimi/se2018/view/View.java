package it.polimi.se2018.view;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Point;

public interface View {

    void moveDieFromDraftToWindow(Point p, Die draftedDie, String playerId);



}
