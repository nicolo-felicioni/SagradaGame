package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
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

    @FXML
    GridPane draftPool;

    /**
     * Constructor.
     */
    public GUIDraftPool() {
        this.guiDice = new ArrayList<>()
        ;FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIDraftPool.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try  {
            loader.load();
            draftPool.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    guiDice.forEach(die -> {
                        if(die == event.getTarget()) {
                            die.setSelected(!die.isSelected());
                            if(die.isSelected())
                                selectedDie = die.getDie();
                            else
                                selectedDie = null;
                        } else {
                            die.setSelected(false);
                        }
                    });
                }
            });
        } catch (IOException exception)  {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Set the draft pool.
     * @param draft the draft pool.
     */
    public void setDraftPool(DraftPool draft) {
        draftPool.getChildren().clear();
        this.draft = draft.cloneDraftPool();
        List<Die> dice = draft.getAllDice();
        for(int i = 0; i < dice.size(); i++) {
            GUIDie guiDie = new GUIDie(dice.get(i));
            guiDice.add(guiDie);
            draftPool.add(guiDie, i, 0, 1, 1);
            GridPane.setHgrow(guiDie, Priority.ALWAYS);
            GridPane.setVgrow(guiDie, Priority.ALWAYS);
        }
    }

    /**
     * Return the selected die.
     * @return the selected die.
     */
    public Die getSelectedDie() {
        if(selectedDie != null)
            return selectedDie.cloneDie();
        else
            return null;
    }

}
