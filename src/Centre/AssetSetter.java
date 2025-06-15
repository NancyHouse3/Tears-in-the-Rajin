package Centre;

import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        placeRevolver(gp,67,40,4,0);

        placeRevolver(gp,40,25,7,1);
        placeRevolver(gp,13,43,25,2);

        placeDoor(gp,42,34,4,3);

        placeFootlocker(gp,24,15,5,4);

        placeSteelHelmet(gp,60,35,5,5);

        placeCocaine(gp,13,35,3,6);
    }

    static void placeRevolver(GamePanel gp,int worldX, int worldY, int quality,int slot) {
        gp.obj[slot] = new OBJ_Revolver();
        gp.obj[slot].worldX = worldX * gp.tileSize;
        gp.obj[slot].worldY = worldY * gp.tileSize;
        gp.obj[slot].itemQuality = quality;
    }

    static void placeDoor(GamePanel gp,int worldX, int worldY, int quality,int slot) {
        gp.obj[slot] = new OBJ_Door();
        gp.obj[slot].worldX = worldX * gp.tileSize;
        gp.obj[slot].worldY = worldY * gp.tileSize;
        gp.obj[slot].itemQuality = quality;
    }

    static void placeFootlocker(GamePanel gp,int worldX, int worldY, int quality,int slot) {
        gp.obj[slot] = new OBJ_Footlocker();
        gp.obj[slot].worldX = worldX * gp.tileSize;
        gp.obj[slot].worldY = worldY * gp.tileSize;
        gp.obj[slot].itemQuality = quality;
    }

    static void placeSteelHelmet(GamePanel gp,int worldX, int worldY, int quality,int slot) {
        gp.obj[slot] = new OBJ_Steel_Helmet();
        gp.obj[slot].worldX = worldX * gp.tileSize;
        gp.obj[slot].worldY = worldY * gp.tileSize;
        gp.obj[slot].itemQuality = quality;
    }

    static void placeCocaine(GamePanel gp,int worldX, int worldY, int quality,int slot) {
        gp.obj[slot] = new OBJ_Cocaine();
        gp.obj[slot].worldX = worldX * gp.tileSize;
        gp.obj[slot].worldY = worldY * gp.tileSize;
        gp.obj[slot].itemQuality = quality;
    }

}
