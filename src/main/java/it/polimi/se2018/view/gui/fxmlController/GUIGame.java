package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.EndTurnGameEvent;
import it.polimi.se2018.event.game.UseToolCardGameEvent;
import it.polimi.se2018.model.*;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
     * The private objective card.
     */
    private PrivateObjectiveCard privateObjectiveCard;

    /**
     * The player state.
     */
    private PlayerState playerState;

    /**
     * The selected tool card.
     */
    private CardPosition selectedToolCard;

    /**
     * The player.
     */
    private Player player;

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
        if(this.playerState.canEndTurn()) {
            this.observer.handle(new EndTurnGameEvent(playerId));
        }
    }

    public void privateObjectCard(javafx.event.ActionEvent event) throws IOException {
        Platform.runLater(
                () -> {
                    GUIPrivateObjectiveCard guiPrivateObjectiveCard=new GUIPrivateObjectiveCard();
                    guiPrivateObjectiveCard.setPrivateObjectiveCard(this.privateObjectiveCard);
                    Scene scene = new Scene(guiPrivateObjectiveCard,300,420);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Private Objective Card");
                    stage.setResizable(false);
                    stage.show();
                }
        );

    }

    public void showEnemyWindowPattern(javafx.event.ActionEvent event) {
    }

    /**
     * Set the player identifier;
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
        this.toolcardLeft.setPlayerId(playerId);
        this.toolcardCenter.setPlayerId(playerId);
        this.toolcardRight.setPlayerId(playerId);
    }

    /**
     * Set the observer.
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
        this.toolcardLeft.setObserver(observer);
        this.toolcardCenter.setObserver(observer);
        this.toolcardRight.setObserver(observer);
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
            this.toolcardLeft.setCardPosition(CardPosition.LEFT);
        } else if (position == CardPosition.CENTER) {
            this.toolcardCenter.setToolCard(toolcard);
            this.toolcardCenter.setCardPosition(CardPosition.CENTER);
        } else if (position == CardPosition.RIGHT) {
            this.toolcardRight.setToolCard(toolcard);
            this.toolcardRight.setCardPosition(CardPosition.RIGHT);
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

    /**
     * Set the private objective card.
     * @param privateObjectiveCard the private objective card.
     */
    public void setPrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard) {
        this.privateObjectiveCard = privateObjectiveCard;
    }

    /**
     * Set the player state.
     * @param playerState the player state.
     */
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }


    /**
     * Set the player.
     * @param player the player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Window pattern clicked.
     */
    @FXML
    private void handleWindowPatternClicked(MouseEvent event) {
        if(playerState != null && playerState.canPlaceDie() && draftpool.getSelectedDie() != null &&
                windowpattern.getSelectedSpace() != null && observer != null) {
            this.observer.handle(new DraftAndPlaceGameEvent(draftpool.getSelectedDie(), windowpattern.getSelectedPosition(), playerId));
        }
    }

    /**
     * Draft pool clicked.
     */
    @FXML
    private void handleDraftPoolClicked(MouseEvent event) {
        if(playerState.canPlaceDie())
            windowpattern.highlightPlaceableSpaces(draftpool.getSelectedDie());
    }


}
