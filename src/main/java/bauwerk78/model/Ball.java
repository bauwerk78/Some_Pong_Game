package bauwerk78.model;

import bauwerk78.tools.Randomize;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static bauwerk78.implementer.MainGame.*;

public class Ball extends GameObject implements Randomize {

    private boolean goingUp;
    private boolean goingRight;
    private boolean ballOutOfBounds;

    public Ball(double posX, double posY) {
        super.setPosX(posX);
        super.setPosY(posY);

        init();
    }

    private void init() {
        setSpeedX(90);
        setSpeedY(90);
        setWidth(15);
        setHeight(15);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
        //System.out.println(goingRight);
    }

    private void update() {
        if(getPosX() <= 0 || getPosX() + getWidth() >= windowWidth) {
            ballOutOfBounds = true;
            return;
        }

        if(getPosY() <= 0) {
            goingUp = false;
        }

        if(getPosY() + getHeight() >= windowHeight) {
            goingUp = true;
        }

        if(goingRight) {
            setPosX(getPosX() + getSpeedX() * elapsedTime);
        } else {
            setPosX(getPosX() - getSpeedX() * elapsedTime);
        }

        if(goingUp) {
            setPosY(getPosY() - getSpeedY() * elapsedTime);
        } else {
            setPosY(getPosY() + getSpeedY() * elapsedTime);
        }


        //System.out.println(getPosX() + " " + getPosY());

    }

    public void render(GraphicsContext gc) {
        update();
        gc.setFill(Color.WHITE);
        gc.fillOval(getPosX(), getPosY(), getWidth(), getHeight());

    }

    public boolean isBallOutOfBounds() {
        return ballOutOfBounds;
    }
}
