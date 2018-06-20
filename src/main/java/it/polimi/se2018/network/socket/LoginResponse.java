package it.polimi.se2018.network.socket;

import com.google.gson.Gson;

public class LoginResponse {

    /**
     * If login has been successful.
     */
    private boolean loginResult;

    /**
     * The login response message.
     */
    private String message;

    /**
     * Login successful message.
     */
    public static final String LOGIN_SUCCESS_MESSAGE = "Login successful";

    /**
     * Login failed message.
     */
    public static final String LOGIN_FAIL_MESSAGE = "Login failed";


    /**
     * Constructor.
     * @param loginResult if login has been successful.
     * @param message the login response message.
     */
    public LoginResponse(boolean loginResult, String message) {
        this.loginResult = loginResult;
        this.message = message;
    }

    /**
     * Json constructor.
     * @param json the json text of the login response.
     */
    public LoginResponse(String json) {
        loginResult = new Gson().fromJson(json, this.getClass()).isLoginResult();
        message = new Gson().fromJson(json, this.getClass()).getMessage();
    }

    /**
     * Login result getter.
     * @return true if login has been successful.
     */
    public boolean isLoginResult() {
        return loginResult;
    }

    /**
     * Login result message getter.
     * @return the login response message.
     */
    public String getMessage() {
        return message;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
