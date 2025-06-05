package Centre;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { // making our game panel screen that is based with JPanel

    // Screen Settings
    final int originalTileSize = 48; // default scale of tiles. Traditionally, this is 16 but I'll use 48 for reasons explored later

    // traditional rescale of the standard 16 as I said but since I am rocking 48, it'll be 1. Why 48? because the image would be small on the monitor
    final int scale = 1;

    public final int tileSize = originalTileSize * scale; // It is assumed that the tiles are too small so we'll resize them
    final int maxScreenCol = 16; // 16 wide
    final int maxScreenRow = 12; // 12 tall

    //see? Here's out dimensions!
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60; // max framerate

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // keeps the game going, calls the run method when created
    Player player = new Player(this,keyH); // deploying the player !!

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
    public void run() { // Created as a result of the thread. This occurs when the game starts

        double drawInterval = 1000000000/FPS; // the time in between redraws
        double delta = 0; // difference
        long lastTime = System.nanoTime(); // time elapsed
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) { // out loop that goes on infinitely while the game is goin'

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS=" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }
    public void update() { // the things that happen each screen update

        player.update();

    }
    public void paintComponent(Graphics g) { // this isn't actually *new* this exists already ! Called by 'repaint();'!

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // similar but different

        player.draw(g2);

        g2.dispose(); // release memories
    }
}
