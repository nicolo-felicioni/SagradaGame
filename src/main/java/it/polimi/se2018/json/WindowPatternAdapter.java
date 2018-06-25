package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.exceptions.UnboundDifficultyValueException;
import it.polimi.se2018.exceptions.WindowPatternDimensionException;
import it.polimi.se2018.model.*;

import java.lang.reflect.Type;

/**
 * @author Davide Yi Xian Hu
 */
class WindowPatternAdapter implements JsonSerializer<WindowPattern>, JsonDeserializer<WindowPattern> {

	private final static String X = "x";
	private final static String Y = "y";
	private final static String COLOR = "color";
	private final static String COLOR_SPACES = "colorSpaces";
	private final static String VALUE = "value";
	private final static String VALUE_SPACES = "valueSpaces";
	private final static String DIFFICULTY = "difficulty";
	private final static String NAME = "name";
	private final static String WINDOW = "window";

	/**
	 * {@inheritDoc}
	 * Deserialize a json element.
	 *
	 * @return a window pattern.
	 */
	@Override
	public WindowPattern deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject windowPattern = jsonElement.getAsJsonObject().getAsJsonObject(WINDOW);
		JsonArray colorSpaces = windowPattern.getAsJsonArray(COLOR_SPACES);
		JsonArray valueSpaces = windowPattern.getAsJsonArray(VALUE_SPACES);
		int difficulty = windowPattern.getAsJsonPrimitive(DIFFICULTY).getAsInt();
		String name = windowPattern.getAsJsonPrimitive(NAME).getAsString();
		Space[][] spaces = new Space[WindowPattern.SPACES_HEIGHT][WindowPattern.SPACES_LENGTH];
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[i].length; j++) {
				spaces[i][j] = new BlankSpace();
			}
		}
		for (JsonElement e : colorSpaces) {
			int x = e.getAsJsonObject().getAsJsonPrimitive(X).getAsInt();
			int y = e.getAsJsonObject().getAsJsonPrimitive(Y).getAsInt();
			DieColor color = DieColor.fromInt(e.getAsJsonObject().getAsJsonPrimitive(COLOR).getAsInt());
			spaces[x][y] = new ColorSpace(color);
		}
		for (JsonElement e : valueSpaces) {
			int x = e.getAsJsonObject().getAsJsonPrimitive(X).getAsInt();
			int y = e.getAsJsonObject().getAsJsonPrimitive(Y).getAsInt();
			DieValue value = DieValue.fromInt(e.getAsJsonObject().getAsJsonPrimitive(VALUE).getAsInt());
			spaces[x][y] = new ValueSpace(value);
		}
		try {
			return new WindowPattern(spaces, difficulty, name);
		} catch (WindowPatternDimensionException e) {
			e.printStackTrace();
		} catch (UnboundDifficultyValueException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Serialize a window pattern.
	 *
	 * @return a JsonElement.
	 */
	@Override
	public JsonElement serialize(WindowPattern window, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject retValue = new JsonObject();
		JsonArray valueSpaces = new JsonArray();
		JsonArray colorSpaces = new JsonArray();
		Space[][] spaces = window.getAllSpaces();
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[i].length; j++) {
				if (spaces[i][j] instanceof ColorSpace) {
					JsonObject colorSpace = new JsonObject();
					colorSpace.addProperty(X, i);
					colorSpace.addProperty(Y, j);
					colorSpace.addProperty(COLOR, spaces[i][j].getColorRestriction().toInt());
					colorSpaces.add(colorSpace);
				}
			}
		}
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[i].length; j++) {
				if (spaces[i][j] instanceof ValueSpace) {
					JsonObject valueSpace = new JsonObject();
					valueSpace.addProperty(X, i);
					valueSpace.addProperty(Y, j);
					valueSpace.addProperty(VALUE, spaces[i][j].getValueRestriction().toInt());
					valueSpaces.add(valueSpace);
				}
			}
		}
		JsonObject windowPattern = new JsonObject();
		windowPattern.add(COLOR_SPACES, colorSpaces);
		windowPattern.add(VALUE_SPACES, valueSpaces);
		windowPattern.addProperty(DIFFICULTY, window.getDifficulty());
		windowPattern.addProperty(NAME, window.getName());
		retValue.add(WINDOW, windowPattern);
		return retValue;
	}
}