package it.polimi.se2018.view;

import it.polimi.se2018.model.*;

public interface View {


    void moveDieFromDraftToWindow(Point p, Die draftedDie, String playerId);
    void updateToolCard(ToolCard toolCard, int number);
    void updateRoundTrack(RoundTrack roundTrack);
    void updateDraftPool(DraftPool draftPool);
    void updateStatePlayer(String playerId, PlayerState state);


}
