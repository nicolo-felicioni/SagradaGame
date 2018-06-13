package it.polimi.se2018.view;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.controller.updater.WindowPatternPosition;
import it.polimi.se2018.model.*;

/**
 * @author Nicolò Felicioni
 */

public interface View extends ViewUpdaterObserver{



    void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId);
    void updateToolCard(ToolCard toolCard, int number);
    void updateRoundTrack(RoundTrack roundTrack);
    void updateDraftPool(DraftPool draftPool);
    void updateStatePlayer(String playerId, PlayerState state);
    void updatePlayer(String playerId);
    void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card);
    void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position);


}
