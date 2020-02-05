package bauwerk78.model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {

    public Paddle() {

    }

    public Paddle(double posX, double posY) {
        super.setPosX(posX);
        super.setPosY(posY);
        init();
    }

    private void init() {
        setSpeedY(100);
        setWidth(20);
        setHeight(80);
    }

    private void update() {

    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(getPosX(), getPosY(), getWidth(), getHeight());
        System.out.println(getPosX() + " " + getPosY());
        System.out.println(getWidth() + " " + getHeight());
    }
}
