package bauwerk78.model;


import bauwerk78.settings.StaticFinals;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {

    public Paddle() {}

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(getPosX(), getPosY(), getWidth(), getHeight());
    }


}
