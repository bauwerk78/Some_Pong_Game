package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;

import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;

public class ComputerOpponent extends Paddle {

    public ComputerOpponent() {
        init();
    }

    public void init() {
        setWidth(GameVariables.paddleWidth);
        setHeight(GameVariables.paddleHeight);
        setPosY((GameOptions.windowHeight / 2d) - (getHeight() / 2));
        setPosX(GameOptions.windowWidth - (GameVariables.paddleDistanceToWindowSide + GameVariables.paddleWidth));
        setSpeedY(GameVariables.paddleSpeedComputer);
    }

    public void update(MainGame mg) {
        //Going down
        if (mg.getBallYPosition() + (mg.getBallHeight() / 2) > getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() + getSpeedY() * elapsedTime);
            if (getPosY() + getHeight() >= GameOptions.windowHeight - GameVariables.paddleTopBottomPadding) {
                setPosY(GameOptions.windowHeight - getHeight() - GameVariables.paddleTopBottomPadding);
            }

        }
        //Going up
        if (mg.getBallYPosition() + (mg.getBallHeight() / 2) < getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() - getSpeedY() * elapsedTime);
            if (getPosY() <= GameVariables.paddleTopBottomPadding) {
                setPosY(GameVariables.paddleTopBottomPadding);
            }
        }

    }

}
