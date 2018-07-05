package it.polimi.se2018.view.cli;


import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.controller.utils.MyLog;
import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.exceptions.IllegalMoveTurnException;
import it.polimi.se2018.exceptions.LoginException;
import it.polimi.se2018.exceptions.NetworkException;
import it.polimi.se2018.exceptions.PlacementException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.rmi.RMIClient;
import it.polimi.se2018.network.socket.SocketClient;
import it.polimi.se2018.view.AbstractView;
import it.polimi.se2018.view.cli.options.Option;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * @author Nicolò Felicioni
 */

public class CommandLineInterface extends AbstractView {

    private static final String WINNER_MESSAGE = "You win!";
    private static final String LOSER_MESSAGE = "You lost.";

    public static final int END_GAME_CODE = -999;
    private final Object draftedDieLock = new Object();
    private ClientInterface client;
    private volatile boolean exit = false;
    private PlayerMenu menu;
    private List<Player> players;
    private Player player;
    private WindowPattern[] patterns;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private PrivateObjectiveCard privateObjectiveCard;
    private ToolCard[] toolCards;
    private PublicObjectiveCard[] publicObjectiveCards;
    private int numberOfPatternsReceived;

    private static final String WELCOME_MESSAGE = "WELCOME TO SAGRADA!";
    private static final String CONNECTION_REQUEST_MESSAGE = "What connection do you want? \n1. RMI\n2. Socket";
    private static final String PORT_REQUEST_MESSAGE = "Enter the number of the port: ";
    private static final String ADDRESS_REQUEST_MESSAGE = "Enter the IP address of the server: ";
    private static final String USERNAME_REQUEST_MESSAGE = "Enter your username: ";
    private static final String NOT_YOUR_TURN_MESSAGE = "It's not your turn.";
    private static final String CHOICE_ERROR_MESSAGE = "Not valid choice.";
    private static final int RMI_CHOICE = 1;
    private static final int SOCKET_CHOICE = 2;
    private static final String LOADING_MESSAGE = "Loading...";
    private static final DieColor ERROR_COLOR = DieColor.RED;




    public Object getDraftedDieLock() {
        return draftedDieLock;
    }


    public synchronized List<Player> getPlayers() {
        return players;
    }

    public synchronized Player getPlayer() {
        return player;
    }

    public synchronized WindowPattern[] getPatterns() {
        return patterns;
    }

    public synchronized RoundTrack getRoundTrack() {
        return roundTrack;
    }

    public synchronized DraftPool getDraftPool() {
        return draftPool;
    }

    public synchronized PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }

    public synchronized ToolCard[] getToolCards() {
        return toolCards;
    }

    public synchronized PublicObjectiveCard[] getPublicObjectiveCards() {
        return publicObjectiveCards;
    }


    public MyScanner getKeyboard() {
        return keyboard;
    }

    private MyScanner keyboard;


    public CommandLineInterface() {
        players = new ArrayList<>();
        keyboard = new MyScanner();
        patterns = new WindowPattern[Player.N_WINDOW_PATTERNS];
        toolCards = new ToolCard[Model.SET_OF_TOOL_CARDS_SIZE];
        roundTrack = new RoundTrack();
        publicObjectiveCards = new PublicObjectiveCard[Model.SET_OF_PUBLIC_OBJECTIVE_CARDS_SIZE];
        numberOfPatternsReceived = 0;
    }


    public void start() {
        Printer.printlnColor(WELCOME_MESSAGE, DieColor.getRandom());

        typeOfConnectionRequest();
        connection();
        login();
        Printer.println(LOADING_MESSAGE);//todo


        while (!areInitialOptionsInitialized()) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                MyLog.getMyLog().log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        startChooseWindow();
        Printer.println(LOADING_MESSAGE);//todo

        while (!areAllWindowInitialized()) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                MyLog.getMyLog().log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        startGame();

    }


    //-----------------------------UPDATER--------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updatePlayer(String playerId, int favorTokens, boolean connected) {
        Optional<Player> optionalPlayer = this.players.stream()
                .filter(p -> p.getId().equals(playerId)).findAny();



        if (!optionalPlayer.isPresent()) {
            Player tempPlayer = new Player(playerId);
            tempPlayer.setConnected(connected);
            tempPlayer.setTokens(favorTokens);
            this.players.add(tempPlayer);

            if(this.player.equalsPlayer(tempPlayer)){
                this.player.setConnected(connected);
                this.player.setTokens(favorTokens);
            }

           if(!connected){
                Printer.print("player '"+ playerId + "' disconnected.");
                if(playerId.equals(this.player.getId())) {
                    try {
                        client.disconnect();
                    } catch (NetworkException e) {
                        MyLog.getMyLog().log(Level.WARNING, e.getMessage());
                    }
                }
           }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {
        if (this.player.getId().equals(playerId)) {
            this.player.setPrivateObjective(card);
            this.privateObjectiveCard = card;
            Printer.print(card); //todo
            notifyAll();//to wake up the thread
        }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {

        //if it is an update of the chosen window pattern
        if (position == WindowPatternPosition.CHOSEN) {

            //UPDATE OF THE LIST OF THE PLAYERS
            this.players.stream().filter(p -> p.getId().equals(playerId))
                    .findAny().ifPresent(p -> p.setChosenPattern(windowPattern));

            //UPDATE OF MY PLAYER
            if (playerId.equals(this.player.getId()))
                this.player.setChosenPattern(windowPattern);


        } else //if it is an update of the initial set of windows
            if (this.player.getId().equals(playerId)) {//if it is this player's window
                patterns[position.toInt()] = windowPattern;
                numberOfPatternsReceived++;
                notifyAll();//to wake up the thread
                Printer.println("Debug: you received a window pattern in position:" + position.name());
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateToolCard(ToolCard toolCard, int number) {
        this.toolCards[number] = toolCard;
        Printer.println("DEBUG: è arrivato l'update della tool card in posizione: " + number); //todo
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
        synchronized (draftedDieLock){
            draftedDieLock.notifyAll();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updatePublicObjectiveCard(PublicObjectiveCard card, CardPosition position) {
        this.publicObjectiveCards[position.toInt()] = card;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateErrorMessage(String playerId, String message) {
        if(playerId.equals(this.player.getId()))
            Printer.printlnColor(message, ERROR_COLOR);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateEndGame(List<RankingPlayer> rankingPlayers, List<String> disconnectedPlayerId) {

        menu.setExit();

        if(rankingPlayers.get(0).getPlayerId().equals(player.getId()))
            Printer.println(WINNER_MESSAGE);
        else
            Printer.println(LOSER_MESSAGE);

        Printer.println("Ranking:");
        for(int i = 0; i<rankingPlayers.size(); i++){
            Printer.println(i+1 + " -> " + rankingPlayers.get(i).getPlayerId().toUpperCase());
            Printer.print(rankingPlayers.get(i));
        }
        disconnectedPlayerId.stream().forEach(id->{
            Printer.println("Disconnected players:");
            Printer.println(id);
        });
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void updateStatePlayer(String playerId, PlayerState state) {
        Player wantedPlayer;

        Optional<Player> optPlayer = this.players.stream().filter(p -> p.getId().equals(playerId)).findAny();

        if (optPlayer.isPresent()) {
            wantedPlayer = optPlayer.get();
            wantedPlayer.changePlayerStateTo(state);

            if (wantedPlayer.equalsPlayer(player)) {
                player.changePlayerStateTo(state);

                Printer.printlnColor("DEBUG: stato del player:" + state.getClass().getSimpleName(), DieColor.RED);

                if(state.canPlaceDie() && state.canUseTool())
                    Printer.println("It's your turn.");
                notifyAll();//to wake up the thread
            }

        } else {
            //TODO - DUBBIA GESTIONE NEL CASO IN CUI NON SI TROVI UN GIOCATORE CON L'ID GIUSTO
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(ViewUpdaterInterface updater) {
        updater.update(this);
    }


    //----------------------PRIVATE METHODS--------------------------------------------------------------------

    private void startGame() {
        int returnedValue;
        do {
            returnedValue = menu.executeMenu();
        } while(returnedValue != END_GAME_CODE);
    }

    private void startChooseWindow() {
        menu = new PlayerMenu(this);
        int returnedValue;

        do{
            returnedValue = menu.executeMenu();
        }while(returnedValue == Option.EXIT_CODE);

    }


    /**
     * returns true if the player has his state, his private card and his patterns initialized
     *
     * @return true if the player has his state, his private card and his patterns initialized
     */
    private synchronized boolean areInitialOptionsInitialized() {
        return player.getState() != null && privateObjectiveCard != null && numberOfPatternsReceived == 4;
    }


    /**
     * returns true if all players have already chosen a window pattern
     *
     * @return true if all players have already chosen a window pattern
     */
    private synchronized boolean areAllWindowInitialized() {
        boolean temp = true;
        for (Player p : players) {
            if (p.getPattern() == null)
                temp = false;
        }

        return temp;
    }


    private void typeOfConnectionRequest() {
        int choice;

        boolean badChoice;

        do {
            Printer.println(CONNECTION_REQUEST_MESSAGE);
            choice = keyboard.readInt();

            if (choice == RMI_CHOICE) {
                this.client = new RMIClient(this);
                badChoice = false;
            } else if (choice == SOCKET_CHOICE) {
                this.client = new SocketClient(this);
                badChoice = false;
            } else {
                Printer.println(CHOICE_ERROR_MESSAGE);
                badChoice = true;
            }
        } while (badChoice);


        addGameObserver(this.client);
    }

    private void connection() {
        int port;
        String address;
        boolean connectError = true;

        do {
            Printer.println(PORT_REQUEST_MESSAGE);
            port = keyboard.readInt();
            Printer.println(ADDRESS_REQUEST_MESSAGE);
            address = keyboard.readLine();
            try {
                client.connect(address, port);
                connectError = false;
            } catch (NetworkException | NotBoundException e) {
                Printer.println(e.getMessage());
            }
        } while (connectError);

    }

    private void login() {
        String username;
        boolean loginError = true;

        do {
            Printer.println(USERNAME_REQUEST_MESSAGE);
            username = keyboard.readLine();

            this.player = new Player(username);
            try {
                client.login(username);
                loginError = false;
            } catch (LoginException e) {
                try {
                    client.reconnect(username);
                    loginError = false;
                } catch (LoginException e1) {
                    MyLog.getMyLog().log(Level.WARNING, e1.getMessage());
                    this.player = null;
                }

            }
        } while (loginError);

    }

}
