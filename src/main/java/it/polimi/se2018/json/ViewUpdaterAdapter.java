package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.MyLog;

import java.lang.reflect.Type;
import java.util.logging.Level;
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
