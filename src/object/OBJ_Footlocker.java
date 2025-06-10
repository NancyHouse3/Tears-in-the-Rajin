package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Footlocker extends SuperObject{
    public OBJ_Footlocker() {

        name = "Footlocker";
        classification = "container";

        itemQuality = 4; // quality of object, affects performance. (1-8)

        /*
        solidArea.x = 12;
        solidArea.y = 38;
        solidArea.width = 24;
        solidArea.height = 10;
         */

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
