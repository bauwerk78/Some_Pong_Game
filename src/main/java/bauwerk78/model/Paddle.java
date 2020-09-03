package bauwerk78.model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static bauwerk78.settings.StaticFinals.*;

public class Paddle extends GameObject {

    public Paddle(double posX, double posY) {
        super.setPosX(posX);
        super.setPosY(posY);
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


}
