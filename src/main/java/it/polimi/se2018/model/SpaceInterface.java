package it.polimi.se2018.model;

import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.exceptions.SpaceNotOccupiedException;

/**
 * @author davide yi xian hu
 */
public interface SpaceInterface{
	/**
	 * Place a die on this space.
	 *
	 * @param die the die that have to be placed on this space.
	 */
	void placeDie(Die die) throws PlacementException;

	/**
	 * Place a die on this space. Ignore color restrictions.
	 *
	 * @param die the die that have to be placed on this space.
	 */
	void placeDieIgnoreColor(Die die) throws PlacementException ;

	/**
	 * Place a die on this space. Ignore value restrictions.
	 *
	 * @param die the die that have to be placed on this space.
	 */
	void placeDieIgnoreValue(Die die) throws PlacementException;

	/**
	 * Remove the die from this space.
	 *
	 * @return the die that has been removed.
	 */
	Die removeDie() throws SpaceNotOccupiedException;

	/**
	 * Getter of the die.
	 *
	 * @return the die on this space.
	 */
	Die getDie();

	/**
	 * Check if a die can be placed on this space.
	 *
	 * @param die the die that have to be checked if it's possible to be placed on this space.
	 * @return true if the die can be placed, false otherwise.
	 */
	boolean isPlaceable(Die die);

	/**
	 * Check if this space is color restricted.
	 *
	 * @return true if this space is color restricted, false otherwise.
	 */
	boolean isColorRestricted();

	/**
	 * Check if this space is value restricted.
	 *
	 * @return true if this space is value restricted, false otherwise.
	 */
	boolean isValueRestricted();

	/**
	 * Getter of the color restriction of this space.
	 *
	 * @return the color of the restriction of this space.
	 */
	DieColor getColorRestriction();

	/**
	 * Getter of the value restriction of this space.
	 *
	 * @return the value of the restriction of this space.
	 */
	DieValue getValueRestriction();

	/**
	 * Check if this space has a die.
	 *
	 * @return true if this space has a die, false otherwise.
	 */
	boolean hasDie();


}