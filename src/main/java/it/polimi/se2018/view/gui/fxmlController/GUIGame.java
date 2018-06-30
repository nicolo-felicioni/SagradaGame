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

    @FXML
    GUIDraftPool draftpool;
    @FXML
    GUIRoundTrack roundtrack;
    @FXML
    GUIWindowPattern windowpattern;
    @FXML
    GUIToolCard toolcardLeft;
    @FXML
    GUIToolCard toolcardCenter;
    @FXML
    GUIToolCard toolcardRight;
    @FXML
    GUIPublicObjectiveCard publiccardLeft;
    @FXML
    GUIPublicObjectiveCard publiccardCenter;
    @FXML
    GUIPublicObjectiveCard publiccardRight;

    public void endTurn(javafx.event.ActionEvent event) {
    }

    public void privateObjectCard(javafx.event.ActionEvent event) {
    }

    public void useToolCard(javafx.event.ActionEvent event) {
    }

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
