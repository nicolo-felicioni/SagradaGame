package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.PublicObjectiveCard;
import javafx.scene.layout.Pane;

public class GUIPublicObjectiveCard extends Pane {

    /**
     * The public objective card.
     */
    private PublicObjectiveCard publicObjectiveCard;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Constructor.
     */
    public GUIPublicObjectiveCard() {
        this.publicObjectiveCard = null;
        this.selected = false;
    }

    /**
     * Set the public objective card.
     *
     * @param publicObjectiveCard the public objective card.
     */
    public void setPublicObjectiveCard(PublicObjectiveCard publicObjectiveCard) {
        this.publicObjectiveCard = publicObjectiveCard;
        this.refresh();
    }

    /**
     * Return the public objective card.
     *
     * @return the public objective card.
     */
    public PublicObjectiveCard getPublicObjectiveCard() {
        return publicObjectiveCard;
    }

    /**
     * Return true if the public objective card is selected.
     *
     * @return true if the public objective card is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Set if the public objective card is selected.
     *
     * @param selected if the public objective card is selected.
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
            this.getStyleClass().add("public-card-selected");
        } else {
            this.getStyleClass().add("public-card");
        }
        this.getStyleClass().add("public-card-" + publicObjectiveCard.getName().toLowerCase().replace(' ', '-'));
    }

}