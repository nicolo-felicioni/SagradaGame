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

    private synchronized List<Player> getPlayers() {
        return players;
    }

    private synchronized Player getPlayer() {
        return player;
    }

    private synchronized WindowPattern[] getPatterns() {
        return patterns;
    }

    private synchronized RoundTrack getRoundTrack() {
        return roundTrack;
    }

    private synchronized DraftPool getDraftPool() {
        return draftPool;
    }

    private synchronized PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }

    private synchronized ToolCard[] getToolCards() {
        return toolCards;
    }

    private synchronized PublicObjectiveCard[] getPublicObjectiveCards() {
        return publicObjectiveCards;
    }

    private List<Player> players;
    private Player player;
    private WindowPattern[] patterns;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private PrivateObjectiveCard privateObjectiveCard;
    private ToolCard[] toolCards;
    private PublicObjectiveCard[] publicObjectiveCards;
    private int numberOfPatternsReceived;

    private static final String WELCOME_MESSAGE = "------------------WELCOME TO SAGRADA!----------------------";
    private static final String CONNECTION_REQUEST_MESSAGE = "What connection do you want? \n1. RMI\n2. Socket";
    private static final String PORT_REQUEST_MESSAGE = "Enter the number of the port: ";
    private static final String ADDRESS_REQUEST_MESSAGE = "Enter the IP address of the server: ";
    private static final String USERNAME_REQUEST_MESSAGE = "Enter your username: ";
    private static final String NOT_YOUR_TURN_MESSAGE = "It's not your turn.";
    private static final String CHOICE_ERROR_MESSAGE = "Not valid choice.";
    private static final int RMI_CHOICE = 1;
    private static final int SOCKET_CHOICE = 2;
    private static final String LOADING_MESSAGE = "Loading...";



    private MyScanner keyboard;


    public CommandLineInterface() {
        players = new ArrayList<>();
        keyboard = new MyScanner();
        patterns = new WindowPattern[Player.N_WINDOW_PATTERNS];
        toolCards = new ToolCard[Model.SET_OF_TOOL_CARDS_SIZE];
        numberOfPatternsReceived = 0;
    }


    public void start() {
        Printer.printlnBold(WELCOME_MESSAGE);

        typeOfConnectionRequest();
        connection();
        login();
        Printer.println(LOADING_MESSAGE);
        while(!areInitialOptionsInitialized()){
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


    private void startGame() {
        boolean exit = false;

        Menu menu = new PlayerMenu();

        while(!exit){
            menu.executeMenu();
        }
    }


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

    private synchronized boolean areInitialOptionsInitialized() {
        return player.getState() != null && privateObjectiveCard != null && numberOfPatternsReceived == 4;
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
            try {
                client.login(username);
                loginError = false;
            } catch (LoginException e) {
                Printer.println(e.getMessage());
            }
        } while (loginError);

        this.player = new Player(username);
    }


    //----------------INNER CLASS PLAYER MENU--------------------------------------------------------------------

    class PlayerMenu extends Menu {

        private static final String ERROR_CHOICE = "Impossible choice.";
        private static final String FIRST_MESSAGE = "Press 'm' for menu.";
        private static final String SELECT_MESSAGE = "Select an option.";
        private static final String MENU_CHOICE = "m";


        @Override
        void executeMenu() {

            boolean validChoice = true;
            int choice;
            String firstInput;

            Printer.println(FIRST_MESSAGE);
            firstInput = keyboard.readLine();
            if (!firstInput.equals(MENU_CHOICE)) {
                Printer.println(ERROR_CHOICE);
            } else {
                do{ //if it was pressed the right key

                    this.options = buildOptions(CommandLineInterface.this.player.getState());
                    Printer.println(SELECT_MESSAGE);
                    Printer.print(options);
                    choice = keyboard.readInt();

                    if (choice == Option.EXIT_CODE)
                        return;
                    if(choice<=0 || choice >options.size()){
                        validChoice=false;
                        Printer.print(ERROR_CHOICE);
                    }else{
                        options.get(choice-1).executeOption();
                    }
                }while(!validChoice);
            }


        }

        private List<Option> buildOptions(PlayerState state) {
            List<Option> options = new ArrayList<>();
            int i = 0;
            if (state.hasChosenWindowPattern()) { //if the player already chose a window pattern
                if (state.canEndTurn()) {//if it's this player's turn
                    if (state.canPlaceDie()) {
                        options.add(i, new PlaceDieOption());
                        i++;
                    }
                    if (state.canUseTool()) {
                        options.add(i, new UseToolOption());
                        i++;
                    }
                    //can end turn
                    options.add(i, new EndTurnOption());
                } else {
                    //if it's not this player's turn
                    return options; //return an empty list of options
                }
            } else {
                //if the player hasn't chosen a window yet he has to choose one
                options.add(i, new ChooseWindowOption());
            }

            return options;
        }


    }


    //----------------INNER CLASS PLACE DIE OPTION--------------------------------------------------------------------


    class PlaceDieOption extends ComplexOption {

        private static final String PLACE_DIE_NAME = "Place a die.";
        private static final int N_OF_CHOICES = 3;
        private int[] choiceVector;


        public PlaceDieOption() {
            this.name = PLACE_DIE_NAME;
            subOptions = buildPlaceDieOptions();
        }


        private List<Option> buildPlaceDieOptions() {
            List<Option> options = new ArrayList<>();
            options.add(0, new ReadDieOption());
            options.add(1, new ReadXOption());
            options.add(2, new ReadYOption());
            return options;
        }

        @Override
        public int executeOption() {
            int choice;
            Die selectedDie;
            Point selectedPoint;
            int[] choiceVector = new int[3]; //this vector contains the choice of the die and of the two coord.


            int i = 0;
            while (i >= 0 && i < subOptions.size()) {
                choice = subOptions.get(i).executeOption();
                if (choice == EXIT_CODE) //go back
                    i--;
                else {
                    choiceVector[i] = choice;
                    i++;
                }
            }
            selectedDie = draftPool.getAllDice().get(choiceVector[0]);
            try {
                selectedPoint = new Point(choiceVector[1], choiceVector[2]);
            } catch (NotValidPointException e) {
                return ERROR_CODE;
            }


            CommandLineInterface.
                    this.notifyObservers(new DraftAndPlaceGameEvent(selectedDie, selectedPoint, player.getId()));

            return 0;

        }
    }


    //----------------INNER CLASS USE TOOL OPTION--------------------------------------------------------------------

    class UseToolOption extends ComplexOption {

        //TODO - TUTTA LA CLASSE ANCORA DA RIFARE
        private static final String USE_TOOL_NAME = "Use a tool card";
        private static final String USE_TOOL_MESSAGE = "Select a tool card";
        private static final String ERROR_USE_TOOL = "There isn't any tool card with that number.";


        public UseToolOption() {
            this.name = USE_TOOL_NAME;
        }


        @Override
        public int executeOption() {
            return 0;
        }

        private int selectToolCard() {
            boolean goodChoice = true;
            int choice;
            do {

                Printer.println(USE_TOOL_MESSAGE);
                for (int i = 0; i < toolCards.length; i++) {
                    int n = i + 1;
                    Printer.print(n);
                    Printer.println(":");
                    Printer.print(toolCards[i]);
                }
                choice = keyboard.readInt();
                if (choice <= 0 || choice > toolCards.length) {
                    Printer.println(ERROR_USE_TOOL);
                    goodChoice = false;
                }
            } while (!goodChoice);

            return (choice - 1);
        }


    }


    //----------------INNER CLASS CHOOSE WINDOW OPTION--------------------------------------------------------------------

    class ChooseWindowOption extends SimpleOption {

        private static final String CHOOSE_WINDOW_NAME = "Choose a window pattern.";
        private static final String CHOOSE_WINDOW_MESSAGE = "Select a window pattern";
        private static final String WRONG_CHOICE_MESSAGE = "There isn't any window with that number.";

        public ChooseWindowOption() {
            this.name = CHOOSE_WINDOW_NAME;
        }

        @Override
        public int executeOption() {
            int choice;
            int n = 0;
            boolean validChoice;
            WindowPattern patternChosen;

            do {
                Printer.println(CHOOSE_WINDOW_MESSAGE);
                for (int i = 0; i < patterns.length; i++) {
                    n = i + 1;
                    Printer.println(n + ":");
                    Printer.print(patterns[i]);
                }
                Printer.print("\n");
                Printer.println(EXIT_CODE + GO_BACK_MESSAGE);

                choice = keyboard.readInt();
                if ((choice <= 0 && choice != EXIT_CODE) || choice > n) { //if the choice isn't in the range
                    Printer.println(WRONG_CHOICE_MESSAGE);
                    validChoice = false;
                } else{
                    validChoice = true;
                    if(choice!=EXIT_CODE){//if the choice is valid and it's not the exit code
                        patternChosen = patterns[choice-1];

                        //throw the event
                        CommandLineInterface.this.notifyObservers(new WindowPatternChosenGameEvent(patternChosen, player.getId()));
                    }

                }


            } while (!validChoice);

            return choice;
        }
    }


    class EndTurnOption extends SimpleOption {

        @Override
        public int executeOption() {
            return 0;
        }
    }


    class ReadDieOption extends SimpleOption {
        final static String WRONG_CHOICE_MESSAGE = "This die doesn't exists.";

        @Override
        public int executeOption() {
            int choice;
            int n = 0;
            boolean validChoice;

            do {
                for (int i = 0; i < draftPool.size(); i++) {
                    n = i + 1;
                    Printer.print(n + ": " + draftPool.getAllDice().get(i) + " ");
                }
                Printer.print("\n");
                Printer.println(EXIT_CODE + GO_BACK_MESSAGE);


                choice = keyboard.readInt();
                if ((choice <= 0 && choice != EXIT_CODE) || choice > n) { //if the choice isn't in the range
                    Printer.println(WRONG_CHOICE_MESSAGE);
                    validChoice = false;
                } else
                    validChoice = true;

            } while (!validChoice);

            return choice;
        }

    }

    class ReadXOption extends SimpleOption {
        final static String READ_X_MESSAGE = "Enter the first coordinate of the space in which you want to place the die."
                + "\nEnter " + EXIT_CODE + "for go back.";
        final static String INFO = "Coordinates starts from 0.";
        final static String WRONG_CHOICE_MESSAGE = "Wrong coordinate.";

        @Override
        public int executeOption() {
            int choice;

            boolean validChoice;
            do {
                Printer.print(player.getPattern());
                Printer.println(READ_X_MESSAGE);
                Printer.println(INFO);
                choice = keyboard.readInt();
                if ((choice < 0 && choice != EXIT_CODE) || choice >= WindowPattern.SPACES_HEIGHT) { //if the choice isn't in the range
                    Printer.println(WRONG_CHOICE_MESSAGE);
                    validChoice = false;
                } else
                    validChoice = true;
            } while (!validChoice);

            return choice;
        }
    }

    class ReadYOption extends SimpleOption {
        final static String READ_Y_MESSAGE = "Enter the second coordinate."
                + "\nEnter " + EXIT_CODE + "for go back.";
        final static String WRONG_CHOICE_MESSAGE = "Wrong coordinate.";

        @Override
        public int executeOption() {
            int choice;

            boolean validChoice;
            do {
                Printer.println(READ_Y_MESSAGE);
                choice = keyboard.readInt();
                if ((choice < 0 && choice != EXIT_CODE) || choice >= WindowPattern.SPACES_LENGTH) { //if the choice isn't in the range
                    Printer.println(WRONG_CHOICE_MESSAGE);
                    validChoice = false;
                } else
                    validChoice = true;
            } while (!validChoice);

            return choice;
        }
    }
}
