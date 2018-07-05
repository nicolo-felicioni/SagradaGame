package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.event.game.GameEvent;

import java.lang.reflect.Type;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
class GameEventAdapter implements JsonSerializer<GameEvent>, JsonDeserializer<GameEvent> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of game event.
     */
    private static final String PACKAGE = "it.polimi.se2018.event.game.";
    /**
     * {@inheritDoc}
     * Deserialize a json element.
     *
     * @return a game event.
     */
    @Override
    public GameEvent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = PACKAGE + prim.getAsString();
        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
            throw new JsonParseException(e.getMessage());
        }
        return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), klass);
    }

    /**
     * {@inheritDoc}
     * Serialize a game event.
     *
     * @return a JsonElement.
     */
    @Override
    public JsonElement serialize(GameEvent updater, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        String className = updater.getClass().getSimpleName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = jsonSerializationContext.serialize(updater);
        retValue.add(INSTANCE, elem);
        return retValue;
    }
}