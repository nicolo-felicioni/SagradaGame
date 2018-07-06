package it.polimi.se2018.json;

import it.polimi.se2018.model.Space;

public class SpaceAdapter extends JsonAdapter<Space> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Space";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of the spaces.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * Constructor.
     */
    SpaceAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }
}