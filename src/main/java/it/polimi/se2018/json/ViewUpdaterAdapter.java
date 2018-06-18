package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.controller.ViewUpdaterInterface;

import java.lang.reflect.Type;

/**
 * @author davide yi xian hu
 */
public class ViewUpdaterAdapter implements JsonSerializer<ViewUpdaterInterface>, JsonDeserializer<ViewUpdaterInterface> {

	/**
	 * ClassName
	 */
	private static final String CLASSNAME = "CLASSNAME";

	/**
	 * Instance
	 */
	private static final String INSTANCE  = "INSTANCE";

	/**
	 * {@inheritDoc}
	 * Deserialize a json element.
	 * @return a View Updater.
	 */
	@Override
	public ViewUpdaterInterface deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
		String className = prim.getAsString();
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
	 * Serialize a view updater.
	 * @return a JsonElement.
	 */
	@Override
	public JsonElement serialize(ViewUpdaterInterface updater, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject retValue = new JsonObject();
		String className = updater.getClass().getName();
		retValue.addProperty(CLASSNAME, className);
		JsonElement elem = jsonSerializationContext.serialize(updater);
		retValue.add(INSTANCE, elem);
		return retValue;
	}
}
