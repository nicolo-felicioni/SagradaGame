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
        try {
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();//TODO - DUBBIO
            return -1;
        }
    }

}
