package bauwerk78.settings;

public interface Statics {

    //Window related settings.
    String windowGameTitle = "Some pong game";

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
