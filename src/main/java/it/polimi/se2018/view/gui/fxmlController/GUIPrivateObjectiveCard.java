package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.PrivateObjectiveCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUIPrivateObjectiveCard extends Pane {

    /**
     * The private objective card.
     */
    private PrivateObjectiveCard privateObjectiveCard;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Constructor.
     */
    public GUIPrivateObjectiveCard( ) {
        this.privateObjectiveCard = null;
        this.selected = false;
    }

    /**
     * Set the private objective card.
     *
     * @param privateObjectiveCard the private objective card.
     */
    public void setPrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard) {
        this.privateObjectiveCard = privateObjectiveCard;
        this.refresh();
    }

    /**
     * Return the private objective card.
     *
     * @return the private objective card.
     */
    public PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }

    /**
     * Return true if the private objective card is selected.
     *
     * @return true if the private objective card is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the private objective card is selected.
     *
     * @param selected if the private objective card is selected.
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
        if (selected) {
            this.getStyleClass().add("private-card-selected");
        } else {
            this.getStyleClass().add("private-card");
        }
        if(privateObjectiveCard != null)
        this.getStyleClass().add("private-card-" + privateObjectiveCard.getColor().toString().toLowerCase());
    }

    /**
     * Return true if the control has been correctly initialized.
     * @return true if the control has been correctly initialized.
     */
    public boolean isInitialized() {
        return privateObjectiveCard != null;
    }

}
