package it.polimi.se2018.view.gui;

import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
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

    @FXML
    GridPane roundTrackSecondary;

    @FXML
    GridPane roundTrackMain;

    /**
     * Constructor.
     */
    public GUIRoundTrack() {
        this.guiDice = new ArrayList<>();
        this.guiSelectedRoundDice = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIRoundTrack.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try  {
            loader.load();
            roundTrackMain.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
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
     * Return the round track.
     * @return the round track.
     */
    public RoundTrack getRoundTrack() {
        return roundTrack.cloneRoundTrack();
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
        this.guiDice = new ArrayList<>();
        this.guiSelectedRoundDice = new ArrayList<>();
        roundTrackMain.getChildren().clear();
        roundTrackSecondary.getChildren().clear();
        for (int i = 0; i < roundTrack.getRound() - 1; i++) {
            try {
                GUIDie guiDie = new GUIDie(roundTrack.getDice(i + 1).get(0));
                guiDice.add(guiDie);
                roundTrackMain.add(guiDie, i, 0, 1, 1);
                GridPane.setHgrow(guiDie, Priority.ALWAYS);
                GridPane.setVgrow(guiDie, Priority.ALWAYS);
            } catch ( NotValidRoundException e) {
            }
        }
    }

    private void refreshSelectedRoundDice() {
        try {
            List<Die> dice = roundTrack.getDice(selectedRound + 1);
            roundTrackSecondary.getChildren().clear();
            for (int i = 0; i < dice.size(); i++) {
                GUIDie guiDie = null;
                guiDie = new GUIDie(dice.get(i));
                guiSelectedRoundDice.add(guiDie);
                roundTrackSecondary.add(guiDie, i, 0, 1, 1);
                GridPane.setHgrow(guiDie, Priority.ALWAYS);
                GridPane.setVgrow(guiDie, Priority.ALWAYS);
            }
        } catch ( NotValidRoundException e) {
        }
    }
}
