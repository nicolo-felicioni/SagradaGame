package it.polimi.se2018.json;

import com.google.gson.*;
import it.polimi.se2018.exceptions.UnboundDifficultyValueException;
import it.polimi.se2018.exceptions.WindowPatternDimensionException;
import it.polimi.se2018.model.*;

import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Davide Yi Xian Hu
 */
class WindowPatternAdapter implements JsonSerializer<WindowPattern>, JsonDeserializer<WindowPattern> {

	private static final String X = "x";
	private static final String Y = "y";
	private static final String COLOR = "color";
	private static final String COLOR_SPACES = "colorSpaces";
	private static final String VALUE = "value";
	private static final String VALUE_SPACES = "valueSpaces";
	private static final String DIFFICULTY = "difficulty";
	private static final String NAME = "name";
	private static final String WINDOW = "window";

	/**
	 * {@inheritDoc}
	 * Deserialize a json element.
	 *
	 * @return a window pattern.
	 */
	@Override
	public WindowPattern deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
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
		} catch (UnboundDifficultyValueException | WindowPatternDimensionException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, e.getMessage(), e);
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