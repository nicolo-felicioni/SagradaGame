package it.polimi.se2018.network.socket;

import it.polimi.se2018.event.*;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.observer.*;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class SocketServerSession implements SessionInterface {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private NetworkListener listener;


	public SocketServerSession(Socket socket) {
		this.socket = socket;
		try {
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}

	/**
	 * Initialize the connection.
	 * It has 2 steps. It wait for a login message, then it log the client to the Server.
	 */

	public void init() {
		try {
			String login = this.inStream.readUTF();
			Server.getInstance().login(new LoginMessage(login, true).getUid(), this);
		} catch (IOException | LoginException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUID() {
		return null;
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater) {
		String message = updater.toString();
	}

	/**
	 * Add a GameEventObserver.
	 *
	 * @param observer the GameEventObserver.
	 */
	@Override
	public void addGameObserver(GameEventObserver observer) {

	}

	/**
	 * Remove a GameEventObserver.
	 *
	 * @param observer the GameEventObserver.
	 */
	@Override
	public void removeGameObserver(GameEventObserver observer) {

	}

	/**
	 * Add a ChooseDraftDieValueObserver.
	 *
	 * @param observer the ChooseDraftDieValueObserver.
	 */
	@Override
	public void addObserver(ChooseDraftDieValueObserver observer) {

	}

	/**
	 * Remove a ChooseDraftDieValueObserver.
	 *
	 * @param observer the ChooseDraftDieValueObserver.
	 */
	@Override
	public void removeObserver(ChooseDraftDieValueObserver observer) {

	}

	/**
	 * Notify the ChooseDraftDieValueObservers an ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void notifyObservers(ChooseDraftDieValueGameEvent event) {

	}

	/**
	 * Add a DecreaseDieValueObserver.
	 *
	 * @param observer the DecreaseDieValueObserver.
	 */
	@Override
	public void addObserver(DecreaseDieValueObserver observer) {

	}

	/**
	 * Remove a DecreaseDieValueObserver.
	 *
	 * @param observer the DecreaseDieValueObserver.
	 */
	@Override
	public void removeObserver(DecreaseDieValueObserver observer) {

	}

	/**
	 * Notify the DecreaseDieValueObservers an DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void notifyObservers(DecreaseDieValueGameEvent event) {

	}

	/**
	 * Add a DraftAndPlaceAgainObserver.
	 *
	 * @param observer the DraftAndPlaceAgainObserver.
	 */
	@Override
	public void addObserver(DraftAndPlaceAgainObserver observer) {

	}

	/**
	 * Remove a DraftAndPlaceAgainObserver.
	 *
	 * @param observer the DraftAndPlaceAgainObserver.
	 */
	@Override
	public void removeObserver(DraftAndPlaceAgainObserver observer) {

	}

	/**
	 * Notify the DraftAndPlaceAgainObservers an DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceAgainGameEvent event) {

	}

	/**
	 * Add a DraftAndPlaceNoAdjacentObserver.
	 *
	 * @param observer the DraftAndPlaceNoAdjacentObserver.
	 */
	@Override
	public void addObserver(DraftAndPlaceNoAdjacentObserver observer) {

	}

	/**
	 * Remove a DraftAndPlaceNoAdjacentObserver.
	 *
	 * @param observer the DraftAndPlaceNoAdjacentObserver.
	 */
	@Override
	public void removeObserver(DraftAndPlaceNoAdjacentObserver observer) {

	}

	/**
	 * Notify the DraftAndPlaceNoAdjacentObservers an DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceNoAdjacentGameEvent event) {

	}

	/**
	 * Add a DraftAndPlaceObserver.
	 *
	 * @param observer the DraftAndPlaceObserver.
	 */
	@Override
	public void addObserver(DraftAndPlaceObserver observer) {

	}

	/**
	 * Remove a DraftAndPlaceObserver.
	 *
	 * @param observer the DraftAndPlaceObserver.
	 */
	@Override
	public void removeObserver(DraftAndPlaceObserver observer) {

	}

	/**
	 * Notify the DraftAndPlaceObservers an DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void notifyObservers(DraftAndPlaceGameEvent event) {

	}

	/**
	 * Add a EndTurnObserver.
	 *
	 * @param observer the EndTurnObserver.
	 */
	@Override
	public void addObserver(EndTurnObserver observer) {

	}

	/**
	 * Remove a EndTurnObserver.
	 *
	 * @param observer the EndTurnObserver.
	 */
	@Override
	public void removeObserver(EndTurnObserver observer) {

	}

	/**
	 * Notify the EndTurnObservers an EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void notifyObservers(EndTurnGameEvent event) {

	}

	/**
	 * Add a FlipDraftDieObserver.
	 *
	 * @param observer the FlipDraftDieObserver.
	 */
	@Override
	public void addObserver(FlipDraftDieObserver observer) {

	}

	/**
	 * Remove a FlipDraftDieObserver.
	 *
	 * @param observer the FlipDraftDieObserver.
	 */
	@Override
	public void removeObserver(FlipDraftDieObserver observer) {

	}

	/**
	 * Notify the FlipDraftDieObservers an FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void notifyObservers(FlipDraftDieGameEvent event) {

	}

	/**
	 * Add a IncreaseDieValueObserver.
	 *
	 * @param observer the IncreaseDieValueObserver.
	 */
	@Override
	public void addObserver(IncreaseDieValueObserver observer) {

	}

	/**
	 * Remove a IncreaseDieValueObserver.
	 *
	 * @param observer the IncreaseDieValueObserver.
	 */
	@Override
	public void removeObserver(IncreaseDieValueObserver observer) {

	}

	/**
	 * Notify the IncreaseDieValueObservers an IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void notifyObservers(IncreaseDieValueGameEvent event) {

	}

	/**
	 * Add a MoveDieIgnoreColorRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreColorRestrictionObserver.
	 */
	@Override
	public void addObserver(MoveDieIgnoreColorRestrictionObserver observer) {

	}

	/**
	 * Remove a MoveDieIgnoreColorRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreColorRestrictionObserver.
	 */
	@Override
	public void removeObserver(MoveDieIgnoreColorRestrictionObserver observer) {

	}

	/**
	 * Notify the MoveDieIgnoreColorRestrictionObservers an MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieIgnoreColorRestrictionGameEvent event) {

	}

	/**
	 * Add a MoveDieIgnoreValueRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreValueRestrictionObserver.
	 */
	@Override
	public void addObserver(MoveDieIgnoreValueRestrictionObserver observer) {

	}

	/**
	 * Remove a MoveDieIgnoreValueRestrictionObserver.
	 *
	 * @param observer the MoveDieIgnoreValueRestrictionObserver.
	 */
	@Override
	public void removeObserver(MoveDieIgnoreValueRestrictionObserver observer) {

	}

	/**
	 * Notify the MoveDieIgnoreValueRestrictionObservers an MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieIgnoreValueRestrictionGameEvent event) {

	}

	/**
	 * Add a MoveDieMatchColorRoundTrackObserver.
	 *
	 * @param observer the MoveDieMatchColorRoundTrackObserver.
	 */
	@Override
	public void addObserver(MoveDieMatchColorRoundTrackObserver observer) {

	}

	/**
	 * Remove a MoveDieMatchColorRoundTrackObserver.
	 *
	 * @param observer the MoveDieMatchColorRoundTrackObserver.
	 */
	@Override
	public void removeObserver(MoveDieMatchColorRoundTrackObserver observer) {

	}

	/**
	 * Notify the MoveDieMatchColorRoundTrackObservers an MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void notifyObservers(MoveDieMatchColorRoundTrackGameEvent event) {

	}

	/**
	 * Add a MoveDieRespectAllRestrictionObserver.
	 *
	 * @param observer the MoveDieRespectAllRestrictionObserver.
	 */
	@Override
	public void addObserver(MoveDieRespectAllRestrictionsObserver observer) {

	}

	/**
	 * Remove a MoveDieRespectAllRestrictionObserver.
	 *
	 * @param observer the MoveDieRespectAllRestrictionObserver.
	 */
	@Override
	public void removeObserver(MoveDieRespectAllRestrictionsObserver observer) {

	}

	/**
	 * Notify the MoveDieRespectAllRestrictionObservers an MoveDieRespectAllRestrictionEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionEvent.
	 */
	@Override
	public void notifyObservers(MoveDieRespectAllRestrictionsGameEvent event) {

	}

	/**
	 * Add a RerollAllDraftDiceObserver.
	 *
	 * @param observer the RerollAllDraftDiceObserver.
	 */
	@Override
	public void addObserver(RerollAllDraftDiceObserver observer) {

	}

	/**
	 * Remove a RerollAllDraftDiceObserver.
	 *
	 * @param observer the RerollAllDraftDiceObserver.
	 */
	@Override
	public void removeObserver(RerollAllDraftDiceObserver observer) {

	}

	/**
	 * Notify the RerollAllDraftDiceObservers an RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void notifyObservers(RerollAllDraftDiceGameEvent event) {

	}

	/**
	 * Add a RerollDraftDieObserver.
	 *
	 * @param observer the RerollDraftDieObserver.
	 */
	@Override
	public void addObserver(RerollDraftDieObserver observer) {

	}

	/**
	 * Remove a RerollDraftDieObserver.
	 *
	 * @param observer the RerollDraftDieObserver.
	 */
	@Override
	public void removeObserver(RerollDraftDieObserver observer) {

	}

	/**
	 * Notify the RerollDraftDieObservers an RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void notifyObservers(RerollDraftDieGameEvent event) {

	}

	/**
	 * Add a StartGameObserver.
	 *
	 * @param observer the StartGameObserver.
	 */
	@Override
	public void addObserver(StartGameObserver observer) {

	}

	/**
	 * Remove a StartGameObserver.
	 *
	 * @param observer the StartGameObserver.
	 */
	@Override
	public void removeObserver(StartGameObserver observer) {

	}

	/**
	 * Notify the StartGameObserver an StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void notifyObservers(StartGameEvent event) {

	}

	/**
	 * Add a SwapDraftDieWithDiceBagDieObserver.
	 *
	 * @param observer the SwapDraftDieWithDiceBagDieObserver.
	 */
	@Override
	public void addObserver(SwapDraftDieWithDiceBagDieObserver observer) {

	}

	/**
	 * Remove a SwapDraftDieWithDiceBagDieObserver.
	 *
	 * @param observer the SwapDraftDieWithDiceBagDieObserver.
	 */
	@Override
	public void removeObserver(SwapDraftDieWithDiceBagDieObserver observer) {

	}

	/**
	 * Notify the SwapDraftDieWithDiceBagDieObservers an SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void notifyObservers(SwapDraftDieWithDiceBagDieGameEvent event) {

	}

	/**
	 * Add a SwapDrafDieWithRoundTrackDieObserver.
	 *
	 * @param observer the SwapDrafDieWithRoundTrackDieObserver.
	 */
	@Override
	public void addObserver(SwapDraftDieWithRoundTrackDieObserver observer) {

	}

	/**
	 * Remove a SwapDrafDieWithRoundTrackDieObserver.
	 *
	 * @param observer the SwapDrafDieWithRoundTrackDieObserver.
	 */
	@Override
	public void removeObserver(SwapDraftDieWithRoundTrackDieObserver observer) {

	}

	/**
	 * Notify the SwapDrafDieWithRoundTrackDieObservers an SwapDrafDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDrafDieWithRoundTrackDieEvent.
	 */
	@Override
	public void notifyObservers(SwapDraftDieWithRoundTrackDieGameEvent event) {

	}

	/**
	 * Add a UseToolCardObserver.
	 *
	 * @param observer the UseToolCardObserver.
	 */
	@Override
	public void addObserver(UseToolCardObserver observer) {

	}

	/**
	 * Remove a UseToolCardObserver.
	 *
	 * @param observer the UseToolCardObserver.
	 */
	@Override
	public void removeObserver(UseToolCardObserver observer) {

	}

	/**
	 * Notify the UseToolCardObservers an UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void notifyObservers(UseToolCardGameEvent event) {

	}

	/**
	 * Add a WindowPatternChosenObserver.
	 *
	 * @param observer the WindowPatternChosenObserver.
	 */
	@Override
	public void addObserver(WindowPatternChosenObserver observer) {

	}

	/**
	 * Remove a WindowPatternChosenObserver.
	 *
	 * @param observer the WindowPatternChosenObserver.
	 */
	@Override
	public void removeObserver(WindowPatternChosenObserver observer) {

	}

	/**
	 * Notify the WindowPatternChosenObservers an WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void notifyObservers(WindowPatternChosenGameEvent event) {

	}

	private class NetworkListener implements Runnable {
		private boolean run = true;

		@Override
		public void run() {
			while(this.run) {
				try {
					String request = inStream.readUTF();
				} catch (IOException e) {
				}
			}
		}

	}
}
