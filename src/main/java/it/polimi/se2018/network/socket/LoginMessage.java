package it.polimi.se2018.network.socket;

import com.google.gson.Gson;

/**
 * @author davide yi xian hu
 */
public class LoginMessage {

	/**
	 * User identifier.
	 */
	private String uid;

	/**
	 * Constructor.
	 * @param uid the user identifier.
	 */
	public LoginMessage(String uid) {
		this.uid = uid;
	}

	/**
	 * Constructor.
	 * @param text a String of text. It can be a String or a JSON String that represent the uid.
	 */
	public LoginMessage(String text, boolean isJson) {
		if(isJson) {
			uid = new Gson().fromJson(text, this.getClass()).getUid();
		}else{
			this.uid = text;
		}
	}

	/**
	 * Getter of the user identifier
	 * @return the user identifier.
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Return this object as a Json String.
	 */
	public String toJsonString() {
		return new Gson().toJson(this);
	}
}
