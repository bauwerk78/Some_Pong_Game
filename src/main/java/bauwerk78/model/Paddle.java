package bauwerk78.model;


import bauwerk78.implementer.MainGame;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {

    public Paddle() {
    }

    public void render() {
        MainGame.gc.setFill(Color.WHITE);
        MainGame.gc.fillRect(getPosX(), getPosY(), getWidth(), getHeight());
    }


}
