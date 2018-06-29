package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

public class GUIRoundTrack extends GridPane{

    /**
     * The round track.
     */
    private RoundTrack roundTrack;

    /**
     * GUIDie components
     */
    private List<GUIDie> guiDice;


    /**
     * GUIDie components
     */
    private List<GUIDie> guiSelectedRoundDice;

    /**
     * The selected die;
     */
    private Die selectedDie;

    /**
     * The selected round;
     */
    private int selectedRound;

    /**
     * Constructor.
     */
    GUIRoundTrack() {
        this.guiDice = new ArrayList<>();
        this.guiSelectedRoundDice = new ArrayList<>();
        this.getStyleClass().add("round-track");
        RowConstraints firstRow = new RowConstraints();
        RowConstraints secondRow = new RowConstraints();
        firstRow.setPercentHeight(50);
        firstRow.setPercentHeight(50);
        this.getRowConstraints().removeAll();
        this.getRowConstraints().add(firstRow);
        this.getRowConstraints().add(secondRow);
        for(int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/10);
            this.getColumnConstraints().add(column);
        }
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                guiDice.forEach(die -> {
                    if(die == event.getTarget()) {
                        setSelectedRound(guiDice.indexOf(die));
                    } else {
                        die.setSelected(false);
                    }
                });
                guiSelectedRoundDice.forEach(die -> {
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
     * Set the round track.
     * @param track the round track.
     */
    public void setRoundTrack(RoundTrack track) {
        this.roundTrack = track.cloneRoundTrack();
        refresh();
    }

    /**
     * Return the selected die.
     * @return the selected die.
     */
    public Die getSelectedDie() {
        return selectedDie.cloneDie();
    }

    /**
     * Return the selected round.
     * @return the selected round.
     */
    public int getSelectedRound() {
        return selectedRound;
    }

    /**
     * Set the selected round.
     * @param round the selected round;
     */
    private void setSelectedRound(int round) {
        this.selectedRound = round;
        this.refreshSelectedRoundDice();
    }

    private void refresh() {
        for (int i = 0; i < roundTrack.getRound() - 1; i++) {
            try {
                System.out.println(roundTrack.getRound());
                GUIDie guiDie = new GUIDie(roundTrack.getDice(i + 1).get(0));
                guiDice.add(guiDie);
                this.add(guiDie, i, 0, 1, 1);
                GridPane.setHgrow(guiDie, Priority.ALWAYS);
                GridPane.setVgrow(guiDie, Priority.ALWAYS);
            } catch (RoundTrackEmptyException | NotValidRoundException e) {
            }
        }
    }

    private void refreshSelectedRoundDice() {
        try {
            List<Die> dice = roundTrack.getDice(selectedRound + 1);
            guiSelectedRoundDice.forEach(d -> getChildren().remove(d));
            for (int i = 0; i < dice.size(); i++) {
                GUIDie guiDie = null;
                guiDie = new GUIDie(dice.get(i));
                guiSelectedRoundDice.add(guiDie);
                this.add(guiDie, i, 1, 1, 1);
                GridPane.setHgrow(guiDie, Priority.ALWAYS);
                GridPane.setVgrow(guiDie, Priority.ALWAYS);
            }
        } catch (RoundTrackEmptyException | NotValidRoundException e) {
        }
    }
}
