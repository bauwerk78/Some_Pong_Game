package bauwerk78.model;

import bauwerk78.tools.Randomize;
import javafx.geometry.Rectangle2D;
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
        setSpeedX(250);
        setWidth(15);
        setHeight(15);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
        //System.out.println(goingRight);
    }

    public void reset() {
        setPosX(windowWidth / 2d);
        setPosY(windowHeight / 2d);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
        ballOutOfBounds = false;
        setSpeedX(250);
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
            setPosY(getPosY() - getSpeedX() * elapsedTime);
        } else {
            setPosY(getPosY() + getSpeedX() * elapsedTime);
        }


        //System.out.println(getPosX() + " " + getPosY());

    }

    public void render(GraphicsContext gc) {
        update();
        gc.setFill(Color.WHITE);
        gc.fillOval(getPosX(), getPosY(), getWidth(), getHeight());

    }

    public Rectangle2D collidingBox() {
        return new Rectangle2D(getPosX(), getPosY(), getWidth(), getHeight());
    }

    public boolean isBallOutOfBounds() {
        return ballOutOfBounds;
    }

    public void setBallOutOfBounds(boolean ballOutOfBounds) {
        this.ballOutOfBounds = ballOutOfBounds;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }
}
