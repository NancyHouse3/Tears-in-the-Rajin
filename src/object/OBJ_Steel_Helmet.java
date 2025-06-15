package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Steel_Helmet extends SuperObject{
    public OBJ_Steel_Helmet () {

        name = "Steel Helmet";
        classification = "apparel";

        itemQuality = 4; // quality of object, affects performance. (1-8)

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/steel_helmet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
