package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import static bauwerk78.settings.StaticFinals.*;
import bauwerk78.settings.GameOptions;

public class Player extends Paddle {

    private final int player;
    private String keyboardUp;
    private String keyboardDown;
    //TODO implement action-key.
    private String keyboardActionKey;

    public Player(double posX, double posY, int player) {
        super(posX, posY);
        this.player = player;
        init();
    }

    private void init() {
        if(player == 1) {
            keyboardUp = GameOptions.keyboardOneUp;
            keyboardDown = GameOptions.keyboardOneDown;
        } else {
            keyboardUp = GameOptions.keyboardTwoUp;
            keyboardDown = GameOptions.keyboardTwoDown;
        }
    }


    public void update() {

        if(UserInput.input.contains(keyboardUp)) {
            setPosY(getPosY() - getSpeedY() * MainGame.elapsedTime);
            if(getPosY() <= paddleEdgePadding) {
                setPosY(paddleEdgePadding);
            }

        }

        if(UserInput.input.contains(keyboardDown)) {
            setPosY(getPosY() + getSpeedY() * MainGame.elapsedTime);
            if(getPosY() + getHeight() >= GameOptions.windowHeight - paddleEdgePadding) {
                setPosY(GameOptions.windowHeight - getHeight() - paddleEdgePadding);
            }
        }

    }



}
