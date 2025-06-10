package entity;

import Centre.GamePanel;
import Centre.KeyHandler;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    String character = "spike";
    Random random = new Random();
    int characterSize = 1; // how big player will appear on the screen

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        solidArea = new Rectangle();

        solidArea.x = 20;
        solidArea.y = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 8;
        solidArea.height = 8;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 41;
        worldY = gp.tileSize * 27;
        speed = 4;
        direction = "down";

        strengthScore = 5;
        perceptionScore = 5;
        enduranceScore = 5;
        charismaScore = 5;
        intelligenceScore = 5;
        agilityScore = 5;
        luckScore = 5;

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
            } else if (keyH.downPressed == true) {

                direction = "down";
            } else if (keyH.leftPressed == true) {

                direction = "left";
            } else if (keyH.rightPressed == true) {

                direction = "right";
            }

            //Check tile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            interactObject(objIndex);

            // If collision is false, player can move
            if (collisionOn == false) {

                switch (direction) {
                    case "up" : worldY -= speed;
                        break;
                    case "down" : worldY += speed;
                        break;
                    case "left" : worldX -= speed;
                        break;
                    case "right" : worldX += speed;
                        break;
                }
            }

            // sprite changing!!
            spriteCounter++;
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

    public void interactObject(int i) {
        if (i != -1) {

            SuperObject object = gp.obj[i];

            if (object.classification.equalsIgnoreCase("door")) { // lock picking doors
                int lockPickDC = object.itemQuality * 2;
                int playerRoll = random.nextInt(1,21)+lockPickMod;

                if(playerRoll >= lockPickDC) {
                    System.out.println("You lock picked this door!");
                    gp.obj[i] = null;
                }else {
                    System.err.println("Lock pick failed");
                }
            }else {
                putItemIntoInventory(i);
            }

        }
    }

    public void putItemIntoInventory(int worldItemListIndex) {
        int openSlot = 837;

        for (int s = 0; s < inventory.length; s++) { // checking inventory space
            if (inventory[s] == null) {
                openSlot = s;
            }
        }

        if (openSlot != 837) { // if there is space
            inventory[openSlot] = gp.obj[worldItemListIndex]; // add item to free space
            gp.obj[worldItemListIndex] = null; // cleaning up object // dont draw the object on the map anymore

            System.out.println(inventory[openSlot].displayName+" added to your inventory!");
        } else { // if there is no space
            System.err.println("No Storage Space!");
        }
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
        g2.drawImage(image,screenX,screenY,(characterSize * gp.tileSize),(characterSize * gp.tileSize),null);
    }
}
