package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;
import it.polimi.se2018.model.*;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

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
    @FXML
    GUIPrivateObjectiveCard privatecard;
    @FXML
    GUIInfo infobox;

    /**
     * Set the player identifier;
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
        this.toolcardLeft.setPlayerId(playerId);
        this.toolcardCenter.setPlayerId(playerId);
        this.toolcardRight.setPlayerId(playerId);
        this.infobox.setPlayerId(playerId);
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
        this.infobox.setObserver(observer);
    }

    /**
     * Set the draft pool.
     * @param draftpool the draft pool.
     */
    public void setDraftpool(DraftPool draftpool) {
        this.draftpool.setDraftPool(draftpool.cloneDraftPool());
    }

    /**
     * Set the round track.
     * @param roundtrack the round track.
     */
    public void setRoundtrack(RoundTrack roundtrack) {
        this.roundtrack.setRoundTrack(roundtrack.cloneRoundTrack());
    }

    /**
     * Set the window pattern.
     * @param windowpattern the window pattern.
     */
    public void setWindowpattern(WindowPattern windowpattern) {
        this.windowpattern.setWindowPattern(windowpattern.cloneWindowPattern());
    }

    /**
     * Set the tool card.
     * @param toolcard the tool card.
     * @param position the tool card position.
     */
    public void setToolCard(ToolCard toolcard, CardPosition position) {
        if (position == CardPosition.LEFT) {
            this.toolcardLeft.setToolCard(toolcard.cloneToolCard());
            this.toolcardLeft.setCardPosition(CardPosition.LEFT);
        } else if (position == CardPosition.CENTER) {
            this.toolcardCenter.setToolCard(toolcard.cloneToolCard());
            this.toolcardCenter.setCardPosition(CardPosition.CENTER);
        } else if (position == CardPosition.RIGHT) {
            this.toolcardRight.setToolCard(toolcard.cloneToolCard());
            this.toolcardRight.setCardPosition(CardPosition.RIGHT);
        }
        checkToolCard(toolcard);
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
        this.privatecard.setPrivateObjectiveCard(privateObjectiveCard);
    }

    /**
     * Set the player state.
     * @param playerState the player state.
     */
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState.cloneState();
        this.infobox.setPlayerState(playerState.cloneState());
    }


    /**
     * Set the player.
     * @param player the player.
     */
    public void setPlayer(Player player) {
        this.player = player.clonePlayer();
    }

    private void checkToolCard(ToolCard toolCard) {
        if(playerState.canEndTurn() || playerState.canPlaceDie()) {
            try {
                if (toolCard.decreaseDieValue() && toolCard.increaseDieValue()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIIncreaseDecreaseDraftPoolDie.fxml"));
                    Parent root = loader.load();
                    GUIIncreaseDecreaseDraftPoolDie controller = loader.getController();
                    controller.setDraftpool(draftpool.getDraftPool().cloneDraftPool());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 160);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.moveDieIgnoreColor()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDieIgnoreColor.fxml"));
                    Parent root = loader.load();
                    GUIMoveDieIgnoreColor controller = loader.getController();
                    controller.setWindowPattern(windowpattern.getWindowPattern().cloneWindowPattern());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.moveDieIgnoreValue()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDieIgnoreValue.fxml"));
                    Parent root = loader.load();
                    GUIMoveDieIgnoreValue controller = loader.getController();
                    controller.setWindowPattern(windowpattern.getWindowPattern().cloneWindowPattern());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.moveADie()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDie.fxml"));
                    Parent root = loader.load();
                    GUIMoveDie controller = loader.getController();
                    controller.setWindowPattern(windowpattern.getWindowPattern().cloneWindowPattern());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.swapDraftDieWithRoundTrackDie()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUISwapDieRoundTrackDraftPool.fxml"));
                    Parent root = loader.load();
                    GUISwapDieRoundTrackDraftPool controller = loader.getController();
                    controller.setRoundTrack(roundtrack.getRoundTrack().cloneRoundTrack());
                    controller.setDraftpool(draftpool.getDraftPool().cloneDraftPool());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.rerollDraftedDie()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIRollDie.fxml"));
                    Parent root = loader.load();
                    GUIRollDie controller = loader.getController();
                    controller.setDraftpool(draftpool.getDraftPool().cloneDraftPool());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.rerollAllDraftPoolDice()) {
                    this.observer.handle(new RerollAllDraftDiceGameEvent(playerId));
                } else if (toolCard.placeDieAfterFirstTurn()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIDraftPlaceAgain.fxml"));
                    Parent root = loader.load();
                    GUIDraftPlaceAgain controller = loader.getController();
                    controller.setDraftpool(draftpool.getDraftPool().cloneDraftPool());
                    controller.setWindowPattern(windowpattern.getWindowPattern().cloneWindowPattern());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.placeDraftedDieNoAdjacent() && playerState.canPlaceDie()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIPlaceNoAdjacent.fxml"));
                    Parent root = loader.load();
                    GUIPlaceNoAdjacent controller = loader.getController();
                    controller.setDraftpool(draftpool.getDraftPool().cloneDraftPool());
                    controller.setWindowPattern(windowpattern.getWindowPattern().cloneWindowPattern());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.flipDraftedDie()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIFlipDie.fxml"));
                    Parent root = loader.load();
                    GUIFlipDie controller = loader.getController();
                    controller.setDraftpool(draftpool.getDraftPool().cloneDraftPool());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                } else if (toolCard.swapDraftDieWithRoundTrackDie()) {
                    //TODO
                } else if (toolCard.chooseNewDieValue()) {
                    //TODO
                } else if (toolCard.moveTwoDiceMatchColorOnRoundTrack()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDieMatchColor.fxml"));
                    Parent root = loader.load();
                    GUIMoveDieMatchColor controller = loader.getController();
                    controller.setColors(roundtrack.getRoundTrack().getAllDice().stream().map(Die::getColor).distinct().collect(Collectors.toList()));
                    controller.setWindowpattern(windowpattern.getWindowPattern().cloneWindowPattern());
                    controller.setObserver(observer);
                    controller.setPlayerId(playerId);
                    Scene scene = new Scene(root, 720, 480);
                    scene.getStylesheets().add("css/style.css");
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
