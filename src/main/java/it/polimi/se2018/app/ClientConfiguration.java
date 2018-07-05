package it.polimi.se2018.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import it.polimi.se2018.controller.utils.MyLog;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

public class ClientConfiguration {

    /**
     * The client app type.
     */
    public static String CLIENT;

    /**
     * Server configuration file path.
     */
    private static final String CONF_PATH = "src/main/resources/client_conf.json";

    /**
     * Load client configuration from file.
     */
    public static void getClientConfiguration() {
        try (JsonReader reader = new JsonReader(new FileReader(CONF_PATH))) {
            Gson gson = new GsonBuilder().create();
            Configuration configuration = gson.fromJson(reader, Configuration.class);
            CLIENT = configuration.client;
        } catch (IOException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        }
    }

    private static class Configuration {

        /**
         * Client app type.
         */
        private String client;

    }
}