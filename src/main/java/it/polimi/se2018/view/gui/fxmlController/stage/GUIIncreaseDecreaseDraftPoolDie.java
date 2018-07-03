package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.DecreaseDieValueGameEvent;
import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIDraftPool;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


public class GUIIncreaseDecreaseDraftPoolDie extends GUIStage{

    @FXML
    GUIDraftPool draftPool;

    /**
     * Set the draft pool.
     * @param draftpool the draft pool.
     */
    public void setDraftpool(DraftPool draftpool) {
        this.draftPool.setDraftPool(draftpool);
    }

    @FXML
    private void increaseDie(MouseEvent event) {
        if(draftPool.getSelectedDie() != null) {
            this.observer.handle(new IncreaseDieValueGameEvent(draftPool.getSelectedDie(), playerId));
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
    private void decreaseDie(MouseEvent event) {
        if(draftPool.getSelectedDie() != null) {
            this.observer.handle(new DecreaseDieValueGameEvent(draftPool.getSelectedDie(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Before decreasing a die value, you should select a die from the draft pool");
            alert.showAndWait();
        }
    }

}
