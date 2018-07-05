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


    /**
     * method that updates the view after that a die has been moved from the draft pool to the window pattern
     * @param p the point where the die is placed
     * @param draftedDie the drafted die
     * @param playerId the player id
     */
    void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId);

    /**
     * method that updates the view after a tool card changed
     * @param toolCard the updated tool card
     * @param number the number of the tool card
     */
    void updateToolCard(ToolCard toolCard, int number);

    /**
     * method that updates the view after the round track changes
     * @param roundTrack the updated round track
     */
    void updateRoundTrack(RoundTrack roundTrack);

    /**
     * method that updates the view after the draft pool changed
     *
     * @param draftPool the updated draft pool
     */
    void updateDraftPool(DraftPool draftPool);

    /**
     * method that updates the view after a player's state changed
     * @param playerId the player id of the player that changed his state
     * @param state the updated state
     */
    void updateStatePlayer(String playerId, PlayerState state);

    /**
     * method that updates the view after a player changed any of his parameters.
     * @param playerId the player id of the player that changed
     * @param favorTokens the updated number of favor tokens
     * @param connected the boolean variable that says whether a player is connected or not
     */
    void updatePlayer(String playerId, int favorTokens, boolean connected);

    /**
     * method that updates the view after the private objective card is given to a player
     * @param playerId the player that received the card
     * @param card the card given to the player
     */
    void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card);

    /**
     * method that updates the view after a window pattern changed
     * @param playerId the player id of the one who has the window pattern that changed
     * @param windowPattern the updated window pattern
     * @param position the position of the window pattern
     */
    void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position);

    /**
     * method that updates the view after the public objective card is given
     * @param card the card given
     * @param position the position of the card
     */
    void updatePublicObjectiveCard(PublicObjectiveCard card, CardPosition position);

    /**
     * method that updates the view after an error occurred
     * @param playerId the player id
     * @param message the error message
     */
    void updateErrorMessage(String playerId, String message);

    /**
     * method that updates the view after the game ended
     *
     * this method has (as parameters) a list of players ordered by their points, following the game's rules
     * and it has a list of player ids pertaining to the players that are disconnected.
     *
     * @param rankingPlayers a list of players ordered
     * @param disconnectedPlayerId a list of player ids of the players that are disconnected
     */
    void updateEndGame(List<RankingPlayer> rankingPlayers, List<String> disconnectedPlayerId);

}
