package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.PlacementException;

/**
 * author PeiQing Gao
 */
public class BlankSpace extends Space {
    /**
     * Copy Constructor
     * @param blankspace
     */
    public BlankSpace(BlankSpace blankspace){
        super(blankspace);
    }

    /**
     * Constructor with no parameters
     */
    public BlankSpace() {
        super();
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

    @Override
    public boolean equalsSpace(Space space){
        return !space.isColorRestricted() &&
                !space.isValueRestricted() &&
                (this.hasDie() && space.hasDie() && this.getDie().equalsDie(space.getDie()) ||
                        !this.hasDie() && !space.hasDie());
    }

}