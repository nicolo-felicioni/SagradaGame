package it.polimi.se2018.view.gui.stage;

import it.polimi.se2018.event.game.SwapDraftDieWithRoundTrackDieGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.view.gui.GUIDraftPool;
import it.polimi.se2018.view.gui.GUIRoundTrack;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUISwapDieRoundTrackDraftPool extends GUIStage {

    @FXML
    GUIDraftPool draftPool;

    @FXML
    GUIRoundTrack roundTrack;

    /**
     * Set the draft pool.
     * @param draftpool the draft pool.
     */
    public void setDraftpool(DraftPool draftpool) {
        this.draftPool.setDraftPool(draftpool);
    }

    /**
     * Set the round track.
     * @param roundTrack the round track.
     */
    public void setRoundTrack(RoundTrack roundTrack) {
        this.roundTrack.setRoundTrack(roundTrack);
    }

    @FXML
    private void swapDie(MouseEvent event) {
        if(draftPool.getSelectedDie() != null && roundTrack.getSelectedDie() != null) {
            this.observer.handle(new SwapDraftDieWithRoundTrackDieGameEvent(draftPool.getSelectedDie(),
                    roundTrack.getSelectedDie(), roundTrack.getSelectedRound() + 1, playerId));
            container.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Dear player, please select a die from the draft pool and a die from the round track.");
            alert.showAndWait();
        }
    }

}
