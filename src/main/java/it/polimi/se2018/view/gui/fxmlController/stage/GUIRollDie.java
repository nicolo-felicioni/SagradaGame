package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.DecreaseDieValueGameEvent;
import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;
import it.polimi.se2018.event.game.RerollDraftDieGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIDraftPool;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUIRollDie extends GUIStage{

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
    private void rollDie(MouseEvent event) {
        if(draftPool.getSelectedDie() != null) {
            this.observer.handle(new RerollDraftDieGameEvent(draftPool.getSelectedDie(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Before rolling a die, you should select a die from the draft pool");
            alert.showAndWait();
        }
    }

}
