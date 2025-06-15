package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cocaine extends SuperObject{
    public OBJ_Cocaine() {

        name = "Cocaine Brick";
        classification = "consumable";

        itemQuality = 4; // quality of object, affects performance. (1-8)

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/cocaine.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
