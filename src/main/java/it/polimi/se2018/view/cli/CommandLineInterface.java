package it.polimi.se2018.view.cli;


import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.event.game.WindowPatternChosenGameEvent;
import it.polimi.se2018.model.WindowPatternPosition;
import it.polimi.se2018.event.game.DraftAndPlaceGameEvent;
import it.polimi.se2018.exceptions.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.network.client.ClientInterface;
import it.polimi.se2018.network.rmi.RMIClient;
import it.polimi.se2018.network.socket.SocketClient;
import it.polimi.se2018.view.AbstractView;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Nicolò Felicioni
 */

public class CommandLineInterface extends AbstractView {

    private ClientInterface client;

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

    private Menu menu;
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


    public MyScanner getKeyboard() {
        return keyboard;
    }

    private MyScanner keyboard;


    public CommandLineInterface() {
        players = new ArrayList<>();
        keyboard = new MyScanner();
        patterns = new WindowPattern[Player.N_WINDOW_PATTERNS];
        toolCards = new ToolCard[Model.SET_OF_TOOL_CARDS_SIZE];
        numberOfPatternsReceived = 0;
    }


    public void start() {
        Printer.printColor(WELCOME_MESSAGE, DieColor.getRandom());

        typeOfConnectionRequest();
        connection();
        login();
        Printer.println(LOADING_MESSAGE);//todo
        while(!areInitialOptionsInitialized()){
            try {
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startChooseWindow();
        Printer.println(LOADING_MESSAGE);//todo

        while(!areAllWindowInitialized()){
            try {
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startGame();

    }




    //-----------------------------UPDATER--------------------------------------------------------------------

    @Override
    public synchronized void updatePlayer(String playerId) {
        Optional<Player> optionalPlayer = this.players.stream()
                .filter(p -> p.getId().equals(playerId)).findAny();

        if (!optionalPlayer.isPresent()) {
            this.players.add(new Player(playerId));

            //TODO - PROVA
            if (!playerId.equals(this.player.getId())) {
                Printer.print("Player '");
                Printer.printColor(playerId, DieColor.BLUE);
                Printer.println("' joined the game.");
            }
        }

    }

    @Override
    public synchronized void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {
        if (this.player.getId().equals(playerId)) {
            this.player.setPrivateObjective(card);
            this.privateObjectiveCard = card;
            Printer.print(card); //todo
            notify();//to wake up the thread
        }


    }

    @Override
    public synchronized void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {

        //if it is an update of the chosen window pattern
        if (position == WindowPatternPosition.CHOSEN)
            this.players.stream().filter(p -> p.getId().equals(playerId))
                    .findAny().ifPresent(p -> p.setChosenPattern(windowPattern));
        else //if it is an update of the initial set of windows
            if (this.player.getId().equals(playerId)) {//if it is this player's window
                patterns[position.toInt()] = windowPattern;
                numberOfPatternsReceived++;
                notify();//to wake up the thread
                Printer.println("Debug: you received a window pattern in position:" + position.name());
            }

    }

    @Override
    public synchronized void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId) {
        Player wantedPlayer;

        Optional<Player> optPlayer = this.players.stream()
                .filter(pl -> pl.getId().equals(playerId)).findAny();

        if (optPlayer.isPresent()) {
            wantedPlayer = optPlayer.get();

            //TODO- DUBBIA GESTIONE DELLE ECCEZIONI
            try {
                wantedPlayer.placeDie(p, draftedDie);
            } catch (PlacementException e) {
                e.printStackTrace();
            } catch (IllegalMoveTurnException e) {
                e.printStackTrace();
            }
        } else {
            //TODO - DUBBIA GESTIONE NEL CASO IN CUI NON SI TROVI UN GIOCATORE CON L'ID GIUSTO
            Printer.println("No such player!");
        }


    }

    @Override
    public synchronized void updateToolCard(ToolCard toolCard, int number) {
        this.toolCards[number] = toolCard;
    }

    @Override
    public synchronized void updateRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
    }

    @Override
    public synchronized void updateDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }

    @Override
    public synchronized void updateStatePlayer(String playerId, PlayerState state) {
        Player wantedPlayer;

        Optional<Player> optPlayer = this.players.stream().filter(p -> p.getId().equals(playerId)).findAny();

        if (optPlayer.isPresent()) {
            wantedPlayer = optPlayer.get();
            wantedPlayer.changePlayerStateTo(state);

            if (wantedPlayer.equalsPlayer(player)) {
                player.changePlayerStateTo(state);
                notify();//to wake up the thread
            }


        } else {
            //TODO - DUBBIA GESTIONE NEL CASO IN CUI NON SI TROVI UN GIOCATORE CON L'ID GIUSTO
            Printer.println("No such player!");
        }
    }

    @Override
    public void handle(ViewUpdaterInterface updater) {
        updater.update(this);
    }


    //----------------------PRIVATE METHODS--------------------------------------------------------------------

    private void startGame() {
        boolean exit = false;
        while(!exit){
            menu.executeMenu();
        }
    }

    private void startChooseWindow() {
        menu = new PlayerMenu(this);

        menu.executeMenu();

    }


    /**
     * returns true if the player has his state, his private card and his patterns initialized
     * @return true if the player has his state, his private card and his patterns initialized
     */
    private synchronized boolean areInitialOptionsInitialized() {
        return player.getState() != null && privateObjectiveCard != null && numberOfPatternsReceived == 4;
    }

    /**
     * returns true if all players have already chosen a window pattern
     * @return true if all players have already chosen a window pattern
     */
    private synchronized boolean areAllWindowInitialized() {
        boolean temp = true;
        for(Player p : players){
            try {
                p.getPattern();
            } catch (GameException e) {
                temp = false;
            }
        }

        return temp;
    }


    private void typeOfConnectionRequest() {
        int choice;

        boolean badChoice = false;

        do {
            Printer.println(CONNECTION_REQUEST_MESSAGE);
            choice = keyboard.readInt();

            if (choice == RMI_CHOICE) {
                this.client = new RMIClient(this);
            } else if (choice == SOCKET_CHOICE) {
                this.client = new SocketClient(this);
            } else {
                Printer.println(CHOICE_ERROR_MESSAGE);
                badChoice = true;
            }
        } while (badChoice);


        addGameObserver(this.client);
        //TODO - DA FINIRE
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
                Printer.println(e.getMessage());
                this.player = null;
            }
        } while (loginError);

    }

}
