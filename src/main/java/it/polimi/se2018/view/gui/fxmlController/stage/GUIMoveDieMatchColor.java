package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.MoveDieIgnoreValueRestrictionGameEvent;
import it.polimi.se2018.event.game.MoveDieMatchColorRoundTrackGameEvent;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIWindowPattern;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class GUIMoveDieMatchColor extends GUIStage{

    /**
     * Round track dice colors.
     */
    private List<DieColor> colors;

    @FXML
    GUIWindowPattern firstPattern;

    @FXML
    GUIWindowPattern secondPattern;

    /**
     * Set the window pattern.
     * @param windowpattern the window pattern.
     */
    public void setWindowpattern(WindowPattern windowpattern) {
        this.firstPattern.setWindowPattern(windowpattern);
        this.secondPattern.setWindowPattern(windowpattern);
        highlightFirstPattern();
    }

    public void setColors(List<DieColor> colors) {
        this.colors = colors;
        highlightFirstPattern();
    }

    private void highlightFirstPattern() {
        if(colors != null && firstPattern.getWindowPattern() != null) {
            firstPattern.highlightDiceMatchColors(colors);
        }
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
        }else{
            highlightFirstPattern();
            secondPattern.setWindowPattern(firstPattern.getWindowPattern());
        }
    }

    @FXML
    private void moveDieMatchColor(MouseEvent event) {
        if(firstPattern.getSelectedPosition() != null && secondPattern.getSelectedPosition() != null) {
            this.observer.handle(new MoveDieMatchColorRoundTrackGameEvent(
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