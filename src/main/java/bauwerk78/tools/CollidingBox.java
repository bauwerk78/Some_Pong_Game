package bauwerk78.tools;

import javafx.geometry.Rectangle2D;

public class CollidingBox {

    public CollidingBox() {

    }
    public CollidingBox(double posX, double posY, double width, double height) {

    }

    //TODO fix upper and front paddle detection.
    public Rectangle2D collidingBox(double posX, double posY, double width, double height) {
        return new Rectangle2D(posX, posY, width, height);
    }
}
