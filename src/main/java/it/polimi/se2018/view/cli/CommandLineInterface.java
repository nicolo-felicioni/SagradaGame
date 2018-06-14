package it.polimi.se2018.view.cli;


import it.polimi.se2018.controller.ViewUpdaterInterface;
import it.polimi.se2018.model.CardPosition;
import it.polimi.se2018.model.WindowPatternPosition;
import it.polimi.se2018.event.DraftAndPlaceGameEvent;
import it.polimi.se2018.event.UseToolCardGameEvent;
import it.polimi.se2018.event.WindowPatternChosenGameEvent;
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

    private List<Player> players;
    private Player player;
    private WindowPattern[] patterns;
    private RoundTrack roundTrack;
    private DraftPool draftPool;
    private PrivateObjectiveCard privateObjectiveCard;
    private ToolCard[] toolCards;
    private PublicObjectiveCard[] publicObjectiveCards;

    private static final String WELCOME_MESSAGE = "------------------WELCOME TO SAGRADA!----------------------";
    private static final String CONNECTION_REQUEST_MESSAGE = "What connection do you want? \n1. RMI\n2. Socket";
    private static final String PORT_REQUEST_MESSAGE = "Enter the number of the port: ";
    private static final String ADDRESS_REQUEST_MESSAGE = "Enter the IP address of the server: ";
    private static final String USERNAME_REQUEST_MESSAGE = "Enter your username: ";
    private static final String NOT_YOUR_TURN_MESSAGE = "It's not your turn.";
    private static final String CHOICE_ERROR_MESSAGE = "Not valid choice.";
    private static final int RMI_CHOICE = 1;
    private static final int SOCKET_CHOICE = 2;


    private MyScanner keyboard;
    private PlayerMenu menu;


    public CommandLineInterface(){
        players=new ArrayList<>();
        keyboard = new MyScanner();
        patterns = new WindowPattern[Player.N_WINDOW_PATTERNS];
    }


    public void startInitialRequests(){
        Printer.printlnBold(WELCOME_MESSAGE);


        typeOfConnectionRequest();
        connection();
        login();
    }


    @Override
    public void updatePlayer(String playerId){
        Optional<Player> optionalPlayer = this.players.stream()
                .filter(p -> p.getId().equals(playerId)).findAny();

        if(!optionalPlayer.isPresent())
            this.players.add(new Player(playerId));
    }

    @Override
    public void updatePrivateObjectiveCard(String playerId, PrivateObjectiveCard card) {
        this.players.stream().filter(p -> p.getId().equals(playerId))
                .findAny().ifPresent(p -> p.setPrivateObjective(card));
        //TODO IF NOT PRESENT?
    }

    @Override
    public void updateWindowPattern(String playerId, WindowPattern windowPattern, WindowPatternPosition position) {

        //if it is an update of the chosen window pattern
        if(position == WindowPatternPosition.CHOSEN)
            this.players.stream().filter(p -> p.getId().equals(playerId))
                    .findAny().ifPresent(p -> {
                try {
                    p.choosePattern(windowPattern);
                } catch (NotValidPatterException e) {
                    e.printStackTrace(); //TODO GESTIONE ECCEZIONE
                }
            });
        else //if it is an update of the initial set of windows
            if(this.player.getId().equals(playerId)) //if it is this player's window
                patterns[position.toInt()] = windowPattern;
    }

    @Override
    public void updateMoveDieFromDraftToWindow(Point p, Die draftedDie, String playerId) {
        Player wantedPlayer;

        Optional<Player> optPlayer = this.players.stream()
                .filter(pl -> pl.getId().equals(playerId)).findAny();

        if(optPlayer.isPresent()){
            wantedPlayer=optPlayer.get();

            //TODO- DUBBIA GESTIONE DELLE ECCEZIONI
            try {
                wantedPlayer.placeDie(p, draftedDie);
            } catch (PlacementException e) {
                e.printStackTrace();
            } catch (IllegalMoveTurnException e) {
                e.printStackTrace();
            }
        }

        else{
            //TODO - DUBBIA GESTIONE NEL CASO IN CUI NON SI TROVI UN GIOCATORE CON L'ID GIUSTO
            Printer.println("No such player!");
        }



    }

    @Override
    public void updateToolCard(ToolCard toolCard, int number) {
        this.toolCards[number] = toolCard;
    }

    @Override
    public void updateRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
    }

    @Override
    public void updateDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }

    @Override
    public void updateStatePlayer(String playerId, PlayerState state) {

        //TODO - DI QUALI GIOCATORI ARRIVA QUESTO UPDATE?
        //TODO -Può arrivare di qualsiasi giocatore o solo di quello del client?

        Player wantedPlayer;

        Optional<Player> optPlayer = this.players.stream().filter(p -> p.getId().equals(playerId)).findAny();

        if(optPlayer.isPresent()){
            wantedPlayer=optPlayer.get();
            wantedPlayer.changePlayerStateTo(state);

            if(wantedPlayer.equalsPlayer(player)){
                player.changePlayerStateTo(state);
                menu = new PlayerMenu();
                menu.executeMenu();
            }


        } else{
            //TODO - DUBBIA GESTIONE NEL CASO IN CUI NON SI TROVI UN GIOCATORE CON L'ID GIUSTO
            Printer.println("No such player!");
        }
    }

    @Override
    public void handle(ViewUpdaterInterface updater) {
        updater.update(this);
    }


    //----------------------PRIVATE METHODS--------------------------------------------------------------------

    private void typeOfConnectionRequest(){
        int choice;

        boolean badChoice=false;

        do{
            Printer.println(CONNECTION_REQUEST_MESSAGE);
            choice = keyboard.readInt();

            if(choice == RMI_CHOICE){
                this.client = new RMIClient(this);
            }else if(choice == SOCKET_CHOICE){
                this.client = new SocketClient();
            }else{
                Printer.println(CHOICE_ERROR_MESSAGE);
                badChoice = true;
            }
        }while(badChoice);



        Printer.println("è arrivato alla fine");
        addGameObserver(this.client);
        //TODO - DA FINIRE
    }

    private void connection(){
        int port;
        String address;
        boolean connectError = true;

        do{
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
        }while(connectError);

    }

    private void login(){
        String username;
        boolean loginError = true;

        do{
            Printer.println(USERNAME_REQUEST_MESSAGE);
            username = keyboard.readLine();
            try {
                client.login(username);
                loginError = false;
            } catch (LoginException e) {
                Printer.println(e.getMessage());
            }
        }while(loginError);

        this.player = new Player(username);
    }



    //----------------INNER CLASS PLAYER MENU--------------------------------------------------------------------

    class PlayerMenu extends Menu{

        private static final String ERROR_CHOICE = "Impossible choice.";


        public PlayerMenu(){
            super();

            PlayerState state = player.getState();

            int i = 0;

            if(state.hasChosenWindowPattern()){
                if(state.canEndTurn()){
                    if(state.canPlaceDie()){
                        this.options.add(i, new PlaceDieOption());
                        i++;
                    }
                    if(state.canUseTool()){
                        this.options.add(i, new UseToolOption());
                        i++;
                    }

                    //this.options.add(i, new EndTurnOption);
                }
            }else
                this.options.add(i, new ChooseWindowOption());
        }

        public void executeMenu(){
            boolean goodChoice = true;
            int choice;

            do{
                showMenu();
                choice = keyboard.readInt();
                if(choice <=0 || choice > options.size()) {
                    Printer.println(ERROR_CHOICE);
                    goodChoice = false;
                }
            }while(!goodChoice);

            executeOption(choice);
        }

        public void showMenu(){
            for(int i=0; i<options.size(); i++){
                Printer.print(i+1);
                Printer.println(": " + options.get(i).getName());
            }
        }


        public void executeOption(int choice){
            options.get(choice-1).executeOption();
        }

    }


    //----------------INNER CLASS PLACE DIE OPTION--------------------------------------------------------------------


    class PlaceDieOption extends Option {

        private static final String SELECT_DIE_MESSAGE = "Select a die from the draftpool: ";
        private static final String ERROR_SELECT_DIE_MESSAGE = "There isn't any die with that number.";
        private static final String SELECT_SPACE_MESSAGE = "Select a space in your window.";
        private static final String SELECT_SPACE_INFO_MESSAGE = "Enter the first coordinate, then press enter, enter the second coordinate and finally press enter.\nCoordinates starts from 0.";
        private static final String ERROR_SELECT_SPACE_MESSAGE = "There isn't any space with that coordinates.";


        public PlaceDieOption(){
            this.name="Place a die";
        }


        @Override
        public void executeOption() {
            Die selectedDie = selectDie();
            Point p = selectSpace();
            notifyObservers(new DraftAndPlaceGameEvent(selectedDie, p, player.getId()));
        }

        private Point selectSpace() {

            int x, y;
            boolean goodChoice;


            do{
                Printer.print(player.getPattern());
                Printer.println(SELECT_SPACE_MESSAGE);
                Printer.println(SELECT_SPACE_INFO_MESSAGE);
                x = keyboard.readInt();
                y = keyboard.readInt();

                try {
                    return new Point(x, y);
                } catch (NotValidPointException e) {
                    Printer.println(ERROR_SELECT_SPACE_MESSAGE);
                    goodChoice = false;
                }

            }while(!goodChoice);

            return null; //TODO STRANO
        }

        private Die selectDie(){
            Die selectedDie;
            int number, choice;
            boolean goodChoice = true;


            do{

                Printer.println(SELECT_DIE_MESSAGE);

                for(int i = 0; i<draftPool.size(); i++){
                    number = i+1;
                    Printer.print(number);
                    Printer.print(": ");
                    Printer.print(draftPool.getAllDice().get(i));
                }


                choice = keyboard.readInt();
                if(choice < 1 || choice > draftPool.size()){
                    Printer.println(ERROR_SELECT_DIE_MESSAGE);
                    goodChoice = false;
                }


            }while (!goodChoice);


            return draftPool.getAllDice().get(choice - 1);
            }


    }


    //----------------INNER CLASS USE TOOL OPTION--------------------------------------------------------------------

    class UseToolOption extends Option {

        private static final String USE_TOOL_NAME = "Use a tool card";
        private static final String USE_TOOL_MESSAGE = "Select a tool card";
        private static final String ERROR_USE_TOOL = "There isn't any tool card with that number.";


        public UseToolOption(){
            this.name = USE_TOOL_NAME;
        }


        @Override
        public void executeOption() {
            int selectedToolCard = selectToolCard();
            notifyObservers(new UseToolCardGameEvent(CardPosition.fromInt(selectedToolCard), player.getId()));
        }

        private int selectToolCard() {
            boolean goodChoice = true;
            int choice;
            do{

                Printer.println(USE_TOOL_MESSAGE);
                for(int i =0; i<toolCards.length; i++){
                    int n = i+1;
                    Printer.print(n);
                    Printer.println(":");
                    Printer.print(toolCards[i]);
                }
                choice = keyboard.readInt();
                if(choice <=0 || choice > toolCards.length){
                    Printer.println(ERROR_USE_TOOL);
                    goodChoice = false;
                }
            }while(!goodChoice);

            return (choice - 1);
        }


    }


    //----------------INNER CLASS CHOOSE WINDOW OPTION--------------------------------------------------------------------

    class ChooseWindowOption extends Option{

        private static final String CHOOSE_WINDOW_NAME = "Choose a window pattern.";
        private static final String CHOOSE_WINDOW_MESSAGE = "Select a window pattern";
        private static final String ERROR_CHOOSE_WINDOW = "There isn't any window with that number.";


        public ChooseWindowOption(){
            this.name = CHOOSE_WINDOW_NAME;
        }


        @Override
        public void executeOption() {
            int numberOfChosenWindow = selectWindow();
            notifyObservers(new WindowPatternChosenGameEvent(patterns[numberOfChosenWindow], player.getId()));
        }

        private int selectWindow() {
            boolean goodChoice = true;
            int choice;
            do{

                Printer.println(CHOOSE_WINDOW_MESSAGE);
                for(int i =0; i<patterns.length; i++){
                    int n = i+1;
                    Printer.print(n);
                    Printer.println(":");
                    Printer.print(patterns[i]);
                }
                choice = keyboard.readInt();
                if(choice <=0 || choice > patterns.length){
                    Printer.println(ERROR_CHOOSE_WINDOW);
                    goodChoice = false;
                }
            }while(!goodChoice);

            return (choice - 1);
        }
    }

}
