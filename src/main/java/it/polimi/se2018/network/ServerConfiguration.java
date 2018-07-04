package it.polimi.se2018.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import it.polimi.se2018.event.game.GameEvent;

import java.io.FileReader;
import java.io.IOException;

public class ServerConfiguration {

    /**
     * RMI server port number.
     */
    public static int RMI_SERVER_PORT;

    /**
     * Socket server port number.
     */
    public static int SOCKET_SERVER_PORT;

    /**
     * Game room timer.
     * Amount of time a game root waits before starting the game.
     */
    public static int GAME_ROOM_TIMER;

    /**
     * Turn timer.
     * Amount of time to wait before disconnecting a client.
     */
    public static int TURN_TIMER;

    /**
     * Server configuration file path.
     */
    private static final String CONF_PATH = "src/main/resources/server_conf.json";

    /**
     * Load server configuration from file.
     */
    public static void getServerConfiguration() {
        try(JsonReader reader = new JsonReader(new FileReader(CONF_PATH))) {
            Gson gson = new GsonBuilder().create();
            Configuration configuration = gson.fromJson(reader, Configuration.class);
            RMI_SERVER_PORT = configuration.rmiPort;
            SOCKET_SERVER_PORT = configuration.socketPort;
            GAME_ROOM_TIMER = configuration.gameRoomTimer;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Configuration {

        /**
         * RMI server port number.
         */
        private int rmiPort;

        /**
         * Socket server port number.
         */
        private int socketPort;

        /**
         * Game room timer.
         */
        private int gameRoomTimer;

        /**
         * Turn timer.
         */
        private int turnTimer;

    }
}
