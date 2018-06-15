package it.polimi.se2018.controller;

/**
 * @author davide yi xian hu
 */
public interface ViewUpdaterObservable {

	/**
	 * Add a view updater observer.
	 *
	 * @param observer the view updater observer.
	 */
	void addObserver(ViewUpdaterObserver observer);

	/**
	 * Remove a view updater observer.
	 *
	 * @param observer the view updater observer.
	 */
	void removeObserver(ViewUpdaterObserver observer);

	/**
	 * Notify a view updater.
	 *
	 * @param updater the view updater to be executed.
	 */
	void notifyObservers(ViewUpdaterInterface updater);

}
