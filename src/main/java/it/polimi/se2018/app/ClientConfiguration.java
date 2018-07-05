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
    private static String client;

    /**
     * Server configuration file path.
     */
    private static final String CONF_PATH = "src/main/resources/client_conf.json";

    /**
     * Constructor.
     */
    private ClientConfiguration() {
        getClientConfiguration();
    }

    /**
     * Load client configuration from file.
     */
    public static void getClientConfiguration() {
        try (JsonReader reader = new JsonReader(new FileReader(CONF_PATH))) {
            Gson gson = new GsonBuilder().create();
            Configuration configuration = gson.fromJson(reader, Configuration.class);
            client = configuration.client;
        } catch (IOException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Return the client app type.
     * @return the client app type.
     */
    public static String getClient() {
        return client;
    }

    private static class Configuration {

        /**
         * Client app type.
         */
        private String client;

    }
}