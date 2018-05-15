package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ColorRestrictionViolatedException;
import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.SpaceAlreadyOccupiedException;

public class ColorSpace extends Space {

	private DieColor color;

	public ColorSpace(Space space,DieColor color){
	super(space);
	this.color= color;
	}

	/**
	 * 
	 * @param color
	 */
	public ColorSpace(DieColor color) {
		this.color=color;
	}


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
	 * @param die
	 * @inheritDoc
	 */
	@Override
	public void placeDieIgnoreColor(Die die) throws PlacementException {
		super.placeDie(die);
	}

	/**
	 * @param die
	 * @inheritDoc
	 */
	@Override
	public void placeDieIgnoreValue(Die die) throws PlacementException {
		this.placeDie(die);
	}

	/**
	 * @param die
	 * @inheritDoc
	 */
	@Override
	public boolean respectAllRestrictions(Die die) {
		if (this.getColorRestriction() == die.getColor()){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean isColorRestricted() {
		return true;
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
		return color;
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
		return new ColorSpace(this, this.color);
	}
}