package Centre;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Centre { // beginning of the main class, or I chose to call it my 'Centre' because I wanted to be classy and unique.
    static Random random = new Random(); // creating random class
    static Scanner scanner = new Scanner(System.in); // scanner class, just in case. not sure if i will need it but its fun to have!

    public static void main(String[] args) throws IOException { // here we have our general main function that will facilitate processes.

        JFrame window = new JFrame(); // creating a window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tears in the Rajin");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    } // end of the main function

    static void banana() { // i don't know what to call this but this is going to be a sort of prototype for level generation. I'd like to do that sorta randomly?

    } // end of the prototype world generation

} // ending the main function
