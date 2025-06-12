package entity;

import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

// Hi! Welcome to ENTITY! this is our super class ! :)
public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int compliance = 50; // used for companions and slaves. If people are mistreated, they may rebel

    //SPECIAL
    public int strengthScore,perceptionScore,enduranceScore,charismaScore,intelligenceScore,agilityScore,luckScore;
    public int strScoreMod = strengthScore / 2;
    public int perScoreMod = perceptionScore / 2;
    public int endScoreMod = enduranceScore / 2;
    public int chaScoreMod = charismaScore / 2;
    public int intScoreMod = intelligenceScore / 2;
    public int aglScoreMod = agilityScore / 2;
    public int lckScoreMod = luckScore / 2;

    //SKILLS

    public int lockPickBase = 0;
    public int lockPickScore = lockPickBase + perceptionScore;
    public int lockPickMod = lockPickScore / 8;

    // Basics
    public int level = 1;
    public int healthPoints = 14;
    public int radiationDamage; // 0-1000

    // technical nerd shit

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public SuperObject inventory[] = new SuperObject[25];
    public boolean collisionOn = false;

}
