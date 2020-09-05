package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.StaticFinals;

import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;

public class ComputerOpponent extends Paddle {

    public ComputerOpponent() {
        init();
    }

    public void init() {
        setWidth(StaticFinals.paddleWidth);
        setHeight(StaticFinals.paddleHeight);
        setPosY((GameOptions.windowHeight / 2d) - (getHeight() / 2));
        setPosX(GameOptions.windowWidth - (StaticFinals.paddleDistanceToWindowSide + StaticFinals.paddleWidth));
        setSpeedY(StaticFinals.paddleSpeedComputer);
    }

    public void update(MainGame mg) {
        //Going down
        if (mg.getBallYPosition() + (mg.getBallHeight() / 2) > getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() + getSpeedY() * elapsedTime);
            if (getPosY() + getHeight() >= GameOptions.windowHeight - StaticFinals.paddleTopBottomPadding) {
                setPosY(GameOptions.windowHeight - getHeight() - StaticFinals.paddleTopBottomPadding);
            }

        }
        //Going up
        if (mg.getBallYPosition() + (mg.getBallHeight() / 2) < getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() - getSpeedY() * elapsedTime);
            if (getPosY() <= StaticFinals.paddleTopBottomPadding) {
                setPosY(StaticFinals.paddleTopBottomPadding);
            }
        }

    }

}
