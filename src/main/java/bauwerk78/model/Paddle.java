package bauwerk78.model;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {

    public Paddle(double posX, double posY) {
        super.setPosX(posX);
        super.setPosY(posY);
        init();
    }

    private void init() {
        setSpeedY(250);
        setWidth(20);
        setHeight(80);
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(getPosX(), getPosY(), getWidth(), getHeight());

    }

    public Rectangle2D collidingBox() {
        return new Rectangle2D(getPosX(), getPosY(), getWidth(), getHeight());
    }
}
