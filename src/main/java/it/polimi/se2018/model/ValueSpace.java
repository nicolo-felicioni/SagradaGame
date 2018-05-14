package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.ColorRestrictionViolatedException;
import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.SpaceAlreadyOccupiedException;
import it.polimi.se2018.exceptions.ValueRestrictionViolatedException;

public class ValueSpace extends Space {

	private DieValue value;

	/**
	 * 
	 * @param value
	 */
	public ValueSpace(Space space,DieValue value){
		super(space);
		this.value= value;
	}
	public ValueSpace(DieValue value) {
		this.value=value;
	}


	public void placeDie(Die die) throws PlacementException{

		//checks the value restriction
		if(respectAllRestrictions(die))
			super.placeDie(die);
		else
			//color restriction violated
			throw new ValueRestrictionViolatedException("This space has value:" + this.value +
					"the die has value:" + die.getValue());
	}

	/**
	 * @param die
	 * @inheritDoc
	 */
	@Override
	public void placeDieIgnoreColor(Die die) throws PlacementException {
		this.placeDie(die);
	}

	/**
	 * @param die
	 * @inheritDoc
	 */
	@Override
	public void placeDieIgnoreValue(Die die) throws PlacementException {
		super.placeDie(die);
	}

	/**
	 * @param die
	 * @inheritDoc
	 */
	@Override
	public boolean respectAllRestrictions(Die die) {
		if (this.getValueRestriction() == die.getValue()){
			return true;
		}else{
			return false;
		}
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
		return true;
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
		return value;
	}

	/**
	 * Clone a space
	 *
	 * @return a clone of this space.
	 */
	@Override
	public Space clone() {
		return new ValueSpace(this, this.value);
	}
}