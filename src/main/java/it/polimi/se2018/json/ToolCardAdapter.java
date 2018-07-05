package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.model.ToolCard;

import java.lang.reflect.Type;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
class ToolCardAdapter extends JsonAdapter<ToolCard> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of tool cards.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * Constructor.
     */
    ToolCardAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }
}