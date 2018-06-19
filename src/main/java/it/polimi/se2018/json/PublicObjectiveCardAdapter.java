package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.model.PublicObjectiveCard;
import it.polimi.se2018.model.ToolCard;

import java.lang.reflect.Type;

class PublicObjectiveCardAdapter implements JsonSerializer<PublicObjectiveCard>, JsonDeserializer<PublicObjectiveCard> {

    /**
     * ClassName
     */
    private static final String CLASSNAME = "Player state type";

    /**
     * Instance
     */
    private static final String INSTANCE = "Values";

    /**
     * Package of public objectiva cards.
     */
    private static final String PACKAGE = "it.polimi.se2018.model.";

    /**
     * {@inheritDoc}
     * Deserialize a json element.
     *
     * @return a public objectiva card.
     */
    @Override
    public PublicObjectiveCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = PACKAGE + prim.getAsString();
        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
        return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), klass);
    }

    /**
     * {@inheritDoc}
     * Serialize a public objectiva card.
     *
     * @return a JsonElement.
     */
    @Override
    public JsonElement serialize(PublicObjectiveCard updater, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        String className = updater.getClass().getSimpleName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement elem = jsonSerializationContext.serialize(updater);
        retValue.add(INSTANCE, elem);
        return retValue;
    }
}