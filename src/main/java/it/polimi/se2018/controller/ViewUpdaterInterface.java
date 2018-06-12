package it.polimi.se2018.controller;

import it.polimi.se2018.view.View;

import java.io.Serializable;

/**
 * @author davide yi xian hu
 */
public interface ViewUpdaterInterface extends Serializable {

	/**
	 * Update a view.
	 * @param view the object that have to be modified by this command.
	 */
	void update(View view);
}
