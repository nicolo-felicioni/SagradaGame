package it.polimi.se2018.view;

import it.polimi.se2018.controller.ViewUpdaterObserver;
import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.model.WindowPatternPosition;
import it.polimi.se2018.model.*;

import java.util.List;

/**
 * @author Nicolò Felicioni
 */

public interface View extends ViewUpdaterObserver {



    void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId);
    void updateToolCard(ToolCard toolCard, int number);
    void updateRoundTrack(RoundTrack roundTrack);
    void updateDraftPool(DraftPool draftPool);
    void updateStatePlayer(String playerId, PlayerState state);
    void updatePlayer(String playerId, int favorTokens, boolean connected);
    void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card);
    void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position);
    void updatePublicObjectiveCard(PublicObjectiveCard card, CardPosition position);
    void updateErrorMessage(String playerId, String message);
    void updateEndGame(List<RankingPlayer> rankingPlayers, List<String> disconnectedPlayerId);

}
