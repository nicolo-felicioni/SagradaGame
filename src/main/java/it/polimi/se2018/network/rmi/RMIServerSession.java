package it.polimi.se2018.network.rmi;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.observable.game.GameEventObservableImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Davide Yi Xian Hu
 */
public class RMIServerSession extends GameEventObservableImpl implements SessionInterface, RMIServerSessionInterface {

	/**
	 * RMI Client
	 */
	private transient List<RMIClientInterface> clientInterfaces;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * Default constructor.
	 * @param uid the user identifier.
	 */
	public RMIServerSession(String uid) {
		this.clientInterfaces = new ArrayList<>();
		this.uid = uid;
	}

	/**
	 * Disconnect a client.
	 */
	@Override
	public synchronized void disconnect(RMIClientInterface client) {
		this.clientInterfaces.remove(client);
	}

	/**
	 * {@inheritDoc}
	 * Add a client observer.
	 */
	public synchronized void addClientObserver(RMIClientInterface observer) {
		this.clientInterfaces.add(observer);
	}

	/**
	 * {@inheritDoc}
	 * Notify a view updater to the client;
	 */
	public synchronized void notify(ViewUpdaterInterface updater) {
		this.clientInterfaces.forEach(observer -> {
			try {
				observer.handle(updater);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Remove a network view updater observer.
	 *
	 * @param observer the network view updater observer.
	 */
	public synchronized void removeClientObserver(RMIClientInterface observer) {
		this.clientInterfaces.add(observer);
	}

	/**
	 * {@inheritDoc}
	 * Forward the view updater to the client.
	 */
	public void handle(ViewUpdaterInterface updater) {
		this.notify(updater);
	}

	@Override
	public String getUID() {
		return this.uid;
	}


	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void handle(MoveDieMatchColorRoundTrackGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	@Override
	public void handle(MoveDieRespectAllRestrictionsGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {
		this.notifyObservers(event);
	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {
		this.notifyObservers(event);
	}

}
