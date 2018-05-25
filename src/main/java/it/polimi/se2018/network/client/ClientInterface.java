package it.polimi.se2018.network.client;

import it.polimi.se2018.exceptions.SessionException;
import it.polimi.se2018.model.*;

/**
 * @author davide yi xian hu
 */
public interface ClientInterface {

	/**
	 * Update the dice bag.
	 * @param diceBag the updated dice bag.
	 */
	void updateDiceBag(DiceBag diceBag);

}
