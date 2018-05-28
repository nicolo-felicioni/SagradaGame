package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface ViewUpdaterObserver {

	/**
	 * Handle the view updater.
	 *
	 * @param updater the updater that have to be notified to the observer.
	 */
	void handle(ViewUpdaterInterface updater);
}
