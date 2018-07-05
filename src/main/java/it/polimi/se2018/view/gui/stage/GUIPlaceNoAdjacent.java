package it.polimi.se2018.view.gui.stage;

import it.polimi.se2018.event.game.DraftAndPlaceNoAdjacentGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.view.gui.GUIDraftPool;
import it.polimi.se2018.view.gui.GUIWindowPattern;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUIPlaceNoAdjacent extends GUIStage{

    @FXML
    GUIDraftPool draftPool;

    @FXML
    GUIWindowPattern windowPattern;

    /**
     * Set the draft pool.
     *
     * @param draftpool the draft pool.
     */
    public void setDraftpool(DraftPool draftpool) {
        this.draftPool.setDraftPool(draftpool);
    }

    /**
     * Set the window pattern.
     *
     * @param windowpattern the window pattern.
     */
    public void setWindowPattern(WindowPattern windowpattern) {
        this.windowPattern.setWindowPattern(windowpattern);
    }

    @FXML
    private void placeDie(MouseEvent event) {
        if (draftPool.getSelectedDie() != null && windowPattern.getSelectedSpace() != null &&
                windowPattern.getWindowPattern().isPlaceable(draftPool.getSelectedDie(), windowPattern.getSelectedPosition())) {
            this.observer.handle(new DraftAndPlaceNoAdjacentGameEvent(draftPool.getSelectedDie(), windowPattern.getSelectedPosition(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Draft and place error");
            alert.setHeaderText("Input information not complete");
            alert.setContentText("Dear player, select a draft pool die and a space in the window pattern before clicking the button.");
            alert.showAndWait();
        }
    }

    @FXML
    private void draftPoolClicked(MouseEvent event) {
        if(draftPool.getSelectedDie() != null) {
            windowPattern.highlightPlaceableNoAdjacentSpaces(draftPool.getSelectedDie());
        } else {
            windowPattern.setWindowPattern(windowPattern.getWindowPattern());
        }
    }
}
