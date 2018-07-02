package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.MoveDieIgnoreColorRestrictionGameEvent;
import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUIMoveDieIgnoreValue {


    /**
     * Player identifier.
     */
    private String playerId;

    /**
     * Game event observers.
     */
    private GameEventObserver observer;

    @FXML
    GUIWindowPattern firstPattern;

    @FXML
    GUIWindowPattern secondPattern;

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
     * Set the window pattern.
     * @param windowpattern the window pattern.
     */
    public void setWindowPattern(WindowPattern windowpattern) {
        this.firstPattern.setWindowPattern(windowpattern);
        this.secondPattern.setWindowPattern(windowpattern);
    }


    @FXML
    private void firstPatternClicked(MouseEvent event) {
        if(firstPattern.getSelectedSpace() != null) {
            WindowPattern window = firstPattern.getWindowPattern();
            Die die = null;
            if(window.getSpace(firstPattern.getSelectedPosition()).hasDie()) {
                die = window.getSpace(firstPattern.getSelectedPosition()).getDie();
            }
            try {
                window.removeDie(firstPattern.getSelectedPosition());
            } catch (SpaceNotOccupiedException e) {
            }
            secondPattern.setWindowPattern(window);
            if(die != null)
                secondPattern.highlightPlaceableIgnoreValueSpaces(die);
        } else {
            secondPattern.setWindowPattern(firstPattern.getWindowPattern());
        }
    }

    @FXML
    private void moveDieIgnoreValue(MouseEvent event) {
        if(firstPattern.getSelectedPosition() != null && secondPattern.getSelectedPosition() != null) {
            this.observer.handle(new MoveDieIgnoreValueRestrictionGameEvent(
                    firstPattern.getSelectedPosition(), secondPattern.getSelectedPosition(), playerId));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Window spaces not selected");
            alert.setHeaderText("Window spaces not selected");
            alert.setContentText("Select a die from the left window and a space in the right window");
            alert.showAndWait();
        }
    }

}