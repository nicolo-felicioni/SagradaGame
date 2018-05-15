package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.SpaceAlreadyOccupiedException;

/**
 * author PeiQing Gao
 */
public class BlankSpace extends Space {
    /**
     * Copy Constructor
     * @param space
     */
    public BlankSpace(Space space){
        super(space);
    }

    /**
     * Constructor with no parameters
     */
    public BlankSpace() {

    }

    /**
     * @param die
     * @inheritDoc
     */
    @Override
    public void placeDieIgnoreColor(Die die) throws PlacementException {
        placeDie(die);
    }

    /**
     * @param die
     * @inheritDoc
     */
    @Override
    public void placeDieIgnoreValue(Die die) throws PlacementException {
        placeDie(die);
    }

    /**
     * @param die
     * @inheritDoc
     */
    @Override
    public boolean respectAllRestrictions(Die die) {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isColorRestricted() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isValueRestricted() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DieColor getColorRestriction() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public DieValue getValueRestriction() {
        return null;
    }

    /**
     * Clone a space
     *
     * @return a clone of this space.
     */
    @Override
    public Space cloneSpace() {
        return new BlankSpace(this);
    }
}