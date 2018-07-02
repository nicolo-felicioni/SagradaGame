package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.RerollDraftDieGameEvent;
import it.polimi.se2018.event.game.SwapDraftDieWithRoundTrackDieGameEvent;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class GUISwapDieRoundTrackDraftPool {

    /**
     * Player identifier.
     */
    private String playerId;

    /**
     * Game event observers.
     */
    private GameEventObserver observer;

    @FXML
    GUIDraftPool draftPool;

    @FXML
    GUIRoundTrack roundTrack;

    /**
     * Set the observer.
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
    }

    /**
     * Set the player identifier;
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }


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
                    roundTrack.getSelectedDie(), roundTrack.getSelectedRound(), playerId));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Die not selected error");
            alert.setHeaderText("Die not selected error");
            alert.setContentText("Before increasing a die value, you should select a die from the draft pool");
            alert.showAndWait();
        }
    }

}
