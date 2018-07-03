package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.event.game.UseToolCardGameEvent;
import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.observer.game.GameEventObserver;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIToolCard extends GridPane {

    /**
     * Player identifier.
     */
    private String playerId;

    /**
     * Game event observers.
     */
    private GameEventObserver observer;

    /**
     * The tool card.
     */
    private ToolCard toolCard;

    /**
     * The tool card position.
     */
    private CardPosition cardPosition;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * True if the tool card shows more options.
     */
    private boolean advancedMode;

    @FXML
    GridPane foreground;

    @FXML
    GridPane show;

    @FXML
    GridPane use;

    /**
     * Constructor.
     */
    public GUIToolCard() {
        this.toolCard = null;
        this.selected = false;
        this.advancedMode = true;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GUIToolCard.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try  {
            loader.load();
            this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(advancedMode)
                    foreground.setVisible(true);
                }
            });
            this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(advancedMode)
                    foreground.setVisible(false);
                }
            });
            show.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Platform.runLater(
                            () -> {
                                if(advancedMode) {
                                    GUIToolCard gui = new GUIToolCard();
                                    gui.setToolCard(toolCard);
                                    gui.setAdvancedMode(false);
                                    Scene scene = new Scene(gui, 300, 420);
                                    scene.getStylesheets().add("css/style.css");
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.initModality(Modality.APPLICATION_MODAL);
                                    stage.setTitle(toolCard.getName());
                                    stage.setResizable(false);
                                    stage.showAndWait();
                                }
                            }
                    );
                }
            });
            use.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(advancedMode) {
                        observer.handle(new UseToolCardGameEvent(cardPosition, playerId));
                    }
                }
            });
            foreground.setVisible(false);
            this.setVisible(false);
        } catch (IOException exception)  {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Set the observer.
     *
     * @param observer the observer.
     */
    public void setObserver(GameEventObserver observer) {
        this.observer = observer;
    }

    /**
     * Set the player identifier;
     *
     * @param playerId the player identifer.
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Set the tool card.
     * @param toolCard the tool card.
     */
    public void setToolCard(ToolCard toolCard) {
        this.toolCard = toolCard.cloneToolCard();
        this.setVisible(true);
        this.refresh();
    }


    /**
     * Set the card position.
     * @param cardPosition the card position.
     */
    public void setCardPosition(CardPosition cardPosition) {
        this.cardPosition = cardPosition;
    }

    /**
     * Set the advanced mode.
     * @param advancedMode the advanced mode.
     */
    public void setAdvancedMode(boolean advancedMode) {
        this.advancedMode = advancedMode;
    }

    /**
     * Return the tool card.
     * @return the tool card.
     */
    public ToolCard getToolCard() {
        return toolCard.cloneToolCard();
    }

    /**
     * Return true if the tool card is selected.
     * @return true if the tool card is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the die is selected.
     * @param selected if the die is selected.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        this.refresh();
    }

    /**
     * Refresh grafically.
     */
    private void refresh() {
        this.getStyleClass().clear();
        if(selected) {
            this.getStyleClass().add("tool-card-selected");
        }else {
            this.getStyleClass().add("tool-card");
        }
        this.getStyleClass().add("tool-card-" + toolCard.getName().toLowerCase().replace(' ', '-'));
    }

}
