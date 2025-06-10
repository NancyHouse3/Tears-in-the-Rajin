package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Revolver extends SuperObject{
    public OBJ_Revolver() {

        ranged = true;

        name = "Revolver";
        classification = "Weapon";

        itemQuality = 4; // quality of object, affects performance. (1-8)

        /*
        solidArea.x = 20;
        solidArea.y = 41;
        solidArea.width = 8;
        solidArea.height = 5;
         */

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
