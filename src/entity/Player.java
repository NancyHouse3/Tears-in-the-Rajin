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
    String character = "spike";

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

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_b1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_b2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_f1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_f2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_l1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_l2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_r1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+character+"_r2.png"));

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

            // sprite changing!!
            spriteCounter++;
            System.out.println(spriteCounter);
            int framesPerImage = (-(speed)+16); // sprite FPS will change based on the movement speed
            if (spriteCounter > framesPerImage) { // the # here is how many frames it takes for the sprite to change
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
        g2.drawImage(image,x,y,96,96,null);
    }
}
