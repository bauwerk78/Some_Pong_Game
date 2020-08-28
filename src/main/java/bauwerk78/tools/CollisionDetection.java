package bauwerk78.tools;

import javafx.geometry.Rectangle2D;

public class CollisionDetection {

    public boolean collisionDetection(Rectangle2D object1, Rectangle2D object2) {
        return object1.intersects(object2);
    }

}
