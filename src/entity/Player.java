package entity;

import Centre.GamePanel;
import Centre.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_b1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_b2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_f1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_f2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_l1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_l2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_r1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/spike_r2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        // if statement that prevents sprite from animating  when not moving
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) { // movement of character based on keyboard input
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        } // end of the if statement
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up": 
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
}
