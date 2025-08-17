package Centre;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    Font arial_20,arial_22B,arial_16B;
    public boolean generalUIOn = true;
    public boolean messageOn = false;
    public String message = "[message]";
    public Color messageColor = Color.white;
    public boolean messageOn2 = false;
    public String message2 = "[message]";
    public Color messageColor2 = Color.white;

    int timeMessageHasBeenDisplayed;
    int timeMessageHasBeenDisplayed2;

    int uiHeight = 11;
    Color uiColor;
    Color uiColor2;

    int screenW;
    int screenH;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_20 = new Font("Arial",Font.PLAIN,20);
        arial_22B = new Font("Arial",Font.BOLD,22);
        arial_16B = new Font("Arial",Font.BOLD,16);

        screenW = gp.maxScreenCol;
        screenH = gp.maxScreenRow;

        uiHeight = uiHeight * gp.maxScreenRow;

        uiColor = new Color(0f,0f,0f,0.8f);
        uiColor2 = new Color(0f,0f,0f,0.5f);
    }

    public void showMessage(String text, Color color) {
        messageOn = true;
        message = text;
        messageColor = color;
    }
    public void showCenterMessage(String text,Color color) {
        messageOn2 = true;
        message2 = text;
        messageColor2 = color;
    }
    public void draw (Graphics2D g2) {
        // Some of these are not drawn to fit aspect ratio and since
        // 1) my game isn't resizeable and 2) nobody but me will prolly have this, it's fine

        // making sure that the UI is on, so we don't have stuff happen during conversations and such

        // Tutorial
        this.g2 = g2;
        g2.setFont(arial_22B);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            drawPlayerUI();
        }else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public int centerTextWidth(String text) {
        int x;

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = (gp.screenWidth / 2) - (length / 2);

        return x;
    }

    void drawPauseScreen () {

        String text = "[PAUSED]";

        int x = centerTextWidth(text);

        int y = gp.screenHeight / 2;

        g2.drawString(text,x,y);
    }

    void drawPlayerUI () {
        if (generalUIOn) {

            // This is for the UI placement at the bottom.
            int spacing = 2;
            int nameYMult = 39;

            // Setting and Drawing the Stuff
            g2.setColor(uiColor);
            g2.fillRect(0, gp.screenHeight - uiHeight, gp.screenWidth, uiHeight);

            g2.setFont(arial_22B);
            g2.setColor(Color.white);

            g2.drawString(gp.player.name + " |", screenW, screenH * nameYMult);
            g2.setFont(arial_20);
            g2.drawString("HP: " + gp.player.healthPoints, screenW, screenH * (nameYMult + spacing)); // fits to the screen
            g2.drawString("Notifications:", screenW, screenH * (nameYMult + spacing * 2));

            //Notification
            if (messageOn == true) { // this does fit to the screen I think
                g2.setColor(messageColor);
                g2.setFont(g2.getFont().deriveFont(18F));
                g2.drawString(message, screenW, screenH * (nameYMult + spacing * 3));

                timeMessageHasBeenDisplayed++;

                if (timeMessageHasBeenDisplayed > 150) {
                    timeMessageHasBeenDisplayed = 0;
                    messageOn = false;
                }
            }

            //Center screen notifications
            if (messageOn2 == true) {

                g2.setFont(arial_16B);
                int textLength = (int) g2.getFontMetrics().getStringBounds(message2, g2).getWidth();
                int x = gp.screenWidth / 2 - textLength / 2;
                int y = gp.screenHeight / 2 - gp.tileSize;

                g2.setColor(uiColor2);
                g2.fillRect(x, y - 14, textLength, 18);
                g2.setColor(messageColor2);
                g2.drawString(message2, x, y);

                timeMessageHasBeenDisplayed2++;

                if (timeMessageHasBeenDisplayed2 > 150) {
                    timeMessageHasBeenDisplayed2 = 0;
                    messageOn2 = false;
                }
            }

        } // end of general UI check
    }
}
