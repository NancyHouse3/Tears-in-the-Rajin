package Centre;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { // making our game panel screen that is based with JPanel

    // Screen Settings
    final int originalTileSize = 48; // default scale of tiles. Traditionally, this is 16 but I'll use 48 for reasons explored later

    // traditional rescale of the standard 16 as I said but since I am rocking 48, it'll be 1. Why 48? because the image would be small on the monitor
    final int scale = 1;

    public final int tileSize = originalTileSize * scale; // It is assumed that the tiles are too small so we'll resize them
    public final int maxScreenCol = 16; // 16 wide
    public final int maxScreenRow = 12; // 12 tall

    //see? Here's out dimensions!
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World Settions !!!!!
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    int FPS = 60; // max framerate
    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // keeps the game going, calls the run method when created
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSett = new AssetSetter(this);
    public Player player = new Player(this,keyH); // deploying the player !!
    public SuperObject obj[] = new SuperObject[25]; // Max # of items that can be displayed on the screen

    public GamePanel() { // making game panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupObjects() {

        aSett.setObject();

        for (SuperObject item : obj) {
            if (item != null) {

                // Reassigning the name based on its quality. Quality 4 weapon becomes 'Weapon IV'
                String romanNumeral;
                switch (item.itemQuality) {
                    case 1: romanNumeral = "I";
                        break;
                    case 2: romanNumeral = "II";
                        break;
                    case 3: romanNumeral = "III";
                        break;
                    case 4: romanNumeral = "IV";
                        break;
                    case 5: romanNumeral = "V";
                        break;
                    case 6: romanNumeral = "VI";
                        break;
                    case 7: romanNumeral = "VII";
                        break;
                    case 8: romanNumeral = "VIII";
                        break;
                    case 9: romanNumeral = "IX";
                        break;
                    case 10: romanNumeral = "X";
                        break;
                    case 11: romanNumeral = "XI";
                        break;
                    case 12: romanNumeral = "XII";
                        break;
                    case 13: romanNumeral = "XIII";
                        break;
                    case 14: romanNumeral = "XIV";
                        break;
                    case 15: romanNumeral = "XV";
                        break;
                    case 16: romanNumeral = "XVI";
                        break;
                    default: romanNumeral = ("["+item.itemQuality+"]");
                }

                item.displayName = (item.name+" "+romanNumeral);

            }
        }
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
        int drawCount = 0; // this thing is used for the FPS counter, i'll use it later

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

            if(timer >= 1000000000) { // FPS display
                System.out.println(player.healthPoints);
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

        //TILE
        tileM.draw(g2);

        //OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //ENTITIES
        player.draw(g2);

        g2.dispose(); // release memories
    }
}
