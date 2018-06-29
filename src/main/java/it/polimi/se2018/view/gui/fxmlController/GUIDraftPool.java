package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

public class GUIDraftPool extends GridPane{

    /**
     * The draft pool.
     */
    private DraftPool draft;

    /**
     * GUIDie components
     */
    private List<GUIDie> guiDice;

    /**
     * The selected die;
     */
    private Die selectedDie;

    /**
     * Constructor.
     */
    public GUIDraftPool() {
        this.guiDice = new ArrayList<>();
        this.getStyleClass().add("draftpool");
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                guiDice.forEach(die -> {
                    if(die == event.getTarget()) {
                        die.setSelected(!die.isSelected());
                        selectedDie = die.getDie();
                    } else {
                        die.setSelected(false);
                    }
                });
            }
        });
    }

    /**
     * Set the draft pool.
     * @param draft the draft pool.
     */
    public void setDraftPool(DraftPool draft) {
        this.draft = draft.cloneDraftPool();
        List<Die> dice = draft.getAllDice();
        for(int i = 0; i < dice.size(); i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/9);
            this.getColumnConstraints().add(column);
            GUIDie guiDie = new GUIDie(dice.get(i));
            guiDice.add(guiDie);
            this.add(guiDie, i, 0, 1, 1);
            GridPane.setHgrow(guiDie, Priority.ALWAYS);
            GridPane.setVgrow(guiDie, Priority.ALWAYS);
        }
    }

    /**
     * Return the selected die.
     * @return the selected die.
     */
    public Die getSelectedDie() {
        return selectedDie.cloneDie();
    }

}
