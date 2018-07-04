package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.MoveDieRespectAllRestrictionsGameEvent;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIWindowPattern;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUIMoveDie extends GUIStage{

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
                secondPattern.highlightPlaceableSpaces(die);
        } else {
            secondPattern.setWindowPattern(firstPattern.getWindowPattern());
        }
    }

    @FXML
    private void moveDie(MouseEvent event) {
        if(firstPattern.getSelectedPosition() != null && secondPattern.getSelectedPosition() != null && firstPattern.getSelectedSpace().hasDie() &&
                secondPattern.getWindowPattern().isPlaceable(firstPattern.getSelectedSpace().getDie(), secondPattern.getSelectedPosition())) {
            this.observer.handle(new MoveDieRespectAllRestrictionsGameEvent(
                    firstPattern.getSelectedPosition(), secondPattern.getSelectedPosition(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Window spaces not selected");
            alert.setHeaderText("Window spaces not selected");
            alert.setContentText("Select a die from the left window and a space in the right window");
            alert.showAndWait();
        }
    }

}
