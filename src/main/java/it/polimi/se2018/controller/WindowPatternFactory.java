package it.polimi.se2018.controller;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.polimi.se2018.json.WindowPatternAdapter;
import it.polimi.se2018.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author davide yi xian hu
 */
public class WindowPatternFactory {

	private List<WindowPattern> windows;
	private static final String STANDARD_WINDOW_PATH = "src/main/resources/std_window_pattern.json";

	public WindowPatternFactory() {
		windows = new ArrayList<>();
		try {
			this.loadWindowPattern(STANDARD_WINDOW_PATH);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public WindowPattern getWindowPattern() {
		return windows.get((int) Math.random() * windows.size());
	}

	public WindowPattern[] getWindowPattern(int n) {
		WindowPattern [] patterns = new WindowPattern[n];
		for(int i = 0 ; i < n; i++) {
			patterns[i] = this.getWindowPattern();
		}
		return patterns;
	}

	private void loadWindowPattern(String path) throws FileNotFoundException{
		JsonReader reader = new JsonReader(new FileReader(path));
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WindowPattern.class, new WindowPatternAdapter());
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		Type listType = new TypeToken<ArrayList<WindowPattern>>(){}.getType();
		List<WindowPattern> list = gson.fromJson(reader, listType);
		this.windows.addAll(list);
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toJson(WindowPattern window) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WindowPattern.class, new WindowPatternAdapter());
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(window);
	}

	public WindowPattern fromJson(String s){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WindowPattern.class, new WindowPatternAdapter());
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.fromJson(s, WindowPattern.class);
	}

}
