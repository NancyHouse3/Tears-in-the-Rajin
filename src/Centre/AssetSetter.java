package Centre;

import object.OBJ_Door;
import object.OBJ_Footlocker;
import object.OBJ_Revolver;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        placeRevolver(gp,67,40,4,0);

        placeRevolver(gp,34,25,7,1);
        placeRevolver(gp,14,43,25,1);

        placeDoor(gp,19,26,4,2);

        placeFootlocker(gp,24,15,5,3);
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

}
