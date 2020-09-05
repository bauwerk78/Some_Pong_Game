package bauwerk78.settings;

public interface StaticFinals {

    /*static finals that aren't supposed to be adjusted by the user.*/

    //Window related settings.
    String windowGameTitle = "Some pong game";

    //Game control settings.
    /* Keyboard settings.*/
    //Options menu.
    //TODO not implemented yet.
    public static String keyboardUp = "UP";
    public static String keyboardDown = "DOWN";
    public static String keyboardLeft = "LEFT";
    public static String keyboardRight = "RIGHT";
    public static String keyboardSelect = "ENTER";

    //Ball settings.
    double ballOnePlayerSpeed = 150; //Starting ball speed for a one player game.
    double ballTwoPlayerSpeed = 200; //Starting ball speed for a two player game.
    double ballStartPosX = (GameOptions.windowWidth / 2d);
    double ballStartPosY = (GameOptions.windowHeight / 2d);
    double ballWidth = 15;
    double ballHeight = 15;

    //Paddle settings.
    double paddleSpeedPlayer = 250;
    double paddleSpeedComputer = 150;
    double paddleWidth = 20;
    double paddleHeight = 80;
    double paddleDistanceToWindowSide = 20;
    double paddleTopBottomCollisionBox = 2; //Height of the top and bottom collision box.
    int paddleTopBottomPadding = 5; //Top and bottom of window padding.

    //Score settings.
    int scoreToEndGame = 3;
}
