package bauwerk78.model;

import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;

import static bauwerk78.settings.GameVariables.paddleTopBottomPadding;
import static bauwerk78.tools.ElapsedTimeTimer.elapsedTime;

public class Player extends Paddle {

    private final int playerNumber;
    private String keyboardUp;
    private String keyboardDown;
    //TODO implement action-key.
    private String keyboardActionKey;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        init();
    }

    private void init() {
        setWidth(GameVariables.paddleWidth);
        setHeight(GameVariables.paddleHeight);
        setPosY((GameOptions.windowHeight / 2d) - (getHeight() / 2));
        setSpeedY(GameVariables.paddleSpeedPlayer);

        if (playerNumber == 1) {
            setPosX(GameVariables.paddleDistanceToWindowSide);
            keyboardUp = GameOptions.keyboardOneUp;
            keyboardDown = GameOptions.keyboardOneDown;
        } else {
            setPosX(GameOptions.windowWidth - (GameVariables.paddleDistanceToWindowSide + GameVariables.paddleWidth));
            keyboardUp = GameOptions.keyboardTwoUp;
            keyboardDown = GameOptions.keyboardTwoDown;
        }
    }


    public void update() {

        if (UserInput.input.contains(keyboardUp)) {
            setPosY(getPosY() - getSpeedY() * elapsedTime);
            if (getPosY() <= paddleTopBottomPadding) {
                setPosY(paddleTopBottomPadding);
            }

        }

        if (UserInput.input.contains(keyboardDown)) {
            setPosY(getPosY() + getSpeedY() * elapsedTime);
            if (getPosY() + getHeight() >= GameOptions.windowHeight - paddleTopBottomPadding) {
                setPosY(GameOptions.windowHeight - getHeight() - paddleTopBottomPadding);
            }
        }

    }

}
