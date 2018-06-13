package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.ToolCard;
import it.polimi.se2018.view.View;

public class ToolCardUpdater implements ViewUpdaterInterface {

    /**
     * The tool card.
     */
    private ToolCard toolCard;

    /**
     * The tool card position.
     */
    private CardPosition position;

    /**
     * Constructor.
     * @param toolCard the tool card.
     * @param position the position of the tool card.
     */
    public ToolCardUpdater(ToolCard toolCard, CardPosition position){
        this.toolCard = toolCard.cloneToolCard();
        this.position = position;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updateToolCard(toolCard, position.toInt());
    }
}
