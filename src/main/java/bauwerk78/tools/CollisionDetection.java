package bauwerk78.tools;

import javafx.geometry.Rectangle2D;

public class CollisionDetection {

    public boolean isCollided(Rectangle2D object1, Rectangle2D object2) {
        return object1.intersects(object2);
    }

/*    //Check for collision on paddles.
    public int isCollided(Rectangle2D paddleTop, Rectangle2D paddleBottom, Rectangle2D paddleSide, Rectangle2D ballObject) {
        if (paddleTop.intersects(ballObject)) {
            return 1;
        } else if(paddleBottom.intersects(ballObject)) {
            return 2;
        }
        else if (paddleSide.intersects(ballObject)) {
            return 3;
        }
        return 0;
    }*/
}
