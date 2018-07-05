package it.polimi.se2018.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import it.polimi.se2018.controller.utils.MyLog;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

public class ServerConfiguration {

    /**
     * RMI server port number.
     */
    private static int rmiServerPort;

    /**
     * Socket server port number.
     */
    private static int socketServerPort;

    /**
     * Game room timer.
     * Amount of time a game root waits before starting the game.
     */
    private static int gameRoomTimer;

    /**
     * Turn timer.
     * Amount of time to wait before disconnecting a client.
     */
    private static int turnTimer;

    /**
     * True if the server have to load the custom pattern in the games.
     */
    private static boolean customPattern;

    /**
     * The custom pattern path location.
     */
    private static String customPatternPath;

    /**
     * Server configuration file path.
     */
    private static final String CONF_PATH = "src/main/resources/server_conf.json";

    /**
     * Constructor.
     */
    private ServerConfiguration() {
        getServerConfiguration();
    }

    /**
     * Load server configuration from file.
     */
    public static void getServerConfiguration() {
        try(JsonReader reader = new JsonReader(new FileReader(CONF_PATH))) {
            Gson gson = new GsonBuilder().create();
            Configuration configuration = gson.fromJson(reader, Configuration.class);
            rmiServerPort = configuration.rmiPort;
            socketServerPort = configuration.socketPort;
            gameRoomTimer = configuration.gameRoomTimer;
            turnTimer = configuration.turnTimer;
            customPattern = configuration.customPattern;
            customPatternPath = configuration.customPatternPath;
        } catch (IOException e) {
            MyLog.getMyLog().log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Return the rmi server port.
     * @return the rmi server port.
     */
    public static int getRmiServerPort() {
        return rmiServerPort;
    }

    /**
     * Return the socket server port.
     * @return the socket server port.
     */
    public static int getSocketServerPort() {
        return socketServerPort;
    }

    /**
     * Return the game room timer.
     * @return the game room timer.
     */
    public static int getGameRoomTimer() {
        return gameRoomTimer;
    }

    /**
     * Return the turn timer.
     * @return the turn timer.
     */
    public static int getTurnTimer() {
        return turnTimer;
    }

    /**
     * Return true if the server have to load the custom pattern in the games.
     * @return true if the server have to load the custom pattern in the games.
     */
    public static boolean isCustomPattern() {
        return customPattern;
    }

    /**
     * Return the custom pattern path location.
     * @return the custom pattern path location.
     */
    public static String getCustomPatternPath() {
        return customPatternPath;
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

        /**
         * True if the server have to load the custom pattern in the games.
         */
        private boolean customPattern;

        /**
         * The custom pattern path location.
         */
        private String customPatternPath;

    }
}
