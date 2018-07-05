package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.ValueRestrictionViolatedException;


/**
 * @author Nicolò Felicioni
 */
public class ValueSpace extends Space {

	/**
	 * the value of this space
	 */
	private DieValue value;


	/**
	 * constructor
	 * @param value the value of the space
	 */
	public ValueSpace(DieValue value) {
		this.value=value;
	}


	/**
	 * copy constructor.
	 * @param valueSpace the space to be copied. it has to be a value space
	 */
	public ValueSpace(ValueSpace valueSpace){
	    this.value=valueSpace.value;
	    if(valueSpace.hasDie())
	    	this.die=valueSpace.getDie();
    }

	/**
	 *{@inheritDoc}
	 */
	@Override
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
	 *{@inheritDoc}
	 */
	@Override
	public void placeDieIgnoreColor(Die die) throws PlacementException {
		this.placeDie(die);
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public void placeDieIgnoreValue(Die die) throws PlacementException {
		super.placeDie(die);
	}

	/**
	 *{@inheritDoc}
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
	 *{@inheritDoc}
	 */
	@Override
	public boolean isColorRestricted() {
		return false;
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public boolean isValueRestricted() {
		return true;
	}


	/**
	 *{@inheritDoc}
	 */
	@Override
	public DieColor getColorRestriction() {
		return null;
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public DieValue getValueRestriction() {
		return value;
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public Space cloneSpace() {
		return new ValueSpace(this);
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public boolean equalsSpace(Space space){
		return space.isValueRestricted() &&
				this.getValueRestriction() == space.getValueRestriction() &&
				( space.hasDie() && this.hasDie() && this.getDie().equalsDie(space.getDie()) ||
						!space.hasDie() && !this.hasDie() );
	}

}