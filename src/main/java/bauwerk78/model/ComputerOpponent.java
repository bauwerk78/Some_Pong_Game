package bauwerk78.model;

import bauwerk78.implementer.MainGame;

public class ComputerOpponent extends Paddle {

    private int directionY;
    private int edgePadding = 5;
    private boolean goingUp;

    public ComputerOpponent(double posX, double posY) {
        super(posX, posY);
        init();
    }

    public void init() {
        setSpeedY(100);
    }

    public void update(MainGame mg) {
        //Going down
        if(mg.getBallYPosition() + (mg.getBallHeight() / 2) > getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() + getSpeedY() * MainGame.elapsedTime);
            goingUp = false;
            if(getPosY() + getHeight() >= MainGame.windowHeight - edgePadding) {
                setPosY(MainGame.windowHeight - getHeight() - edgePadding);
            }

        }
        //Going up
        if(mg.getBallYPosition() + (mg.getBallHeight() / 2) < getPosY() + (getHeight() / 2)) {
            setPosY(getPosY() - getSpeedY() * MainGame.elapsedTime);
            goingUp = true;
            if(getPosY() <= edgePadding) {
                setPosY(edgePadding);
            }
        }

        System.out.println(getPosX() + " " + getPosY());
        System.out.println(getWidth() + " " + getHeight());

    }
}
