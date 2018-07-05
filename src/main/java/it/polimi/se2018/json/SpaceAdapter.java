package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.model.Space;

import java.lang.reflect.Type;
import java.util.logging.Level;

public class SpaceAdapter extends JsonAdapter<Space> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Space";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of the spaces.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * Constructor.
     */
    SpaceAdapter() {
        super(CLASSNAME, INSTANCE, PACKAGE);
    }
}