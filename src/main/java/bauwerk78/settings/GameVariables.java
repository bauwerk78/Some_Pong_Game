package bauwerk78.settings;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameVariables {
    /*Certain game variables collected in one file for easy editing and adjusting*/

    //Window related settings.
    public static String windowGameTitle = "Some pong game";

    //Game score settings.
    public static final int scoreToEndGame = 3; //Winning score, winner needs to win by two points though so this number can climb higher.
    public static int scoreFontSize = (int)(GameOptions.windowHeight * 0.034);
    public static Font scoreFont = Font.font("Verdana", FontWeight.BOLD, scoreFontSize);

    //Game control settings.
    /* Keyboard settings.*/
    //Options menu.
    //TODO not implemented yet.
    public static String keyboardUp = "UP";
    public static String keyboardDown = "DOWN";
    public static String keyboardLeft = "LEFT";
    public static String keyboardRight = "RIGHT";
    public static String keyboardSelect = "ENTER";

    //Menu settings.
    //Main menu.
    public static final int mainMenuSelections = 5;
    public static final String[] mainMenu = new String[mainMenuSelections];
    public static final String mainMenuHeader = "MAIN MENU";
    public static String mainMenu0 = "1 Player";
    public static String mainMenu1 = "2 Player";
    public static String mainMenu2 = "Options";
    public static String mainMenu3 = "Play Via Network";
    public static String mainMenu4 = "Exit Game";
    //Options menu.
    public static final int optionsMenuSelections = 4;
    public static final String[] optionsMenu = new String[optionsMenuSelections];
    public static final String optionsMenuHeader = "OPTIONS";
    //optionsMenu[0] = "Resolution";
    public static final String optionsMenu0 = "Resolution";
    public static final String optionsMenu1 = "Player Name";
    public static final String optionsMenu2 = "Controls";
    public static final String optionsMenu3 = "Go Back";
    //Resolution menu.
    public static final int resolutionMenuSelections = 4;
    public static final String resolutionMenuHeader = "RESOLUTION";
    //TODO make all these into arrays instead.
    public static final String resolutionMenu0 = "1024x768";
    public static final String resolutionMenu1 = "800x600";
    public static final String resolutionMenu2 = "600x400";
    public static final String resolutionMenu3 = "Go Back";

    //TODO Import method of strings to Text objects.
    public static Text[] getTextSelectionArray(String menuName, int arraySize) {
        Text[] textArray = new Text[arraySize];
        for (int i = 0; i < arraySize; i++) {
            textArray[i] = new Text(menuName + i);
        }
        return textArray;
    }

    //Ball settings.
    public static final double ballOnePlayerSpeed = 150; //Starting ball speed for a one player game.
    public static final double ballTwoPlayerSpeed = 200; //Starting ball speed for a two player game.
    public static final double ballStartPosX = (GameOptions.windowWidth / 2d);
    public static final double ballStartPosY = (GameOptions.windowHeight / 2d);
    public static final double ballWidth = 15;
    public static final double ballHeight = 15;
    public static final double ballSpeedMultiplier = 1.05;

    //Paddle settings.
    public static final double paddleSpeedPlayer = 250;
    public static final double paddleSpeedComputer = 150;
    public static final double paddleWidth = 20;
    public static final double paddleHeight = 80;
    public static final double paddleDistanceToWindowSide = 20;
    public static final double paddleTopBottomCollisionBox = 2; //Height of the top and bottom collision box.
    public static final int paddleTopBottomPadding = 5; //Top and bottom of window padding.
    //TODO maybe. Depending on if i want to manipulate paddle sizes with action keys.
    public static double paddlePlayerOneHeight;
    public static double paddlePlayerOneWidth;
    public static double paddlePlayerOneSpeed;

    public static double paddlePlayerTwoHeight;
    public static double paddlePlayerTwoWidth;
    public static double paddlePlayerTwoSpeed;


}
