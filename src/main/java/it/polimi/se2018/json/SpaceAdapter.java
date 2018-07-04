package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.model.Space;
import it.polimi.se2018.controller.utils.myLog;

import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceAdapter implements JsonSerializer<Space>, JsonDeserializer<Space> {

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
     * The logger;
     */
    private myLog myLog;

    /**
     * {@inheritDoc}
     * Deserialize a json element.
     *
     * @return a Space.
     */
    @Override
    public Space deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = PACKAGE + prim.getAsString();
        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            myLog.getMyLog().log(Level.WARNING, e.getMessage());
            throw new JsonParseException(e.getMessage());
        }
        return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), klass);
    }

    /**
     * {@inheritDoc}
     * Serialize a space.
     *
     * @return a JsonElement.
     */
    @Override
    public JsonElement serialize(Space updater, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        String className = updater.getClass().getSimpleName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = jsonSerializationContext.serialize(updater);
        retValue.add(INSTANCE, elem);
        return retValue;
    }
}