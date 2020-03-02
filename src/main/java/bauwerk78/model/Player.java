package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Player extends Paddle {

    private int edgePadding = 5;
    private int player;
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


    public void update(Scene scene) {

        if(UserInput.input.contains(controllerUp)) {
            setPosY(getPosY() - getSpeedY() * MainGame.elapsedTime);
            if(getPosY() <= edgePadding) {
                setPosY(edgePadding);
            }

        }

        if(UserInput.input.contains(controllerDown)) {
            setPosY(getPosY() + getSpeedY() * MainGame.elapsedTime);
            if(getPosY() + getHeight() >= MainGame.windowHeight - edgePadding) {
                setPosY(MainGame.windowHeight - getHeight() - edgePadding);
            }
        }

    }



}
