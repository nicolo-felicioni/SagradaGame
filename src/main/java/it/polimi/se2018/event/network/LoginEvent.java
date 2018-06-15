package it.polimi.se2018.event.network;

public class LoginEvent {

    /**
     * The userename (player identifier).
     */
    private String username;

    /**
     * Constructor.
     * @param username the username.
     */
    public LoginEvent (String username) {
        this.username = username;
    }

    /**
     * Username (player identifier) getter.
     * @return the username (player identifier).
     */
    public String getUsername() {
        return username;
    }
}
