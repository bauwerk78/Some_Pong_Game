package bauwerk78.model;

import bauwerk78.settings.GameOptions;

import static bauwerk78.settings.StaticFinals.paddleEdgePadding;
import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;

public class Player extends Paddle {

    private final int playerNumber;
    private String keyboardUp;
    private String keyboardDown;
    //TODO implement action-key.
    private String keyboardActionKey;

    public Player(double posX, double posY, int playerNumber) {
        super(posX, posY);
        this.playerNumber = playerNumber;
        init();
    }

    private void init() {
        if(playerNumber == 1) {
            keyboardUp = GameOptions.keyboardOneUp;
            keyboardDown = GameOptions.keyboardOneDown;
        } else {
            keyboardUp = GameOptions.keyboardTwoUp;
            keyboardDown = GameOptions.keyboardTwoDown;
        }
    }


    public void update() {

        if(UserInput.input.contains(keyboardUp)) {
            setPosY(getPosY() - getSpeedY() * elapsedTime);
            if(getPosY() <= paddleEdgePadding) {
                setPosY(paddleEdgePadding);
            }

        }

        if(UserInput.input.contains(keyboardDown)) {
            setPosY(getPosY() + getSpeedY() * elapsedTime);
            if(getPosY() + getHeight() >= GameOptions.windowHeight - paddleEdgePadding) {
                setPosY(GameOptions.windowHeight - getHeight() - paddleEdgePadding);
            }
        }

    }



}
