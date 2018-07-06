package it.polimi.se2018.json;

import it.polimi.se2018.controller.ViewUpdaterInterface;
/**
 * @author Davide Yi Xian Hu
 */
class ViewUpdaterAdapter extends JsonAdapter<ViewUpdaterInterface> {

	/**
	 * ClassName
	 */
	private static final String CLASSNAME = "View updater";

	/**
	 * Instance
	 */
	private static final String INSTANCE = "Values";

	/**
	 * Package of view updaters.
	 */
	private static final String PACKAGE = "it.polimi.se2018.controller.updater.";

	/**
	 * Constructor.
	 */
	ViewUpdaterAdapter() {
		super(CLASSNAME, INSTANCE, PACKAGE);
	}
}
