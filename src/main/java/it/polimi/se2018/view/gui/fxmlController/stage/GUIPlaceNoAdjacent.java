package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.game.DraftAndPlaceNoAdjacentGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIDraftPool;
import it.polimi.se2018.view.gui.fxmlController.GUIWindowPattern;
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
        if (draftPool.getSelectedDie() != null && windowPattern.getSelectedSpace() != null) {
            this.observer.handle(new DraftAndPlaceNoAdjacentGameEvent(draftPool.getSelectedDie(), windowPattern.getSelectedPosition(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Before increasing a die value, you should select a die from the draft pool");
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
