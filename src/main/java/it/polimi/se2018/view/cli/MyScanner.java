package it.polimi.se2018.view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Nicolò Felicioni
 */

public class MyScanner {
    private BufferedReader in;
    private static final String INPUT_ERROR = "Wrong input, not a number.\nEnter a number.";


    /**
     * constructor.
     */
    public MyScanner(){
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }


    /**
     * this method returns the line read from standard input.
     * @return a string with the line read from standard input.
     */
    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();//TODO- DUBBIO
            return null;
        }
    }


    /**
     * this method returns the int read from standard input and ignores the '\n' character.
     * @return the int read from standard input.
     */
    public int readInt() {
        boolean validInput;
        int n = -1;
        String input;

        do{
            try {
                input = in.readLine();
            } catch (IOException e) {
                return n;
            }

            try{
                n = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e){
                Printer.println(INPUT_ERROR);
                validInput = false;
            }

        }while(!validInput);

        return n;
    }

}
