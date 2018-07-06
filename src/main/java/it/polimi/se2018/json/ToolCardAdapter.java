package it.polimi.se2018.json;

import it.polimi.se2018.model.ToolCard;

/**
 * @author Davide Yi Xian Hu
 */
class ToolCardAdapter extends JsonAdapter<ToolCard> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of tool cards.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * Constructor.
     */
    ToolCardAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }
}