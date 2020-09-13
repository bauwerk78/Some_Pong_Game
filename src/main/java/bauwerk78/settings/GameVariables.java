package bauwerk78.settings;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
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
    public static final String[] mainMenu = {"MAIN MENU", "1 Player", "2 Player", "Options", "Play Via Network", "Exit Game"};

    //Options menu.
    public static final String[] optionsMenu = {"OPTIONS", "Resolution", "Player Name", "Controls", "Go Back"};

    //Resolution menu.
    public static final String[] resolutionMenu = {"RESOLUTION", "1024x768", "800x600", "600x400", "Go Back"};

    public static Text[] getTextArray(String[] menuName) {
        Text[] textArray = new Text[menuName.length];
        for (int i = 0; i < menuName.length; i++) {
            if(i == 0) {
                textArray[i] = new Text(menuName[i]);
                textArray[i].setId("menu-header");
            } else {
                textArray[i] = new Text(menuName[i]);
            }
        }
        return textArray;
    }

    public static VBox getVbox(String[] menuName) {
        VBox vBox = new VBox(getTextArray(menuName));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setId("verticalBoxArray");
        vBox.getStylesheets().add("file:CSS/menu.css");
        return vBox;
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
