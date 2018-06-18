package it.polimi.se2018.network.socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.json.PlayerStateAdapter;
import it.polimi.se2018.json.ViewUpdaterAdapter;
import it.polimi.se2018.json.WindowPatternAdapter;
import it.polimi.se2018.model.PlayerState;
import it.polimi.se2018.model.WindowPattern;
import it.polimi.se2018.network.server.Server;
import it.polimi.se2018.network.server.SessionInterface;
import it.polimi.se2018.observable.GameEventObservableImpl;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * @author davide yi xian hu
 */
public class SocketServerSession extends GameEventObservableImpl implements SessionInterface {

	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;
	private NetworkListener listener;

	/**
	 * Unique identifier of the client.
	 */
	private String uid;

	/**
	 * Constructor.
	 * @param socket the socket connection with the client.
	 */
	public SocketServerSession(Socket socket) {
		this.socket = socket;
		try {
			this.outStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.outStream.flush();
			this.inStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
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
			this.uid = new LoginMessage(login, true).getUid();
			Server.getInstance().login(uid, this);
		} catch (IOException | LoginException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUID() {
		return uid;
	}

	/**
	 * Handle a view update from the network.
	 *
	 * @param updater the view updater.
	 * @throws RemoteException  if RMI errors occur during the connection.
	 * @throws NetworkException if any connection error occurs during the connection.
	 */
	@Override
	public synchronized void handle(ViewUpdaterInterface updater) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ViewUpdaterInterface.class, new ViewUpdaterAdapter());
		gsonBuilder.registerTypeAdapter(WindowPattern.class, new WindowPatternAdapter());
		gsonBuilder.registerTypeAdapter(PlayerState.class, new PlayerStateAdapter());
		Gson gson = gsonBuilder.create();
		try {
			this.outStream.writeUTF(gson.toJson(updater, ViewUpdaterInterface.class));
			this.outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
