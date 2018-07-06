package it.polimi.se2018.json;

import it.polimi.se2018.event.game.GameEvent;

/**
 * @author Davide Yi Xian Hu
 */
class GameEventAdapter extends JsonAdapter<GameEvent> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of game event.
     */
    private static final String PACKAGE = "it.polimi.se2018.event.game.";

    /**
     * Constructor.
     */
    GameEventAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }

}