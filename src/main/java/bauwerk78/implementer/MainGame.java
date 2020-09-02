package bauwerk78.implementer;


import bauwerk78.model.*;
import bauwerk78.scenes.GameMenu;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;
import bauwerk78.settings.StaticFinals;
import bauwerk78.tools.CollisionDetection;
import bauwerk78.tools.Delayer;
import bauwerk78.tools.ElapsedTimeTimer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainGame extends Application {

    public static Stage stage;
    public static Group rootGroup = new Group();
    public static GraphicsContext gc;

    private final Scene sceneMainGame = new Scene(rootGroup, GameOptions.windowWidth, GameOptions.windowHeight);

    private final Delayer delayerPaddleCollision = new Delayer();
    private final Delayer delayerResetOfRound = new Delayer();
    private final CollisionDetection collisionDetection = new CollisionDetection();
    private final UserInput userInput = new UserInput();
    private final GameMenu gameMenu = new GameMenu();
    private final Score score = new Score();

    private Ball ball;
    private Player player1;
    private Player player2;

    private ComputerOpponent computerOpponent;

    private boolean collisionCheck = true;
    private boolean roundResetTimer = true;


    public MainGame() {
        /*init(); triggers by default by the launcher*/
    }

    public void initGraphics() {
        Canvas canvas = new Canvas(GameOptions.windowWidth, GameOptions.windowHeight);
        gc = canvas.getGraphicsContext2D();

        GameBoard gameBoard = new GameBoard();
        rootGroup.getChildren().addAll(gameBoard.getPaneBackground(), canvas);
    }

    public void init() {
        initGraphics();
        ball = new Ball(GameOptions.windowWidth / 2d, GameOptions.windowHeight / 2d);
        player1 = new Player(20, (GameOptions.windowHeight / 2d), 1);
        player1.setPosY(player1.getPosY() - player1.getHeight() / 2);
        player2 = new Player(GameOptions.windowWidth - 20, (GameOptions.windowHeight / 2d), 2);
        player2.setPosY(player2.getPosY() - player2.getHeight() / 2);
        player2.setPosX(player2.getPosX() - player2.getWidth());
        computerOpponent = new ComputerOpponent(GameOptions.windowWidth - 20, (GameOptions.windowHeight / 2d));
        computerOpponent.setPosX(computerOpponent.getPosX() - computerOpponent.getWidth());
        computerOpponent.setPosY(computerOpponent.getPosY() - computerOpponent.getHeight() / 2);
    }

    public void updateGameRound() {
        userInput.getPlayerInput(sceneMainGame);

        if (collisionCheck) {
            if (collisionDetection.collisionDetection(ball.collidingBox(), player1.collidingBox()) || collisionDetection.collisionDetection(ball.collidingBox(), computerOpponent.collidingBox())
                    || collisionDetection.collisionDetection(ball.collidingBox(), player2.collidingBox())) {

                ball.setGoingRight(!ball.isGoingRight());
                ball.setSpeedX(ball.getSpeedX() * GameVariables.ballSpeedMultiplier);
                collisionCheck = false;
            }
        }
        //Makes sure the collision detection does not happen several times in a row as in ball gets stuck to paddle.
        if (!collisionCheck) {
            collisionCheck = delayerPaddleCollision.delayTimer(0.5);
        }

        if (ball.isBallOutOfBounds() && roundResetTimer) {
            roundResetTimer = false;
            if (ball.isGoingRight()) {
                score.setScoreP1(score.getScoreP1() + 1);
            } else {
                score.setScoreP2(score.getScoreP2() + 1);
            }
        }

        if (!roundResetTimer) {
            roundResetTimer = delayerResetOfRound.delayTimer(2);
            if (roundResetTimer) {
                ball.reset();
            }
        }
    }

    public void renderGamePlay() {
        updateGameRound();
        gc.clearRect(0, 0, GameOptions.windowWidth, GameOptions.windowHeight);

        if (!ball.isBallOutOfBounds()) {
            ball.render(gc);
        }

        player1.update();
        player1.render(gc);

        if (gameMenu.getNumberOfPlayers() == 1) {
            computerOpponent.update(this);
            computerOpponent.render(gc);
        } else {
            player2.update();
            player2.render(gc);
        }

        score.showScore();
    }

    public void sceneControl() {
        //If not in menu, start the game.
        if (gameMenu.isStartGame()) {
            if (!stage.getScene().equals(sceneMainGame)) {
                stage.setScene(sceneMainGame);
            }
            renderGamePlay();
        }
        //If in game menu selection.
        if (!gameMenu.isStartGame()) {
            if (!stage.getScene().equals(gameMenu.getSceneMenu())) {
                stage.setScene(gameMenu.getSceneMenu());
            }
            gameMenu.updateMenu();
        }

    }


    @Override
    public void start(Stage stage) {
        MainGame.stage = stage;
        MainGame.stage.setTitle(StaticFinals.windowGameTitle);

        MainGame.stage.setScene(gameMenu.getSceneMenu());

        MainGame.stage.initStyle(StageStyle.DECORATED);
        MainGame.stage.setResizable(false);
        MainGame.stage.sizeToScene();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                ElapsedTimeTimer.nanoTimer(currentNanoTime);
                sceneControl();
            }
        }.start();

        MainGame.stage.show();

    }

    public double getBallYPosition() {
        return ball.getPosY();
    }

    public double getBallHeight() {
        return ball.getHeight();
    }


}//End of class.
