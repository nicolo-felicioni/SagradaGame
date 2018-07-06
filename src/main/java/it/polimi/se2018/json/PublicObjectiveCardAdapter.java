package it.polimi.se2018.json;

import it.polimi.se2018.model.PublicObjectiveCard;

/**
 * @author Davide Yi Xian Hu
 */
class PublicObjectiveCardAdapter extends JsonAdapter<PublicObjectiveCard> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of public objectiva cards.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * Constructor.
     */
    PublicObjectiveCardAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }
}