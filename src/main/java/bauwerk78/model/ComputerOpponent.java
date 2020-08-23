package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import bauwerk78.settings.GameOptions;

import static bauwerk78.settings.Statics.*;

public class ComputerOpponent extends Paddle {

    private boolean goingUp;

    public ComputerOpponent(double posX, double posY) {
        super(posX, posY);
        init();
    }

    public void init() {
        setSpeedY(150);
    }

    public void update(MainGame mg) {
        //Going down
        if(mg.getBallYPosition() + (mg.getBallHeight() / 2) > getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() + getSpeedY() * MainGame.elapsedTime);
            goingUp = false;
            if(getPosY() + getHeight() >= GameOptions.windowHeight - paddleEdgePadding) {
                setPosY(GameOptions.windowHeight - getHeight() - paddleEdgePadding);
            }

        }
        //Going up
        if(mg.getBallYPosition() + (mg.getBallHeight() / 2) < getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() - getSpeedY() * MainGame.elapsedTime);
            goingUp = true;
            if(getPosY() <= paddleEdgePadding) {
                setPosY(paddleEdgePadding);
            }
        }

    }
}
