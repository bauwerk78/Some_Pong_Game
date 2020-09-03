package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import bauwerk78.settings.GameOptions;
import bauwerk78.tools.Randomize;
import javafx.scene.paint.Color;

import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;


public class Ball extends GameObject {

    private boolean goingUp;
    private boolean goingRight;
    private boolean ballOutOfBounds;

    public Ball(double posX, double posY) {
        setPosX(posX);
        setPosY(posY);
        init();
    }

    private void init() {
        setSpeedX(150);
        setWidth(15);
        setHeight(15);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
    }

    public void reset() {
        setPosX(GameOptions.windowWidth / 2d);
        setPosY(GameOptions.windowHeight / 2d);
        setSpeedX(150);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
        ballOutOfBounds = false;
    }

    private void update() {
        if (getPosX() <= 0 || getPosX() + getWidth() >= GameOptions.windowWidth) {
            ballOutOfBounds = true;
            return;
        }

        if (getPosY() <= 0) {
            goingUp = false;
        }

        if (getPosY() + getHeight() >= GameOptions.windowHeight) {
            goingUp = true;
        }

        if (goingRight) {
            setPosX(getPosX() + getSpeedX() * elapsedTime);
        } else {
            setPosX(getPosX() - getSpeedX() * elapsedTime);
        }

        if (goingUp) {
            setPosY(getPosY() - getSpeedX() * elapsedTime);
        } else {
            setPosY(getPosY() + getSpeedX() * elapsedTime);
        }
    }

    public void render() {
        update();
        MainGame.gc.setFill(Color.WHITE);
        MainGame.gc.fillOval(getPosX(), getPosY(), getWidth(), getHeight());

    }

    public boolean isBallOutOfBounds() {
        return ballOutOfBounds;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }
}
