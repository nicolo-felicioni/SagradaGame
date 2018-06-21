package it.polimi.se2018.network.socket;

import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.game.*;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.json.Json;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.view.View;

import java.io.*;
import java.net.Socket;

/**
 * @author davide yi xian hu
 */
public class SocketClient implements ClientInterface {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private NetworkListener listener;

	/**
	 * The user interface.
	 */
	private View view;

	/**
	 * The user identifier.
	 */
	private String uid;

	/**
	 * Constructor.
	 */
	public SocketClient(View view) {
		this.view = view;
	}

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
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.outStream.flush();
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Login a client to the server.
	 *
	 * @param uid the unique identifier of the client.
	 */
	@Override
	public void login(String uid) throws LoginException {
		this.send(new LoginMessage(uid).toJsonString());
		LoginResponse response = null;
		try {
			String text = inStream.readUTF();
			response = new LoginResponse(text);
			if(!response.isLoginResult()){
				throw new LoginException(response.getMessage());
			}
			this.listener = new NetworkListener();
			new Thread(listener).start();
			this.uid = uid;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter of the unique identifier.
	 *
	 * @return the unique identifier.
	 */
	@Override
	public String getUid() {
		return uid;
	}

	/**
	 * Send a message to the server.
	 *
	 * @param text the message to send to the server session.
	 */
	private void send(String text){
		try {
			this.outStream.writeUTF(text);
			this.outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 */
	@Override
	public void handle(ViewUpdaterInterface updater) {
		this.view.handle(updater);
	}

	/**
	 * Handle a ChooseDraftDieValueEvent.
	 *
	 * @param event the ChooseDraftDieValueEvent.
	 */
	@Override
	public void handle(ChooseDraftDieValueGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a DecreaseDieValueEvent.
	 *
	 * @param event the DecreaseDieValueEvent.
	 */
	@Override
	public void handle(DecreaseDieValueGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a DraftAndPlaceAgainEvent.
	 *
	 * @param event the DraftAndPlaceAgainEvent.
	 */
	@Override
	public void handle(DraftAndPlaceAgainGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a DraftAndPlaceNoAdjacentEvent.
	 *
	 * @param event the DraftAndPlaceNoAdjacentEvent.
	 */
	@Override
	public void handle(DraftAndPlaceNoAdjacentGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a DraftAndPlaceEvent.
	 *
	 * @param event the DraftAndPlaceEvent.
	 */
	@Override
	public void handle(DraftAndPlaceGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a EndTurnEvent.
	 *
	 * @param event the EndTurnEvent.
	 */
	@Override
	public void handle(EndTurnGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a FlipDraftDieEvent.
	 *
	 * @param event the FlipDraftDieEvent.
	 */
	@Override
	public void handle(FlipDraftDieGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a IncreaseDieValueEvent.
	 *
	 * @param event the IncreaseDieValueEvent.
	 */
	@Override
	public void handle(IncreaseDieValueGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a MoveDieIgnoreColorRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreColorRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreColorRestrictionGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a MoveDieIgnoreValueRestrictionEvent.
	 *
	 * @param event the MoveDieIgnoreValueRestrictionEvent.
	 */
	@Override
	public void handle(MoveDieIgnoreValueRestrictionGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
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
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a RerollAllDraftDiceEvent.
	 *
	 * @param event the RerollAllDraftDiceEvent.
	 */
	@Override
	public void handle(RerollAllDraftDiceGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a RerollDraftDieEvent.
	 *
	 * @param event the RerollDraftDieEvent.
	 */
	@Override
	public void handle(RerollDraftDieGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a StartGameEvent.
	 *
	 * @param event the StartGameEvent.
	 */
	@Override
	public void handle(StartGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a SwapDraftDieWithDiceBagDieEvent.
	 *
	 * @param event the SwapDraftDieWithDiceBagDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithDiceBagDieGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a SwapDraftDieWithRoundTrackDieEvent.
	 *
	 * @param event the SwapDraftDieWithRoundTrackDieEvent.
	 */
	@Override
	public void handle(SwapDraftDieWithRoundTrackDieGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a UseToolCardEvent.
	 *
	 * @param event the UseToolCardEvent.
	 */
	@Override
	public void handle(UseToolCardGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}

	/**
	 * Handle a WindowPatternChosenEvent.
	 *
	 * @param event the WindowPatternChosenEvent.
	 */
	@Override
	public void handle(WindowPatternChosenGameEvent event) {
		this.send(Json.getGson().toJson(event, GameEvent.class));
	}


	private class NetworkListener implements Runnable {
		private boolean run = true;

		@Override
		public void run() {
			while(this.run) {
				try {
					handle(Json.getGson().fromJson(inStream.readUTF(), ViewUpdaterInterface.class));
				} catch (IOException e) {
				}
			}
		}
	}
}
