package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.model.PublicObjectiveCard;
import it.polimi.se2018.view.View;

public class PublicObjectiveCardUpdater implements ViewUpdaterInterface {

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
    public PublicObjectiveCardUpdater(PublicObjectiveCard publicObjectiveCard, CardPosition position){
        this.publicObjectiveCard = publicObjectiveCard;
        this.position = position;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updatePublicObjectiveCard(publicObjectiveCard, position);
    }
}
