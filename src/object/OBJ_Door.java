package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door() {

        name = "Door";
        classification = "door";

        itemQuality = 4; // quality of object, affects performance. (1-8)

        /*
        solidArea.x = 12;
        solidArea.y = 6;
        solidArea.width = 25;
        solidArea.height = 42;
         */

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
