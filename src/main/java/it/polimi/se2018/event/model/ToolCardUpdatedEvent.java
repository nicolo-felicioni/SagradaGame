package it.polimi.se2018.event.model;

import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.model.ToolCard;

/**
 * @author Davide Yi Xian Hu
 */
public class ToolCardUpdatedEvent {

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
    public ToolCardUpdatedEvent(ToolCard toolCard, CardPosition position){
        this.toolCard = toolCard.cloneToolCard();
        this.position = position;
    }

    /**
     * Tool card getter.
     * @return the tool card.
     */
    public ToolCard getToolCard() {
        return toolCard.cloneToolCard();
    }

    /**
     * Tool card position getter.
     * @return the tool card position.
     */
    public CardPosition getPosition() {
        return position;
    }
}
