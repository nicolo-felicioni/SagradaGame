package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ColorRestrictionViolatedException;
import it.polimi.se2018.exceptions.PlacementException;

/**
 * @author Nicolò Felicioni
 */

public class ColorSpace extends Space {

	/**
	 *  the color of the space
	 */
	private DieColor color;

	/**
	 * constructor.
	 * @param color the color of the space
	 */
	public ColorSpace(DieColor color) {
		this.color=color;
	}


	/**
	 *Copy constructor.
	 * @param colorSpace the space to be copied
	 */
	public ColorSpace(ColorSpace colorSpace){
		if(colorSpace.hasDie())
			this.die=colorSpace.getDie();
	    this.color= colorSpace.getColorRestriction();

    }


	/**
	 * Places a die in the space, checking the space conditions.
	 * @param die the die to be placed in this space
	 * @throws PlacementException if the die can't be placed in this space
	 */
	@Override
	public void placeDie(Die die) throws PlacementException{

		//checks the color restriction
		if(respectAllRestrictions(die))
			super.placeDie(die);
		else
			//color restriction violated
			throw new ColorRestrictionViolatedException("This space has color:" + this.color +
				"the die has color:" + die.getColor());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void placeDieIgnoreColor(Die die) throws PlacementException {
		super.placeDie(die);
	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public void placeDieIgnoreValue(Die die) throws PlacementException {
		this.placeDie(die);
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public boolean respectAllRestrictions(Die die) {
		return this.getColorRestriction() == die.getColor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isColorRestricted() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValueRestricted() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DieColor getColorRestriction() {
		return color;
	}

	/**
	 * {@inheritDoc}
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
		return new ColorSpace(this);
	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public boolean equalsSpace(Space space){

		return space.isColorRestricted() &&
				this.getColorRestriction() == space.getColorRestriction() &&
				( space.hasDie() && this.hasDie() && this.getDie().equalsDie(space.getDie()) ||
						!space.hasDie() && !this.hasDie() );

	}
}