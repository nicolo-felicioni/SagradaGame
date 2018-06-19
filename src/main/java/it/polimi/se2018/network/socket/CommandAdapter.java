package it.polimi.se2018.network.socket;

import com.google.gson.*;
import it.polimi.se2018.event.game.GameEvent;

import java.lang.reflect.Type;

/**
 * @author davide yi xian hu
 */
public class CommandAdapter implements JsonSerializer<GameEvent>, JsonDeserializer<GameEvent> {

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
	 * @return a Command.
	 */
	@Override
	public GameEvent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
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
	 * Serialize a command.
	 * @return a JsonElement.
	 */
	@Override
	public JsonElement serialize(GameEvent command, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject retValue = new JsonObject();
		String className = command.getClass().getName();
		retValue.addProperty(CLASSNAME, className);
		JsonElement elem = jsonSerializationContext.serialize(command);
		retValue.add(INSTANCE, elem);
		return retValue;
	}
}
