package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.RerollDraftDieGameEvent;
import it.polimi.se2018.event.game.SwapDraftDieWithDiceBagDieGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.view.gui.fxmlController.GUIDraftPool;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUISwapDieDraftPoolDiceBag extends GUIStage {

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
    private void returnDie(MouseEvent event) {
        if(draftPool.getSelectedDie() != null) {
            this.observer.handle(new SwapDraftDieWithDiceBagDieGameEvent(draftPool.getSelectedDie(), playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Before returning a die to the dice, you should select a die from the draft pool");
            alert.showAndWait();
        }
    }

}
