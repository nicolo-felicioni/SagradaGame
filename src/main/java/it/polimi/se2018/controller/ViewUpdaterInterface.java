package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface ViewUpdaterInterface {

	/**
	 * Update a view.
	 * @param controller the object that have to be modified by this command.
	 */
	void update(Controller controller);
}
