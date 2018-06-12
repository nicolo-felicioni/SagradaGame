package it.polimi.se2018.view.cli;

import java.util.Scanner;

/**
 * @author Nicolò Felicioni
 */

class MyScanner {
    private Scanner scanner;



    public MyScanner(){
        this.scanner = new Scanner(System.in);
    }


    /**
     * this method returns the line read from standard input.
     * @return a string with the line read from standard input.
     */
    public String readLine(){
        return scanner.nextLine();
    }


    /**
     * this method returns the int read from standard input and ignores the '\n' character.
     * @return the int read from standard input.
     */
    public int readInt(){
        int n = scanner.nextInt();
        scanner.nextLine(); //this consumes the '\n' character
        return n;
    }

}
