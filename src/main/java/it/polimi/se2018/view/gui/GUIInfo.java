package it.polimi.se2018.view.gui;

import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.event.game.EndTurnGameEvent;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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
        this.windowPatternMap = new HashMap<>();
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
            showOpponent.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Platform.runLater(
                            () -> {
                                    GridPane pane = new GridPane();
                                    RowConstraints row = new RowConstraints();
                                    row.setVgrow(Priority.ALWAYS);
                                    pane.getRowConstraints().add(row);
                                    List<String> keys = new ArrayList<>(windowPatternMap.keySet());
                                    for(int i = 0; i < keys.size(); i++) {
                                        ColumnConstraints column = new ColumnConstraints();
                                        column.setHgrow(Priority.ALWAYS);
                                        pane.getColumnConstraints().add(column);
                                        GUIWindowPattern guiWindowPattern = new GUIWindowPattern();
                                        guiWindowPattern.setWindowPattern(windowPatternMap.get(keys.get(i)));
                                        pane.add(guiWindowPattern, i, 0);
                                    }
                                    Scene scene = new Scene(pane, 300 * keys.size(), 390);
                                    scene.getStylesheets().add("css/style.css");
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.setTitle("WINDOW PATTERNS");
                                    stage.setResizable(false);
                                    stage.show();
                                }
                    );
                }
            });
        } catch (IOException exception)  {
            MyLog.getMyLog().log(Level.WARNING, exception.getMessage());
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
        useToolState.getStyleClass().clear();
        placeDieState.getStyleClass().clear();
        endTurnState.getStyleClass().clear();
        if (playerState.canUseTool()) {
            useToolState.getStyleClass().add("ok");
        } else {
            useToolState.getStyleClass().add("ko");
        }
        if (playerState.canPlaceDie()) {
            placeDieState.getStyleClass().add("ok");
        } else {
            placeDieState.getStyleClass().add("ko");
        }
        if (playerState.canEndTurn()) {
            endTurnState.getStyleClass().add("ok");
        } else {
            endTurnState.getStyleClass().add("ko");
        }
        this.state.setText(playerState.getClass().getSimpleName().toLowerCase());
    }

    /**
     * Set player favor tokens.
     * @param tokens the player favor tokens.
     */
    public void setTokens(int tokens) {
        this.tokens.setText(Integer.toString(tokens));
    }


    public void setWindowPattern(WindowPattern windowPattern, String playerId) {
        if(this.windowPatternMap.containsKey(playerId)) {
            this.windowPatternMap.remove(playerId);
        }
        this.windowPatternMap.put(playerId, windowPattern);
    }



}
