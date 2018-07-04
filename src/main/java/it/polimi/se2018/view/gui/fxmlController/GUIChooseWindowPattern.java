package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.WindowPatternChosenGameEvent;
import it.polimi.se2018.model.PrivateObjectiveCard;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.model.WindowPatternPosition;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class GUIChooseWindowPattern {

    /**
     * Player identifier.
     */
    private String playerId;

    /**
     * Game event observers.
     */
    private GameEventObserver observer;

    /**
     * The selected pattern.
     */
    private WindowPattern selectedPattern;

    /**
     * The private objective card.
     */
    @FXML
    GUIPrivateObjectiveCard privateCard;

    /**
     * First window pattern.
     */
    @FXML
    GUIWindowPattern windowOne;

    /**
     * First window pattern.
     */
    @FXML
    GUIWindowPattern windowTwo;

    /**
     * First window pattern.
     */
    @FXML
    GUIWindowPattern windowThree;

    /**
     * First window pattern.
     */
    @FXML
    GUIWindowPattern windowFour;

    /**
     * The choose button
     */
    @FXML
    Button choose;

    /**
     * Constructor.
     */
    public GUIChooseWindowPattern() {
        this.observer = null;
        this.playerId = null;
        this.selectedPattern = null;
    }

    /**
     * Set window pattern.
     * @param pattern the window pattern.
     */
    void setWindowPattern(WindowPattern pattern, WindowPatternPosition position) {
        if(position.equals(WindowPatternPosition.FIRST)) {
            windowOne.setWindowPattern(pattern.cloneWindowPattern());
        } else if(position.equals(WindowPatternPosition.SECOND)) {
            windowTwo.setWindowPattern(pattern.cloneWindowPattern());
        } else if(position.equals(WindowPatternPosition.THIRD)) {
            windowThree.setWindowPattern(pattern.cloneWindowPattern());
        } else if(position.equals(WindowPatternPosition.FOURTH)) {
            windowFour.setWindowPattern(pattern.cloneWindowPattern());
        }
        refresh();
    }

    /**
     * Set private objective card.
     * @param privateObjectiveCard the private objective card.
     */
    void setPrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard) {
        this.privateCard.setPrivateObjectiveCard(privateObjectiveCard);
        refresh();
    }

    /**
     * Set the player identifier;
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
        refresh();
    }

    /**
     * Set the observer.
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
        refresh();
    }

    /**
     * Select a window pattern
     */
    @FXML
    public void selectFirst(MouseEvent event) {
        selectedPattern = windowOne.getWindowPattern();
        windowOne.setSelected(true);
        windowTwo.setSelected(false);
        windowThree.setSelected(false);
        windowFour.setSelected(false);
    }

    /**
     * Select a window pattern
     */
    @FXML
    public void selectSecond(MouseEvent event) {
        selectedPattern = windowTwo.getWindowPattern();
        windowOne.setSelected(false);
        windowTwo.setSelected(true);
        windowThree.setSelected(false);
        windowFour.setSelected(false);
    }

    /**
     * Select a window pattern
     */
    @FXML
    public void selectThird(MouseEvent event) {
        selectedPattern = windowThree.getWindowPattern();
        windowOne.setSelected(false);
        windowTwo.setSelected(false);
        windowThree.setSelected(true);
        windowFour.setSelected(false);
    }

    /**
     * Select a window pattern
     */
    @FXML
    public void selectFourth(MouseEvent event) {
        selectedPattern = windowFour.getWindowPattern();
        windowOne.setSelected(false);
        windowTwo.setSelected(false);
        windowThree.setSelected(false);
        windowFour.setSelected(true);
    }

    /**
     * Choose a window pattern
     */
    @FXML
    public void chooseWindowPattern(ActionEvent event) {
        if(selectedPattern != null) {
            this.choose.setDisable(true);
            this.observer.handle(new WindowPatternChosenGameEvent(selectedPattern, playerId));
        }
    }

    /**
     * Refresh grafically.
     */
    private void refresh() {
        choose.setDisable(!(playerId != null && observer != null && privateCard.isInitialized() &&
                windowOne.isInitialized() && windowTwo.isInitialized() &&
                windowThree.isInitialized() && windowFour.isInitialized()));
    }

}
