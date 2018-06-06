package it.polimi.se2018.view;

import it.polimi.se2018.exceptions.DraftPoolEmptyException;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;
import it.polimi.se2018.model.*;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.ansi;

public class Printer {

    static final String SQUARE_SYMBOL = "\u25A0";
    static final String OPEN_BRACKET = "[";
    static final String CLOSED_BRACKET = "]";
    static final String FULL_CIRCLE = "\u25CF";


    public static void print(String s){
       System.out.print(Ansi.ansi().fg(Ansi.Color.DEFAULT).a(s));
       Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    public static void printColor(String s, DieColor color){
        System.out.print(Ansi.ansi().fg(color.toAnsiColor()).a(s));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    public static void printBold(String s){
        System.out.print(Ansi.ansi().eraseScreen().fgBright(Ansi.Color.DEFAULT).a(s).bold());
    }


    public static void print(Die die) {
        System.out.print(ansi().eraseScreen().fg(die.getColor().toAnsiColor()).a(die.getValueUnicode()));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    public static void print(Space space){
        if(space.hasDie())
            Printer.print(space.getDie());
        else if(space.isColorRestricted())
            Printer.printColorSpace(space.getColorRestriction());
        else if(space.isValueRestricted())
            Printer.printValueSpace(space.getValueRestriction());
        else
            Printer.printBlankSpace();
    }

    public static void print(WindowPattern windowPattern){

        print("Difficulty : " + windowPattern.getDifficulty() + "\n");

        for(int i=0; i<WindowPattern.SPACES_HEIGTH; i++){
            for(int j=0; j<WindowPattern.SPACES_LENGTH; j++){
                try {
                        print(windowPattern.getSpace(i, j));
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }

            }
            newLine();
        }

    }

    //TODO - IN ATTESA DELLE MODIFICHE AL ROUNDTRACK
    public static void print(RoundTrack roundTrack) throws RoundTrackEmptyException, NotValidRoundException {
        for (int i = 1; i <= 10; i++) {
            print("[");
            if (roundTrack.getDice(i) == null){
                print(i);
            }else{
                roundTrack.getDice(i).stream().forEach(Printer::print);
            }
            print("] ");
        }

        newLine();
    }

    public static void print(DraftPool draftPool){

        print(OPEN_BRACKET);
        try {
            draftPool.getAllDice().stream().forEach(Printer::print);
        } catch (DraftPoolEmptyException e) {

        }finally {
            print(CLOSED_BRACKET);
            newLine();
        }



    }

    public static void print(int n) {
        System.out.print(n);
    }

    public static void printFavorTokens(int numberOfTokens){
        for(int i=0; i< numberOfTokens; i++)
            print(FULL_CIRCLE);

        newLine();
    }

    public static void print(ToolCard toolCard){
        printCard(toolCard);
        if(toolCard.isUsed()){
            print("\nFavor tokens spent: ");
            printFavorTokens(toolCard.getFavorTokensSpent());
        }

    }

    public static void print(PublicObjectiveCard publicObjectiveCard){
        printCard(publicObjectiveCard);
    }

    //TODO - NOME E DESCRIZIONE DA SISTEMARE
    public static void print(PrivateObjectiveCard card){
        print("Your private objective card is: ");
        printColor(card.getInfo(), card.getColor());
        newLine();
    }







    private static void newLine(){
        print("\n");
    }

    private static void printBlankSpace(){
        System.out.print(ansi().eraseScreen().fg(Ansi.Color.WHITE).a(OPEN_BRACKET+SQUARE_SYMBOL+CLOSED_BRACKET));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    private static void printValueSpace(DieValue value){
        System.out.print(ansi().eraseScreen().fg(Ansi.Color.DEFAULT).a(OPEN_BRACKET+value.toUnicode()+CLOSED_BRACKET));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    private static void printColorSpace(DieColor color){
        System.out.print(ansi().eraseScreen().fg(color.toAnsiColor()).a(OPEN_BRACKET+SQUARE_SYMBOL+CLOSED_BRACKET));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    private static void printCard(Card card){
        printBold(card.getName());
        newLine();
        print("Info : "+ card.getInfo());
        newLine();
    }

}