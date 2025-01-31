package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.SpaceAlreadyOccupiedException;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;

public abstract class Space implements SpaceInterface, Cloneable{

	/**
	 * The die placed on this space.
	 */
	protected Die die;

	/**
	 * Constructor with no parameters.
	 */
	protected Space(){
	}

	/**
	 * Copy Constructor.
	 */
	protected Space(Space space){
		if(space.hasDie()) {
			this.die = space.getDie();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void placeDie(Die die) throws PlacementException {
		if(!hasDie()) {

			this.die = die.cloneDie();
		}else{
			throw new SpaceAlreadyOccupiedException("Can not place a die on a occupied space");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Die removeDie() throws SpaceNotOccupiedException {


		if(hasDie()) {
			Die removedDie = this.die.cloneDie();
			this.die = null;

			return new Die(removedDie.getColor(), removedDie.getValue());
		}else{
			throw new SpaceNotOccupiedException("Can not remove a die on a empty space");
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Die getDie() {
		if (hasDie())
			return new Die(die.getColor(), die.getValue());
		else
			return null;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public boolean hasDie() {
		return die != null;
	}


	/**
	 * Copy the fields of a space.
	 *
	 * @param space the space to be copied.
	 */
	protected void copyFieldsFrom(Space space) {
		this.die = space.getDie();
	}

	/**
	 * Clone a space
	 *
	 * @return a clone of this space.
	 */
	public abstract Space cloneSpace();


	public String toString(){
		String message = "Color restriction: " + this.getColorRestriction() +
				"\nValue restriction: " + this.getValueRestriction() ;
		if(this.hasDie())
			message = message + "\ndie color: " + this.getDie().getColor() +
					"\ndie value: " + this.getDie().getValue() ;
		else
			message = message + "\nNo die";

		return message;


	}

}