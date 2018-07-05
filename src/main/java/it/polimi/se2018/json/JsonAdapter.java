package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.controller.utils.MyLog;

import java.lang.reflect.Type;
import java.util.logging.Level;

public class JsonAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T>  {

    /**
     * ClassName
     */
    private String classname = "Player state type";

    /**
     * Instance
     */
    private String instance = "Values";

    /**
     * Package of tool cards.
     */
    private String path = "it.polimi.se2018.model.";

    /**
     * Constructor.
     * @param classname the classname.
     * @param instance the instance.
     * @param path the path.
     */
    public JsonAdapter(String classname, String instance, String path) {
        this.classname = classname;
        this.instance = instance;
        this.path = path;
    }

    /**
     * {@inheritDoc}
     * Deserialize a json element.
     *
     * @return a tool card.
     */
    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(classname);
        String className = path + prim.getAsString();
        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
            throw new JsonParseException(e.getMessage());
        }
        return jsonDeserializationContext.deserialize(jsonObject.get(instance), klass);
    }

    /**
     * {@inheritDoc}
     * Serialize a tool card.
     *
     * @return a JsonElement.
     */
    @Override
    public JsonElement serialize(T updater, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        String className = updater.getClass().getSimpleName();
        retValue.addProperty(classname, className);
        JsonElement elem = jsonSerializationContext.serialize(updater);
        retValue.add(instance, elem);
        return retValue;
    }
}
