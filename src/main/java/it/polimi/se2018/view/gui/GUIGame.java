package it.polimi.se2018.view.gui;

import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.RerollAllDraftDiceGameEvent;
import it.polimi.se2018.model.*;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.stage.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
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

    private Scene guiIncreaseDecreaseDraftPoolDieScene;
    private GUIIncreaseDecreaseDraftPoolDie guiIncreaseDecreaseDraftPoolDie;

    private Scene guiMoveDieIgnoreColorScene;
    private GUIMoveDieIgnoreColor guiMoveDieIgnoreColor;

    private Scene guiMoveDieIgnoreValueScene;
    private GUIMoveDieIgnoreValue guiMoveDieIgnoreValue;

    private Scene guiMoveDieScene;
    private GUIMoveDie guiMoveDie;

    private Scene guiSwapDieRoundTrackDraftPoolScene;
    private GUISwapDieRoundTrackDraftPool guiSwapDieRoundTrackDraftPool;

    private Scene guiRollDieScene;
    private GUIRollDie guiRollDie;

    private Scene guiDraftPlaceAgainScene;
    private GUIDraftPlaceAgain guiDraftPlaceAgain;

    private Scene guiPlaceNoAdjacentScene;
    private GUIPlaceNoAdjacent guiPlaceNoAdjacent;

    private Scene guiFlipDieScene;
    private GUIFlipDie guiFlipDie;

    private Scene guiMoveDieMatchColorScene;
    private GUIMoveDieMatchColor guiMoveDieMatchColor;

    private Scene guiSwapDieDraftPoolDiceBagScene;
    private GUISwapDieDraftPoolDiceBag guiSwapDieDraftPoolDiceBag;

    private Scene guiChooseDieValueScene;
    private GUIChooseDieValue guiChooseDieValue;

    /**
     * Constructor.
     */
    public GUIGame() {
        initGUIStages();
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
        this.infobox.setPlayerId(playerId);

        this.guiDraftPlaceAgain.setPlayerId(playerId);
        this.guiFlipDie.setPlayerId(playerId);
        this.guiIncreaseDecreaseDraftPoolDie.setPlayerId(playerId);
        this.guiMoveDie.setPlayerId(playerId);
        this.guiMoveDieIgnoreColor.setPlayerId(playerId);
        this.guiMoveDieIgnoreValue.setPlayerId(playerId);
        this.guiMoveDieMatchColor.setPlayerId(playerId);
        this.guiPlaceNoAdjacent.setPlayerId(playerId);
        this.guiRollDie.setPlayerId(playerId);
        this.guiSwapDieRoundTrackDraftPool.setPlayerId(playerId);
        this.guiChooseDieValue.setPlayerId(playerId);
        this.guiSwapDieDraftPoolDiceBag.setPlayerId(playerId);
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

        this.guiDraftPlaceAgain.setObserver(observer);
        this.guiFlipDie.setObserver(observer);
        this.guiIncreaseDecreaseDraftPoolDie.setObserver(observer);
        this.guiMoveDie.setObserver(observer);
        this.guiMoveDieIgnoreColor.setObserver(observer);
        this.guiMoveDieIgnoreValue.setObserver(observer);
        this.guiMoveDieMatchColor.setObserver(observer);
        this.guiPlaceNoAdjacent.setObserver(observer);
        this.guiRollDie.setObserver(observer);
        this.guiSwapDieRoundTrackDraftPool.setObserver(observer);
        this.guiChooseDieValue.setObserver(observer);
        this.guiSwapDieDraftPoolDiceBag.setObserver(observer);
    }

    /**
     * Set the draft pool.
     * @param draftpool the draft pool.
     */
    public void setDraftpool(DraftPool draftpool) {
        this.draftpool.setDraftPool(draftpool.cloneDraftPool());

        this.guiDraftPlaceAgain.setDraftpool(draftpool);
        this.guiFlipDie.setDraftpool(draftpool);
        this.guiIncreaseDecreaseDraftPoolDie.setDraftpool(draftpool);
        this.guiPlaceNoAdjacent.setDraftpool(draftpool);
        this.guiRollDie.setDraftpool(draftpool);
        this.guiSwapDieRoundTrackDraftPool.setDraftpool(draftpool);
        this.guiSwapDieDraftPoolDiceBag.setDraftpool(draftpool);
        if(draftpool.getDraftedDie() != null)
            this.guiChooseDieValue.setDie(draftpool.getDraftedDie());
    }

    /**
     * Set the round track.
     * @param roundtrack the round track.
     */
    public void setRoundtrack(RoundTrack roundtrack) {
        this.roundtrack.setRoundTrack(roundtrack.cloneRoundTrack());

        this.guiSwapDieRoundTrackDraftPool.setRoundTrack(roundtrack);
        this.guiMoveDieMatchColor.setColors(roundtrack.getAllDice().stream().map(Die::getColor).distinct().collect(Collectors.toList()));
    }

    /**
     * Set the window pattern.
     * @param windowpattern the window pattern.
     */
    public void setWindowpattern(WindowPattern windowpattern) {
        this.windowpattern.setWindowPattern(windowpattern.cloneWindowPattern());

        this.guiDraftPlaceAgain.setWindowPattern(windowpattern);
        this.guiMoveDie.setWindowPattern(windowpattern);
        this.guiMoveDieIgnoreColor.setWindowPattern(windowpattern);
        this.guiMoveDieIgnoreValue.setWindowPattern(windowpattern);
        this.guiMoveDieMatchColor.setWindowpattern(windowpattern);
        this.guiPlaceNoAdjacent.setWindowPattern(windowpattern);
    }

    /**
     * Set other player window patterns.
     * @param windowpattern the window pattern.
     * @param playerId the player identifier.
     */
    public void setWindowpattern(WindowPattern windowpattern, String playerId) {
        this.infobox.setWindowPattern(windowpattern, playerId);
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
        this.infobox.setTokens(player.getTokens());
    }

    private void checkToolCard(ToolCard toolCard) {
        if(playerState.canEndTurn() || playerState.canPlaceDie()) {
            if (toolCard.decreaseDieValue() && toolCard.increaseDieValue()) {
                Stage stage = new Stage();
                guiIncreaseDecreaseDraftPoolDie.setContainer(stage);
                stage.setScene(guiIncreaseDecreaseDraftPoolDieScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.moveDieIgnoreColor()) {
                Stage stage = new Stage();
                guiMoveDieIgnoreColor.setContainer(stage);
                stage.setScene(guiMoveDieIgnoreColorScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.moveDieIgnoreValue()) {
                Stage stage = new Stage();
                guiMoveDieIgnoreValue.setContainer(stage);
                stage.setScene(guiMoveDieIgnoreValueScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.moveADie()) {
                Stage stage = new Stage();
                guiMoveDie.setContainer(stage);
                stage.setScene(guiMoveDieScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.swapDraftDieWithRoundTrackDie()) {
                Stage stage = new Stage();
                guiSwapDieRoundTrackDraftPool.setContainer(stage);
                stage.setScene(guiSwapDieRoundTrackDraftPoolScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.rerollDraftedDie()) {
                Stage stage = new Stage();
                guiRollDie.setContainer(stage);
                stage.setScene(guiRollDieScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.rerollAllDraftPoolDice()) {
                this.observer.handle(new RerollAllDraftDiceGameEvent(playerId));
            } else if (toolCard.placeDieAfterFirstTurn()) {
                Stage stage = new Stage();
                guiDraftPlaceAgain.setContainer(stage);
                stage.setScene(guiDraftPlaceAgainScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.placeDraftedDieNoAdjacent() && playerState.canPlaceDie()) {
                Stage stage = new Stage();
                guiPlaceNoAdjacent.setContainer(stage);
                stage.setScene(guiPlaceNoAdjacentScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.flipDraftedDie()) {
                Stage stage = new Stage();
                guiFlipDie.setContainer(stage);
                stage.setScene(guiFlipDieScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.returnDieAndGetNewFromDiceBag()) {
                Stage stage = new Stage();
                guiSwapDieDraftPoolDiceBag.setContainer(stage);
                stage.setScene(guiSwapDieDraftPoolDiceBagScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.chooseNewDieValue()) {
                Stage stage = new Stage();
                guiChooseDieValue.setContainer(stage);
                stage.setScene(guiChooseDieValueScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
            } else if (toolCard.moveTwoDiceMatchColorOnRoundTrack()) {
                Stage stage = new Stage();
                guiMoveDieMatchColor.setContainer(stage);
                stage.setScene(guiMoveDieMatchColorScene);
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(GUIController.primaryStage);
                stage.showAndWait();
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

    private void initGUIStages() {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIIncreaseDecreaseDraftPoolDie.fxml"));
            guiIncreaseDecreaseDraftPoolDieScene = new Scene(loader.load(), 900, 180);
            guiIncreaseDecreaseDraftPoolDieScene.getStylesheets().add("css/style.css");
            guiIncreaseDecreaseDraftPoolDie = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDieIgnoreColor.fxml"));
            guiMoveDieIgnoreColorScene = new Scene(loader.load(), 920, 560);
            guiMoveDieIgnoreColorScene.getStylesheets().add("css/style.css");
            guiMoveDieIgnoreColor = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDieIgnoreValue.fxml"));
            guiMoveDieIgnoreValueScene = new Scene(loader.load(), 920, 560);
            guiMoveDieIgnoreValueScene.getStylesheets().add("css/style.css");
            guiMoveDieIgnoreValue = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDie.fxml"));
            guiMoveDieScene = new Scene(loader.load(), 920, 560);
            guiMoveDie = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUISwapDieRoundTrackDraftPool.fxml"));
            guiSwapDieRoundTrackDraftPoolScene = new Scene(loader.load(), 500, 430);
            guiSwapDieRoundTrackDraftPoolScene.getStylesheets().add("css/style.css");
            guiSwapDieRoundTrackDraftPool = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIRollDie.fxml"));
            guiRollDieScene = new Scene(loader.load(), 900, 180);
            guiRollDieScene.getStylesheets().add("css/style.css");
            guiRollDie = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIDraftPlaceAgain.fxml"));
            guiDraftPlaceAgainScene = new Scene(loader.load(), 450, 600);
            guiDraftPlaceAgainScene.getStylesheets().add("css/style.css");
            guiDraftPlaceAgain = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIPlaceNoAdjacent.fxml"));
            guiPlaceNoAdjacentScene = new Scene(loader.load(), 450, 600);
            guiPlaceNoAdjacentScene.getStylesheets().add("css/style.css");
            guiPlaceNoAdjacent = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIFlipDie.fxml"));
            guiFlipDieScene = new Scene(loader.load(), 900, 180);
            guiFlipDieScene.getStylesheets().add("css/style.css");
            guiFlipDie = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUISwapDieDraftPoolDiceBag.fxml"));
            guiSwapDieDraftPoolDiceBagScene = new Scene(loader.load(), 900, 180);
            guiSwapDieDraftPoolDiceBagScene.getStylesheets().add("css/style.css");
            guiSwapDieDraftPoolDiceBag = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIChooseDieValue.fxml"));
            guiChooseDieValueScene = new Scene(loader.load(), 720, 150);
            guiChooseDieValueScene.getStylesheets().add("css/style.css");
            guiChooseDieValue = loader.getController();

            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIMoveDieMatchColor.fxml"));
            guiMoveDieMatchColorScene = new Scene(loader.load(), 920, 560);
            guiMoveDieMatchColorScene.getStylesheets().add("css/style.css");
            guiMoveDieMatchColor = loader.getController();

        } catch (IOException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        }
    }

}
