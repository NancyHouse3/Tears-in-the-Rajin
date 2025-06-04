package Centre;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { // making our game panel screen that is based with JPanel

    // Screen Settings
    final int originalTileSize = 16; // default scale of tiles
    final int scale = 3; // traditional rescale of the standard 16

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16; // 16 wide
    final int maxScreenRow = 12; // 12 tall

    //see? Here's out dimensions!
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // keeps the game going, calls the run method when created

    // setting the player's default position on the screen
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // the player's speed

    public GamePanel() { // making game panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); // this = this class
        gameThread.start();
    }

    @Override
    public void run() { // Created as a result of the thread

        while (gameThread != null) {

            // 1 Update! update information the character performs, like positions
            update();

            // 2 Draw the screen with updated information! Do this based on framerate
            repaint(); // this calls paintComponent! it's weird like that!

        }

        // 1 Update! update information the character performs, like positions
        update();

        // 2 Draw the screen with updated information! Do this based on framerate
        repaint(); // this calls paintComponent! it's weird like that!

    }
    public void update() {

        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
            System.out.println("◘");
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
            System.out.println("☻");
        } else if (keyH.leftPressed == true) {
            playerX += playerSpeed;
            System.out.println("♦");
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
            System.out.println("♠");
        }

    }
    public void paintComponent(Graphics g) { // this isn't actually *new* this exists already ! Called by 'repaint();'!

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // similar but different

        g2.setColor(Color.white);

        g2.fillRect(playerX,playerY,tileSize,tileSize);

        g2.dispose(); // release memories
    }
}
