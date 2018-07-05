package it.polimi.se2018.controller.updater;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.model.WindowPatternPosition;
import it.polimi.se2018.view.View;

public class WindowPatternUpdater implements ViewUpdaterInterface {

    /**
     * The player identifier.
     */
    private final String playerId;

    /**
     * The window pattern.
     */
    private final WindowPattern windowPattern;

    /**
     * Window pattern position;
     */
    private final WindowPatternPosition position;

    /**
     * Constructor.
     * @param playerId the player identifier.
     * @param windowPattern the window pattern.
     */
    public WindowPatternUpdater(String playerId, WindowPattern windowPattern, WindowPatternPosition position){
        this.playerId = playerId;
        this.windowPattern=windowPattern.cloneWindowPattern();
        this.position = position;
    }

    /**
     * Update a view.
     *
     * @param view the object that have to be modified by this command.
     */
    @Override
    public void update(View view) {
        view.updateWindowPattern(playerId, windowPattern, position);
    }

}
