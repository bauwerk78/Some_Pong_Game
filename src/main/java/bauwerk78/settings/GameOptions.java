package bauwerk78.settings;

public class GameOptions {

    /* Variables that can be adjusted via options. */

    //Window related settings.
    public static double windowWidth = 800;
    public static double windowHeight = 600;

    //Game control settings.
    //TODO implement action-keys functionality.
    //Keyboard settings.
    //Player one.
    public static String keyboardOneUp = "W";
    public static String keyboardOneDown = "S";
    public static String keyboardOneActionKey = "D";
    //Player two.
    public static String keyboardTwoUp = "UP";
    public static String keyboardTwoDown = "DOWN";
    public static String keyboardTwoActionKey = "LEFT";

    //Player name.
    public static String playerOneName = "P1";
    public static String playerTwoName = "P2";
    public static String playerComputerName = "Computer";
}
