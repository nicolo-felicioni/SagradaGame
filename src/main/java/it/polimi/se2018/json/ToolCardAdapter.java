package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.ToolCard;

import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Davide Yi Xian Hu
 */
class ToolCardAdapter implements JsonSerializer<ToolCard>, JsonDeserializer<ToolCard> {

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
     * {@inheritDoc}
     * Deserialize a json element.
     *
     * @return a tool card.
     */
    @Override
    public ToolCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = PACKAGE + prim.getAsString();
        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, e.getMessage(), e);
            throw new JsonParseException(e.getMessage());
        }
        return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), klass);
    }

    /**
     * {@inheritDoc}
     * Serialize a tool card.
     *
     * @return a JsonElement.
     */
    @Override
    public JsonElement serialize(ToolCard updater, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        String className = updater.getClass().getSimpleName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = jsonSerializationContext.serialize(updater);
        retValue.add(INSTANCE, elem);
        return retValue;
    }
}