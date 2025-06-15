package Centre;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Centre { // beginning of the main class, or I chose to call it my 'Centre' because I wanted to be classy and unique.
    static Random random = new Random(); // creating random class
    static Scanner scanner = new Scanner(System.in); // scanner class, just in case. not sure if i will need it but its fun to have!

    public static void main(String[] args) throws IOException { // here we have our general main function that will facilitate processes.

        mapGeneration("compound");
        gameStartup();

    } // end of the main function

    static void mapGeneration(String fileName) throws IOException { // i don't know what to call this but this is going to be a sort of prototype for level generation. I'd like to do that sorta randomly?

        File map = new File("src/rawImages/"+fileName+".png");
        File newMapFile = new File("assets/maps",fileName+".txt");

        if (map == null) {
            System.err.println("File not found.");
        }

        try {
            BufferedImage mapBase = ImageIO.read(map);

            FileWriter writer = new FileWriter(newMapFile);

            int imageX = 0;
            int imageY = 0;

            int maxWidth = mapBase.getWidth();
            int maxHeight = mapBase.getHeight();

            while (imageX < maxWidth && imageY < maxHeight) {

                int rgb = mapBase.getRGB(imageX,imageY);

                float hsb[] = new float[3]; // color conversion
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >>  8) & 0xFF;
                int b = (rgb      ) & 0xFF;
                Color.RGBtoHSB(r, g, b, hsb);
                int tileValue = 0;

                float deg0 = hsb[0]*360;
                int deg2 = (int) deg0;

                // color checking

                /*
                if (hsb[1] < 0.1 && hsb[2] > 0.9) { // sand
                    tileValue = 0;
                } else if (hsb[2] >= 0.4 && hsb[2] <= 0.8 && hsb[1] < 0.3) {
                    tileValue = 9;
                } else if (hsb[2] < 0.1) {
                    tileValue = 4;
                } else {
                    float deg = hsb[0]*360;
                    if (deg >= 0 && deg < 30 && hsb[2] >= 0.6) { // brick
                        tileValue = 3;
                    } else if (deg >= 0 && deg < 30 && hsb[2] < 0.6) {
                        tileValue = 8;
                    } else if (deg >= 25 && deg < 50 && hsb[2] < 0.5) {
                        tileValue = 6;
                    } else if (deg >= 50 && deg < 90 && hsb[2] < 0.5) {
                        tileValue = 7;
                    } else if (deg >= 50 && deg < 90 ) {
                        tileValue = 0;
                    } else if (deg >= 90 && deg < 150 && hsb[2] >= 0.5) { // well kept grass
                        tileValue = 1;
                    } else if (deg >= 90 && deg < 150 && hsb[2] < 0.5) { // standard grass
                        tileValue = 2;
                    } else if (deg >= 150 && deg < 210 ) {
                        tileValue = 5;
                    } else if (deg >= 210 && deg < 270 ) {
                        tileValue = 5;
                    } else if (deg >= 270 && deg < 330 ) {
                        tileValue = 0;
                    } else { // brick
                        tileValue = 3;
                    }
                }
                */

                switch (deg2) {
                    case 0 :
                        tileValue = 3;
                        break;
                    case 3 :
                        tileValue = 8;
                        break;
                    case 37 :
                        tileValue = 6;
                        break;
                    case 51 :
                        if (hsb[1] > 0.15) {
                            tileValue = 10; // side facing
                        }else {
                            tileValue = 11; // top facing
                        }
                        break;
                    case 63 :
                        tileValue = 7;
                        break;
                    case 102 :
                        tileValue = 2;
                        break;
                    case 119 :
                        tileValue = 1;
                        break;
                    case 240 :
                        if (hsb[2] > .8) {
                            tileValue = 12; // side facing
                        }else {
                            tileValue = 9; // top facing
                        }
                        break;
                    case 283 :
                        tileValue = 5;
                        break;
                    default :
                        tileValue = 0;
                }

                writer.write(tileValue+" ");

                imageX++;
                if (imageX == maxWidth) { // going to the next row
                    imageX = 0;
                    imageY++;
                    writer.write("\n");
                }
            }

            writer.close();
            System.out.println("wrote to file");

        } catch (IOException e) {
            System.err.println("Oopsie!");
        }
    } // end of the prototype world generation

    static void gameStartup () {

        ImageIcon icon = new ImageIcon("src\\diamond.png");

        JFrame window = new JFrame(); // creating a window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tears in the Rajin");
        window.setIconImage(icon.getImage());

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupObjects();
        gamePanel.startGameThread();
    }

} // ending the main function
