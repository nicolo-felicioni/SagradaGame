package it.polimi.se2018.view.cli;

import it.polimi.se2018.controller.utils.RankingPlayer;
import it.polimi.se2018.exceptions.NotValidPointException;
import it.polimi.se2018.exceptions.NotValidRoundException;
import it.polimi.se2018.exceptions.RoundTrackEmptyException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.options.Option;
import org.fusesource.jansi.Ansi;


import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;


/**
 * @author Nicolò Felicioni
 */


public class Printer {
    private static final String SQUARE_SYMBOL = "\u25A0";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSED_BRACKET = "]";
    private static final String FULL_CIRCLE = "\u25CF";


    /**
     * private constructor that hides the implicit one.
     */
    private Printer() {
    }


    /**
     * Print a string.
     *
     * @param s the string to be printed
     */
    public static void print(String s) {
        System.out.print(Ansi.ansi().fg(Ansi.Color.DEFAULT).a(s));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }


    /**
     * Print a string with a new line character in the end.
     *
     * @param s the string to be printed
     */
    public static void println(String s) {
        print(s);
        newLine();
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a colored string.
     *
     * @param s     the string to be printed
     * @param color the color of the string
     */
    public static void printColor(String s, DieColor color) {
        System.out.print(Ansi.ansi().fg(color.toAnsiColor()).a(s));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a colored string with a new line character in the end.
     *
     * @param s     the string to be printed
     * @param color the color of the string
     */
    public static void printlnColor(String s, DieColor color) {
        System.out.print(Ansi.ansi().fg(color.toAnsiColor()).a(s));
        newLine();
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a string in bold
     *
     * @param s the string to be printed
     */
    public static void printBold(String s) {
        System.out.print(Ansi.ansi().eraseScreen().fgBright(Ansi.Color.DEFAULT).a(s).bold());
    }

    /**
     * print a string in bold with a new line character in the end.
     *
     * @param s the string to be printed
     */
    public static void printlnBold(String s) {
        printBold(s);
        newLine();
    }


    /**
     * print a die.
     * the die will be printed with his value and his color.
     *
     * @param die the die to be printed
     */
    public static void print(Die die) {
        System.out.print(ansi().eraseScreen().fg(die.getColor().toAnsiColor()).a(die.getValueUnicode()));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a space.
     * it will have either a color restriction, a value restriction or no restriction.
     * if it has a color restriction it will be printed colored.
     * if it has a value restriction it will be printed with a grey die.
     * if it hasn't any restriction it will be printed like a blank square.
     *
     * @param space
     */
    public static void print(Space space) {

        if (space.hasDie())
            Printer.printSpaceWithDie(space);
        else if (space.isColorRestricted())
            Printer.printColorSpace(space.getColorRestriction());
        else if (space.isValueRestricted())
            Printer.printValueSpace(space.getValueRestriction());
        else
            Printer.printBlankSpace();
    }

    /**
     * private utility method, it will print a space with a die in it with two brackets with a die within.
     *
     * @param space the space to be printed
     * @see #print(Space)
     */
    private static void printSpaceWithDie(Space space) {
        if (space.isColorRestricted()) {
            DieColor color = space.getColorRestriction();
            printColor(OPEN_BRACKET, color);
            print(space.getDie());
            printColor(CLOSED_BRACKET, color);
        } else {
            print(OPEN_BRACKET);
            print(space.getDie());
            print(CLOSED_BRACKET);
        }
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }


    /**
     * print the window pattern.
     *
     * @param windowPattern the window pattern to be printed
     */
    public static void print(WindowPattern windowPattern) {

        println(windowPattern.getName());

        print("Difficulty : " + windowPattern.getDifficulty() + "\n");

        for (int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
            for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
                try {
                    print(windowPattern.getSpace(i, j));
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }

            }
            newLine();
        }

    }

    /**
     * print the window pattern without the name and the difficulty.
     *
     * @param windowPattern the window pattern to be printed
     */
    public static void printOnlySpaces(WindowPattern windowPattern) {

        for (int i = 0; i < WindowPattern.SPACES_HEIGHT; i++) {
            for (int j = 0; j < WindowPattern.SPACES_LENGTH; j++) {
                try {
                    print(windowPattern.getSpace(i, j));
                } catch (NotValidPointException e) {
                    e.printStackTrace();
                }

            }
            newLine();
        }

    }


    /**
     * print the round track.
     * if a box is with no die it will be printed with its number.
     * if it is filled with dice it will be printed like a series of dice.
     *
     * @param roundTrack the round track to be printed
     */
    public static void print(RoundTrack roundTrack) {


        for (int round = 1; round <= RoundTrack.SIZE; round++) {
            print(round + ": ");
            print("[");
            try {
                if (roundTrack.getDice(round).isEmpty()) {
                    print(" ");
                } else {
                    roundTrack.getDice(round).forEach(Printer::print);
                }
                print("] ");
            } catch (NotValidRoundException e) {
                print(" ] ");
            }
        }
        newLine();
    }


    /**
     * print the dice present in the draft pool.
     *
     * @param draftPool the draft pool from which we print the dice
     */
    public static void print(DraftPool draftPool) {
        int n;
        for (int i = 0; i < draftPool.size(); i++) {
            n = i + 1;
            Printer.print(" " + n + ": ");
            Printer.print(draftPool.getAllDice().get(i));
        }
        newLine();
    }

    /**
     * print a number.
     *
     * @param n the number that will be printed
     */
    public static void print(int n) {
        System.out.print(n);
    }


    /**
     * print the favor tokens.
     * this method will print a numberOfTokens tokens, printed like full white circles.
     *
     * @param numberOfTokens the number of tokens that will be printed
     */
    public static void printFavorTokens(int numberOfTokens) {
        if (numberOfTokens == 0)
            print("0");
        else for (int i = 0; i < numberOfTokens; i++)
            print(FULL_CIRCLE);

        newLine();
    }

    /**
     * print the tool card.
     * this method will print the name and the info of the tool card.
     *
     * @param toolCard the tool card that will be printed
     */
    public static void print(ToolCard toolCard) {
        printCard(toolCard);
        if (toolCard.isUsed()) {
            print("\nFavor tokens spent: ");
            printFavorTokens(toolCard.getFavorTokensSpent());
        }

    }

    /**
     * print the public objective card.
     * this method will print the name and the info of the card.
     *
     * @param publicObjectiveCard the card that will be printed
     */
    public static void print(PublicObjectiveCard publicObjectiveCard) {
        printCard(publicObjectiveCard);
    }


    /**
     * print the private objective card.
     * this method will print the name and the info of the card.
     *
     * @param privateObjectiveCard the card that will be printed
     */
    public static void print(PrivateObjectiveCard privateObjectiveCard) {
        print("Your private objective card is: ");
        printColor(privateObjectiveCard.getName(), privateObjectiveCard.getColor());
        newLine();
    }


    /**
     * this method shows to the user a list of options.
     *
     * @param options a list of options that has to be shown.
     */
    public static void print(List<Option> options) {
        for (int i = 0; i < options.size(); i++) {
            print(i + 1);
            print(". ");
            println(options.get(i).getName());
        }
    }


    /**
     * print a boolean with a new line character in the end.
     *
     * @param b the boolean to be printed
     */
    public static void println(boolean b) {
        System.out.println(b);
    }

    /**
     * print a boolean.
     *
     * @param b the boolean to be printed
     */
    public static void print(boolean b) {
        System.out.print(b);
    }

    /**
     * the method prints the player name and his points.
     *
     * @param rankingPlayer the player to be printed.
     */
    public static void print(RankingPlayer rankingPlayer) {
        Printer.println("Points: " + rankingPlayer.getPoints());
        Printer.println("Points from private objective card: " + rankingPlayer.getPointsFromPrivateObjective());
        Printer.println("Remaining favor tokens: " + rankingPlayer.getFavorTokensRemaining());

        //todo
        //Printer.println("Reverse order in the final round: " + rankingPlayer.getReverseOrderFinalRound());
    }


    //-----PRIVATE METHODS----------------------------------------------------------------------------------

    /**
     * print a new line character
     */
    private static void newLine() {
        print("\n");
    }

    /**
     * print a blank space as a white square
     * Used by {@link #print(Space)}
     */
    private static void printBlankSpace() {
        System.out.print(ansi().eraseScreen().fg(Ansi.Color.WHITE).a(OPEN_BRACKET + SQUARE_SYMBOL + CLOSED_BRACKET));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a value space as a grey die.
     * Used by {@link #print(Space)}
     *
     * @param value the value of the value space
     */
    private static void printValueSpace(DieValue value) {
        System.out.print(ansi().eraseScreen().fg(Ansi.Color.DEFAULT).a(OPEN_BRACKET + value.toUnicode() + CLOSED_BRACKET));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a value space as a colored square.
     * Used by {@link #print(Space)}
     *
     * @param color the color of the color space
     */
    private static void printColorSpace(DieColor color) {
        System.out.print(ansi().eraseScreen().fg(color.toAnsiColor()).a(OPEN_BRACKET + SQUARE_SYMBOL + CLOSED_BRACKET));
        Ansi.ansi().fgBright(Ansi.Color.DEFAULT);
    }

    /**
     * print a generic card name and info.
     *
     * @param card the card to be printed.
     */
    private static void printCard(Card card) {
        printBold(card.getName());
        newLine();
        print("Info : " + card.getInfo());
        newLine();
    }

}