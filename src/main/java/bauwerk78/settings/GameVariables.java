package bauwerk78.settings;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameVariables {

    //Paddle
    //public static int paddleEdgePadding = (5 / GameOptions.windowHeight) * GameOptions.windowHeight ;

    //Game score settings.
    public static int scoreFontSize = (int)(GameOptions.windowHeight * 0.034);
    public static Font scoreFont = Font.font("Verdana", FontWeight.BOLD, scoreFontSize);

    //Ball settings.
    public static double ballSpeedMultiplier = 1.05;
}
