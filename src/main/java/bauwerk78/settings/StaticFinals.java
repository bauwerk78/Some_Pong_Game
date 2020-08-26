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
    double ballOnePlayerSpeed = 150;
    double ballTwoPlayerSpeed = 200;
    double ballStartPosX = (GameOptions.windowWidth / 2d);
    double ballStartPosY = (GameOptions.windowHeight / 2d);

    //Paddle settings.
    double paddleSpeed = 250;
    double paddleWidth = 20;
    double paddleHeight = 80;
    int paddleEdgePadding = 5;


}
