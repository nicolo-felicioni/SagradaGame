package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.EndTurnGameEvent;
import it.polimi.se2018.event.game.UseToolCardGameEvent;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class GUIInfo extends GridPane {

    /**
     * Player identifier.
     */
    private String playerId;

    /**
     * Game event observers.
     */
    private GameEventObserver observer;

    /**
     * Player state.
     */
    private PlayerState playerState;

    /**
     * Other player window patterns.
     */
    private Map<String, WindowPattern> windowPatternMap;

    @FXML
    Label username;

    @FXML
    Label tokens;

    @FXML
    Label state;

    @FXML
    GridPane placeDieState;

    @FXML
    GridPane useToolState;

    @FXML
    GridPane endTurnState;

    @FXML
    GridPane showOpponent;

    /**
     * Constructor.
     */
    public GUIInfo() {
        this.playerState = null;
        this.observer = null;
        this.playerId = null;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIInfo.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try  {
            loader.load();
            endTurnState.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (playerState != null && playerState.canEndTurn() && observer != null) {
                        observer.handle(new EndTurnGameEvent(playerId));
                    }
                }
            });
        } catch (IOException exception)  {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Set the observer.
     *
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
    }

    /**
     * Set the player identifier;
     *
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
        this.username.setText(playerId);
    }

    /**
     * Set the player state.
     * @param playerState the player state.
     */
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState.cloneState();
        if (playerState.canUseTool()) {
            useToolState.getStyleClass().remove("ko");
            useToolState.getStyleClass().add("ok");
        } else {
            useToolState.getStyleClass().add("ko");
            useToolState.getStyleClass().remove("ok");
        }
        if (playerState.canPlaceDie()) {
            placeDieState.getStyleClass().remove("ko");
            placeDieState.getStyleClass().add("ok");
        } else {
            placeDieState.getStyleClass().add("ko");
            placeDieState.getStyleClass().remove("ok");
        }
        if (playerState.canEndTurn()) {
            endTurnState.getStyleClass().remove("ko");
            endTurnState.getStyleClass().add("ok");
        } else {
            endTurnState.getStyleClass().add("ko");
            endTurnState.getStyleClass().remove("ok");
        }
        this.state.setText(playerState.getClass().getSimpleName().toUpperCase());
    }

    /**
     * Set player favor tokens.
     * @param tokens the player favor tokens.
     */
    public void setTokens(int tokens) {
        this.tokens.setText(Integer.toString(tokens));
    }
}
