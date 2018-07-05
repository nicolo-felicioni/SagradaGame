package it.polimi.se2018.controller.factory;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.json.Json;
import it.polimi.se2018.json.WindowPatternAdapter;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.network.ServerConfiguration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * @author Davide Yi Xian Hu
 */
public class WindowPatternFactory {

	private List<WindowPattern> windows;
	private static final String STANDARD_WINDOW_PATH = "src/main/resources/std_window_pattern.json";
	public WindowPatternFactory() {
		windows = new ArrayList<>();
		try {
			this.loadWindowPattern(STANDARD_WINDOW_PATH);
			if(ServerConfiguration.CUSTOM_PATTERN) {
				this.loadWindowPattern(ServerConfiguration.CUSTOM_PATTERN_PATH);
			}
		}catch (FileNotFoundException e) {
			MyLog.getMyLog().log(Level.WARNING, e.getMessage());
		}
	}

	public WindowPattern getWindowPattern() {
		return windows.remove(new Random().nextInt(windows.size()));
	}

	public WindowPattern[] getWindowPattern(int n) {
		WindowPattern [] patterns = new WindowPattern[n];
		for(int i = 0 ; i < n; i++) {
			patterns[i] = this.getWindowPattern();
		}
		return patterns;
	}

	private void loadWindowPattern(String path) throws FileNotFoundException{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WindowPattern.class, new WindowPatternAdapter());
		JsonReader reader = new JsonReader(new FileReader(path));
		Type listType = new TypeToken<ArrayList<WindowPattern>>(){}.getType();
		List<WindowPattern> list = gsonBuilder.create().fromJson(reader, listType);
		this.windows.addAll(list);
		try {
			reader.close();
		} catch (IOException e) {
			MyLog.getMyLog().log(Level.WARNING, e.getMessage());
		}
	}

	public String toJson(WindowPattern window) {
		return Json.getGson().toJson(window);
	}

	public WindowPattern fromJson(String s){
		return Json.getGson().fromJson(s, WindowPattern.class);
	}

}
