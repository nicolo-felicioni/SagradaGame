package it.polimi.se2018.network;

import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;

/**
 * @author davide yi xian hu
 */
public interface SessionInterface {
	
	void updateDiceBag(DiceBag diceBag) throws SessionException;

	void login(String uid) throws SessionException;
	void logout(String uid) throws SessionException;
	String getUID();
}
