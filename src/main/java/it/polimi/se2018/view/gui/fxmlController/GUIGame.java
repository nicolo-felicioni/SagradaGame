package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.*;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUIGame {

    /**
     * Player identifier.
     */
    private String playerId;

    /**
     * Game event observers.
     */
    private GameEventObserver observer;

    /**
     * the draft pool
     */
    @FXML
    GUIDraftPool draftpool;
    /**
     * the roundt track
     */
    @FXML
    GUIRoundTrack roundtrack;
    /**
     * the window pattern
     */
    @FXML
    GUIWindowPattern windowpattern;
    /**
     * the tool card on the left
     */
    @FXML
    GUIToolCard toolcardLeft;
    /**
     * the tool card on the center
     */
    @FXML
    GUIToolCard toolcardCenter;
    /**
     * the tool card on the right
     */
    @FXML
    GUIToolCard toolcardRight;
    /**
     * the public objective card on the left
     */
    @FXML
    GUIPublicObjectiveCard publiccardLeft;
    /**
     * the public objective card on the center
     */
    @FXML
    GUIPublicObjectiveCard publiccardCenter;
    /**
     * the public objective card on the right
     */
    @FXML
    GUIPublicObjectiveCard publiccardRight;

    /**
     * method for handle on click on the end turn button
     * @param event
     */
    public void endTurn(javafx.event.ActionEvent event) {
    }

    /**
     * method for handle on click on the private object card button
     * @param event
     */
    public void privateObjectCard(javafx.event.ActionEvent event) {
    }

    /**
     * method for handle on click on the use tool card button
     * @param event
     */
    public void useToolCard(javafx.event.ActionEvent event) {
    }

    /**
     * method for handle on click on the show enemy window pattern button
     * @param event
     */
    public void showEnemyWindowPattern(javafx.event.ActionEvent event) {
    }

    /**
     * Set the player identifier;
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
        refresh();
    }

    /**
     * Set the observer.
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
        refresh();
    }

    /**
     * Set the draft pool.
     * @param draftpool the draft pool.
     */
    public void setDraftpool(DraftPool draftpool) {
        this.draftpool.setDraftPool(draftpool);
    }

    /**
     * Set the round track.
     * @param roundtrack the round track.
     */
    public void setRoundtrack(RoundTrack roundtrack) {
        this.roundtrack.setRoundTrack(roundtrack);
    }

    /**
     * Set the window pattern.
     * @param windowpattern the window pattern.
     */
    public void setWindowpattern(WindowPattern windowpattern) {
        this.windowpattern.setWindowPattern(windowpattern);
    }

    /**
     * Set the tool card.
     * @param toolcard the tool card.
     * @param position the tool card position.
     */
    public void setToolCard(ToolCard toolcard, CardPosition position) {
        if (position == CardPosition.LEFT) {
            this.toolcardLeft.setToolCard(toolcard);
        } else if (position == CardPosition.CENTER) {
            this.toolcardCenter.setToolCard(toolcard);
        } else if (position == CardPosition.RIGHT) {
            this.toolcardRight.setToolCard(toolcard);
        }
    }

    public void setPublicCard(PublicObjectiveCard publicCard, CardPosition position) {
        if (position == CardPosition.LEFT) {
            this.publiccardLeft.setPublicObjectiveCard(publicCard);
        } else if (position == CardPosition.CENTER) {
            this.publiccardCenter.setPublicObjectiveCard(publicCard);
        } else if (position == CardPosition.RIGHT) {
            this.publiccardRight.setPublicObjectiveCard(publicCard);
        }
    }

    private void refresh() {

    }

}
