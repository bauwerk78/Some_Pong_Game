package bauwerk78.model;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static bauwerk78.settings.StaticFinals.*;

public class Paddle extends GameObject {

    private int playerNumber;

    public Paddle(double posX, double posY) {
        super.setPosX(posX);
        super.setPosY(posY);
        init();
    }
    //TODO implement playerNumber.
    public Paddle(double posX, double posY, int playerNumber) {
        super.setPosX(posX);
        super.setPosY(posY);
        this.playerNumber = playerNumber;
        init();
    }

    private void init() {
        setSpeedY(paddleSpeed);
        setWidth(paddleWidth);
        setHeight(paddleHeight);
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(getPosX(), getPosY(), getWidth(), getHeight());
    }
    //TODO fix upper and front paddle detection.
    public Rectangle2D collidingBox() {
        return new Rectangle2D(getPosX(), getPosY(), getWidth(), getHeight());
    }

}
