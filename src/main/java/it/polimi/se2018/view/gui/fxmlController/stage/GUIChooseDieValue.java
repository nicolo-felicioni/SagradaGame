package it.polimi.se2018.view.gui.fxmlController.stage;

import it.polimi.se2018.event.game.ChooseDraftDieValueGameEvent;
import it.polimi.se2018.event.game.RerollDraftDieGameEvent;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DieColor;
import it.polimi.se2018.model.DieValue;
import it.polimi.se2018.view.gui.fxmlController.GUIDie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class GUIChooseDieValue extends  GUIStage{

    /**
     * The drafted die;
     */
    private Die draftedDie;

    /**
     * The chosen die value;
     */
    private DieValue value;

    @FXML
    GridPane die;

    public void setDie(Die die) {
        this.draftedDie = die.cloneDie();
        this.value = die.getValue();
        GUIDie guiDie = new GUIDie(draftedDie);
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseOne(MouseEvent event) {
        this.value = DieValue.ONE;
        GUIDie guiDie = new GUIDie(new Die(draftedDie.getColor(), DieValue.ONE));
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseTwo(MouseEvent event) {
        this.value = DieValue.TWO;
        GUIDie guiDie = new GUIDie(new Die(draftedDie.getColor(), DieValue.TWO));
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseThree(MouseEvent event) {
        this.value = DieValue.THREE;
        GUIDie guiDie = new GUIDie(new Die(draftedDie.getColor(), DieValue.THREE));
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseFour(MouseEvent event) {
        this.value = DieValue.FOUR;
        GUIDie guiDie = new GUIDie(new Die(draftedDie.getColor(), DieValue.FOUR));
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseFive(MouseEvent event) {
        this.value = DieValue.FIVE;
        GUIDie guiDie = new GUIDie(new Die(draftedDie.getColor(), DieValue.FIVE));
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseSix(MouseEvent event) {
        this.value = DieValue.SIX;
        GUIDie guiDie = new GUIDie(new Die(draftedDie.getColor(), DieValue.SIX));
        this.die.add(guiDie, 0, 0, 1, 1);
        GridPane.setHgrow(guiDie, Priority.ALWAYS);
        GridPane.setVgrow(guiDie, Priority.ALWAYS);
    }

    @FXML
    private void chooseValue(MouseEvent event) {
        this.observer.handle(new ChooseDraftDieValueGameEvent(draftedDie, value, playerId));
        container.close();
    }
}
