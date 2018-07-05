package it.polimi.se2018.view.gui.stage;

import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.gui.GUIWindowPattern;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUIMoveDieIgnoreValue extends GUIStage{

    @FXML
    GUIWindowPattern firstPattern;

    @FXML
    GUIWindowPattern secondPattern;

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
        if(firstPattern.getSelectedPosition() != null && secondPattern.getSelectedPosition() != null && firstPattern.getSelectedSpace().hasDie() &&
                secondPattern.getWindowPattern().isPlaceableIgnoreValue(firstPattern.getSelectedSpace().getDie(), secondPattern.getSelectedPosition())) {
            this.observer.handle(new MoveDieIgnoreValueRestrictionGameEvent(
                    firstPattern.getSelectedPosition(), secondPattern.getSelectedPosition(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Move die error");
            alert.setHeaderText("Input information not complete");
            alert.setContentText("Select from the right window the die you want to move, from the right window the final position of the selected die.");
            alert.showAndWait();
        }
    }

}