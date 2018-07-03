package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.stage.Stage;

public class GUIStage {

    /**
     * Player identifier.
     */
    protected String playerId;

    /**
     * Game event observers.
     */
    protected GameEventObserver observer;

    /**
     * Game event observers.
     */
    protected Stage container;

    /**
     * Set the observer.
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
    }


    /**
     * Set the player identifier;
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Set the container.
     * @param stage the container.
     */
    public void setContainer(Stage stage) {
        this.container = stage;
    }

}
