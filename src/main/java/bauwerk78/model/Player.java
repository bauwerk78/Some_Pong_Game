package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Player extends Paddle {

    private UserInput userInput = new UserInput();
    private List<String> playerInput = new ArrayList<>();
    private int edgePadding = 5;

    public Player(double posX, double posY) {
        super(posX, posY);

    }

    public void update(Scene scene) {
        userInput.getPlayerInput(scene);
        playerInput = userInput.getInputList();

        if(playerInput.contains("UP")) {
            setPosY(getPosY() - getSpeedY() * MainGame.elapsedTime);
            if(getPosY() <= edgePadding) {
                setPosY(edgePadding);
            }

        }

        if(playerInput.contains("DOWN")) {
            setPosY(getPosY() + getSpeedY() * MainGame.elapsedTime);
            if(getPosY() + getHeight() >= MainGame.windowHeight - edgePadding) {
                setPosY(MainGame.windowHeight - getHeight() - edgePadding);
            }
        }

    }



}
