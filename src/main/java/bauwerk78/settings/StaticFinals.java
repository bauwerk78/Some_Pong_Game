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
    int scoreToEndGame = 3; //Winning score, winner needs to win by two points though so this number can climb higher.

    //Menu settings.
    //Main menu.
    int mainMenuSelections = 5;
    String mainMenuHeader = "MAIN MENU";
    String mainMenuOnePlayer = "1 Player";
    String mainMenuTwoPlayer = "2 Player";
    String mainMenuOptions = "Options";
    String mainMenuPlayViaNetwork = "Play Via Network";
    String mainMenuExitGame = "Exit Game";

    //Options menu.
    int optionsMenuSelections = 4;
    String optionsMenuHeader = "OPTIONS";
    String optionsMenuResolution = "Resolution";
    String optionsMenuPlayerName = "Player Name";
    String optionsMenuControls = "Controls";
    String optionsMenuGoBack = "Go Back";

    //Resolution menu.
    int resolutionMenuSelections = 4;
    String resolutionMenuHeader = "RESOLUTION";
    String resolutionMenuOptionOne = "1024x768";
    String resolutionMenuOptionTwo = "800x600";
    String resolutionMenuOptionThree = "600x400";
    String resolutionMenuGoBack = "Go Back";

    //Player name menu.

}
