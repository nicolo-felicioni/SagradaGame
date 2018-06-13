package it.polimi.se2018.network.socket;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.*;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.network.client.AbstractClient;

import java.io.*;
import java.net.Socket;

/**
 * @author davide yi xian hu
 */
public class SocketClient extends AbstractClient {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private NetworkListener listener;

	/**
	 * Connect to the socket server.
	 *
	 * @param address the ip address of the socket server.
	 * @param port the port number of the socket server.
	 *
	 */
	public void connect(String address, int port) {
		try {
			this.socket = new Socket(address, port);
			this.login(getUid());
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.listener = new NetworkListener();
			new Thread(listener).start();
		} catch (IOException|LoginException e) {
		}
	}

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	@Override
	public void login(String uid) throws LoginException {
		try {
			this.send(new LoginMessage(this.getUid()).toJsonString());
		}catch (IOException e){
			new LoginException("Login failed");
		}
	}

	/**
	 * Getter of the unique identifier.
	 *
	 * @return the unique identifier.
	 */
	@Override
	public String getUid() {
		return null;
	}

	/**
	 * Send a message to the server.
	 *
	 * @param text the message to send to the server session.
	 */
	private void send(String text) throws IOException{
		this.outStream.writeUTF(text);
		this.outStream.flush();
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater) {

	}

	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {

	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {

	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {

	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {

	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {

	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {

	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {

	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {

	}

	/**
	 * Handle a MoveDieMatchColorRoundTrackEvent.
	 *
	 * @param event the MoveDieMatchColorRoundTrackEvent.
	 */
	@Override
	public void handle(MoveDieMatchColorRoundTrackGameEvent event) {

	}

	/**
	 * Handle a MoveDieRespectAllRestrictionsEvent.
	 *
	 * @param event the MoveDieRespectAllRestrictionsEvent.
	 */
	@Override
	public void handle(MoveDieRespectAllRestrictionsGameEvent event) {

	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {

	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {

	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {

	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {

	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {

	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {

	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {

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
