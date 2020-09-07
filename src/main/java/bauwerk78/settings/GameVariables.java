package bauwerk78.settings;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameVariables {

    /*Certain game variables collected in one file for easy editing and adjusting*/

    //Game score settings.
    public static int scoreFontSize = (int)(GameOptions.windowHeight * 0.034);
    public static Font scoreFont = Font.font("Verdana", FontWeight.BOLD, scoreFontSize);

    //Game menu settings.
    public static int menuSelectionFontSize = 19;
    //public static Font menuSelectionFont = Font.font("SansSerif", FontWeight.BOLD, menuSelectionFontSize);
    public static Font menuSelectionFont = Font.font("SansSerif", menuSelectionFontSize);
    public static int menuHeaderFontSize = 38;
    public static Font menuHeaderFont = Font.font("SansSerif", FontWeight.BOLD, menuHeaderFontSize);

    //Ball settings.
    public static double ballSpeedMultiplier = 1.05;

    //Paddle settings.
    public static double paddlePlayerOneHeight;
    public static double paddlePlayerOneWidth;
    public static double paddlePlayerOneSpeed;

    public static double paddlePlayerTwoHeight;
    public static double paddlePlayerTwoWidth;
    public static double paddlePlayerTwoSpeed;
}
