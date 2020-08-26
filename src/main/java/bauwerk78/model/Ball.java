package bauwerk78.model;

import bauwerk78.tools.Randomize;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import bauwerk78.settings.GameOptions;

import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;


public class Ball extends GameObject {

    private boolean goingUp;
    private boolean goingRight;
    private boolean ballOutOfBounds;

    public Ball(double posX, double posY) {
        super.setPosX(posX);
        super.setPosY(posY);

        init();
    }

    public Ball(double posX, double posY, double speedX) {
        super.setPosX(posX);
        super.setPosY(posY);
        super.setSpeedX(speedX);

        init();
    }

    private void init() {
        super.setSpeedX(150);
        super.setWidth(15);
        super.setHeight(15);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
    }

    public void reset() {
        super.setPosX(GameOptions.windowWidth / 2d);
        super.setPosY(GameOptions.windowHeight / 2d);
        super.setSpeedX(150);
        goingUp = Randomize.randBoolean();
        goingRight = Randomize.randBoolean();
        ballOutOfBounds = false;
    }

    private void update() {
        if(getPosX() <= 0 || getPosX() + getWidth() >= GameOptions.windowWidth) {
            ballOutOfBounds = true;
            return;
        }

        if(getPosY() <= 0) {
            goingUp = false;
        }

        if(getPosY() + getHeight() >= GameOptions.windowHeight) {
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

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }
}
