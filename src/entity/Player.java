package entity;

import Centre.GamePanel;
import Centre.KeyHandler;
import Centre.Sound;
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
    String character = "Spike";
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

        name = "Spike Spiegel";
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 47;
        worldY = gp.tileSize * 67;
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
    public void update() { // what happens every update

        level = 10;

        healthPoints = (level * 10) * enduranceScore/2;

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
                    gp.ui.showCenterMessage("[Lockpick Success!]",Color.white);
                    gp.obj[i] = null;
                }else {
                    gp.ui.showCenterMessage("[Lockpick Failed!]",Color.red);
                }
            }else if (object.name.equalsIgnoreCase("Cocaine Brick")) {
                speed += object.itemQuality;
                putItemIntoInventory(i);
                gp.ui.showCenterMessage("[Drug High!]",Color.red);
                gp.playSE(2);
            } else {
                putItemIntoInventory(i);
                gp.playSE(1);
            }

        }
    }

    public void putItemIntoInventory(int worldItemListIndex) {
        int openSlot = 837;

        //Picking color based on item quality
        Color textColor;
        if (gp.obj[worldItemListIndex].itemQuality > 6) {
            textColor = Color.yellow;
            if (gp.obj[worldItemListIndex].itemQuality >= 9) {
                textColor = Color.magenta;
            }
        } else if (gp.obj[worldItemListIndex].itemQuality < 4) {
            textColor = Color.orange;
        }else {
            textColor = Color.white;
        }

        for (int s = 0; s < inventory.length; s++) { // checking inventory space
            if (inventory[s] == null) {
                openSlot = s;
            }
        }

        if (openSlot != 837) { // if there is space
            inventory[openSlot] = gp.obj[worldItemListIndex]; // add item to free space
            gp.obj[worldItemListIndex] = null; // cleaning up object // dont draw the object on the map anymore



            gp.ui.showMessage(inventory[openSlot].displayName+" added to your inventory!",textColor);


        } else { // if there is no space
            gp.ui.showCenterMessage("[No Storage Space!]",Color.red);
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
