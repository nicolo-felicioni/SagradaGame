package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.model.PlayerState;
import  it.polimi.se2018.controller.utils.myLog;

import java.lang.reflect.Type;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
class PlayerStateAdapter implements JsonSerializer<PlayerState>, JsonDeserializer<PlayerState> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of player states.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    private myLog myLog;

    /**
     * {@inheritDoc}
     * Deserialize a json element.
     *
     * @return a player state.
     */
    @Override
    public PlayerState deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
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
     * Serialize a player state.
     *
     * @return a JsonElement.
     */
    @Override
    public JsonElement serialize(PlayerState updater, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        String className = updater.getClass().getSimpleName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = jsonSerializationContext.serialize(updater);
        retValue.add(INSTANCE, elem);
        return retValue;
    }
}