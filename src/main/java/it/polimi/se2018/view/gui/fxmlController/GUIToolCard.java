package it.polimi.se2018.view.gui.fxmlController;

import it.polimi.se2018.model.ToolCard;
import javafx.scene.layout.Pane;

public class GUIToolCard extends Pane {

    /**
     * The tool card.
     */
    private ToolCard toolCard;

    /**
     * True if it is selected.
     */
    private boolean selected;

    /**
     * Constructor.
     */
    public GUIToolCard() {
        this.toolCard = null;
        this.selected = false;
    }

    /**
     * Set the tool card.
     * @param toolCard the tool card.
     */
    public void setToolCard(ToolCard toolCard) {
        this.toolCard = toolCard.cloneToolCard();
        this.refresh();
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
