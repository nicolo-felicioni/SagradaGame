package it.polimi.se2018.json;

import it.polimi.se2018.model.PlayerState;

/**
 * @author Davide Yi Xian Hu
 */
class PlayerStateAdapter extends JsonAdapter<PlayerState> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of player states.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * Constructor.
     */
    PlayerStateAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }

}