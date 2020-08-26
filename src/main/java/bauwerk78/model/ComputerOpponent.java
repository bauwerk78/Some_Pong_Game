package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import bauwerk78.settings.GameOptions;

import static bauwerk78.settings.StaticFinals.*;
import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;

public class ComputerOpponent extends Paddle {

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
            setPosY(getPosY() + getSpeedY() * elapsedTime);
            if(getPosY() + getHeight() >= GameOptions.windowHeight - paddleEdgePadding) {
                setPosY(GameOptions.windowHeight - getHeight() - paddleEdgePadding);
            }

        }
        //Going up
        if(mg.getBallYPosition() + (mg.getBallHeight() / 2) < getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() - getSpeedY() * elapsedTime);
            if(getPosY() <= paddleEdgePadding) {
                setPosY(paddleEdgePadding);
            }
        }

    }
}
