package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.FlipDraftDieGameEvent;
import it.polimi.se2018.event.game.IncreaseDieValueGameEvent;
import it.polimi.se2018.event.game.MoveDieRespectAllRestrictionsGameEvent;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.observer.game.GameEventObserver;
import it.polimi.se2018.view.gui.fxmlController.GUIDraftPool;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUIFlipDie extends  GUIStage{

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
    private void flipDie(MouseEvent event) {
        if(draftPool.getSelectedDie() != null) {
            this.observer.handle(new FlipDraftDieGameEvent(draftPool.getSelectedDie(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Before increasing a die value, you should select a die from the draft pool");
            alert.showAndWait();
        }
    }

}