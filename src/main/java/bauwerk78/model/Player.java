package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import static bauwerk78.settings.Statics.*;
import bauwerk78.settings.GameOptions;

public class Player extends Paddle {

    private final int player;
    private String controllerUp;
    private String controllerDown;

    public Player(double posX, double posY, int player) {
        super(posX, posY);
        this.player = player;
        init();
    }

    private void init() {
        if(player == 1) {
            controllerUp = "W";
            controllerDown = "S";
        } else {
            controllerUp = "UP";
            controllerDown = "DOWN";
        }
    }


    public void update() {

        if(UserInput.input.contains(controllerUp)) {
            setPosY(getPosY() - getSpeedY() * MainGame.elapsedTime);
            if(getPosY() <= paddleEdgePadding) {
                setPosY(paddleEdgePadding);
            }

        }

        if(UserInput.input.contains(controllerDown)) {
            setPosY(getPosY() + getSpeedY() * MainGame.elapsedTime);
            if(getPosY() + getHeight() >= GameOptions.windowHeight - paddleEdgePadding) {
                setPosY(GameOptions.windowHeight - getHeight() - paddleEdgePadding);
            }
        }

    }



}
