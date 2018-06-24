package it.polimi.se2018.event.model;

import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.model.PublicObjectiveCard;

/**
 * @author Davide Yi Xian Hu
 */
public class PublicObjectiveCardUpdatedEvent {

    /**
     * The public objective card.
     */
    private PublicObjectiveCard publicObjectiveCard;

    /**
     * The public objective card position.
     */
    private CardPosition position;

    /**
     * Constructor.
     * @param publicObjectiveCard the public objective card.
     * @param position the position of the tool card.
     */
    public PublicObjectiveCardUpdatedEvent(PublicObjectiveCard publicObjectiveCard, CardPosition position){
        this.publicObjectiveCard = publicObjectiveCard;
        this.position = position;
    }

    /**
     * Public objective card getter.
     * @return the public objective card.
     */
    public PublicObjectiveCard getPublicObjectiveCard() {
        return publicObjectiveCard;
    }

    /**
     * Public objective card position getter.
     * @return the public objective card position.
     */
    public CardPosition getPosition() {
        return position;
    }
}
