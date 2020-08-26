package bauwerk78.implementer;


import bauwerk78.model.*;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;
import bauwerk78.settings.StaticFinals;
import bauwerk78.tools.Delayer;
import bauwerk78.tools.ElapsedTimeTimer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainGame extends Application {

    public static Stage stage;
    public static Group rootGroup = new Group();
    public static GraphicsContext gc;

    private final Scene gameScene = new Scene(rootGroup, GameOptions.windowWidth, GameOptions.windowHeight);
    private final Delayer collisionDelayer = new Delayer();
    private final Delayer resetDelayer = new Delayer();

    private final Score score = new Score();
    private Ball ball;
    private Player player1;
    private Player player2;
    private final UserInput userInput = new UserInput();
    private ComputerOpponent computerOpponent;
    private final GameMenu gameMenu = new GameMenu();

    private boolean checkCollision = true;
    private boolean resetTimer = true;


    public MainGame() {
        init();
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

    public void update() {
        userInput.getPlayerInput(gameScene);

        if (checkCollision) {
            if (collisionDetection(ball.collidingBox(), player1.collidingBox()) || collisionDetection(ball.collidingBox(), computerOpponent.collidingBox())
                    || collisionDetection(ball.collidingBox(), player2.collidingBox())) {

                ball.setGoingRight(!ball.isGoingRight());
                ball.setSpeedX(ball.getSpeedX() * GameVariables.ballSpeedMultiplier);
                checkCollision = false;
            }
        }
        //Makes sure the intersects detection does not happen several times in a row as in ball gets stuck to paddle.
        if (!checkCollision) {
            checkCollision = collisionDelayer.delayTimer(0.5);
        }

        if (ball.isBallOutOfBounds() && resetTimer) {
            resetTimer = false;
            if (ball.isGoingRight()) {
                score.setScoreP1(score.getScoreP1() + 1);
            } else {
                score.setScoreP2(score.getScoreP2() + 1);
            }
        }

        if (!resetTimer) {
            resetTimer = resetDelayer.delayTimer(2);
            if (resetTimer) {
                ball.reset();
            }
        }
    }

    public void render() {
        update();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameOptions.windowWidth, GameOptions.windowHeight);
        if (!ball.isBallOutOfBounds()) {
            ball.render(gc);
        }
        gc.strokeLine(GameOptions.windowWidth / 2d, 0, GameOptions.windowWidth / 2d, GameOptions.windowHeight);
        gc.setStroke(Color.WHITE);
        gc.stroke();
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

    public void mainLoop() {
        //If not in menu, start the game.
        if (gameMenu.isStartGame()) {
            if (!stage.getScene().equals(gameScene)) {
                stage.setScene(gameScene);
            }
            render();
        }
        //If in game menu selection.
        if (!gameMenu.isStartGame()) {
            if (!stage.getScene().equals(gameMenu.getSceneMenu())) {
                stage.setScene(gameMenu.getSceneMenu());
            }
            gameMenu.updateMenu();
        }

    }

    public void initGraphics() {
        Canvas canvas = new Canvas(GameOptions.windowWidth, GameOptions.windowHeight);
        gc = canvas.getGraphicsContext2D();
        rootGroup.getChildren().addAll(canvas);
    }

    @Override
    public void start(Stage stage) {
        MainGame.stage = stage;
        MainGame.stage.setTitle(StaticFinals.windowGameTitle);

        MainGame.stage.setScene(gameScene);

        MainGame.stage.setResizable(false);
        MainGame.stage.sizeToScene();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                ElapsedTimeTimer.nanoTimer(currentNanoTime);
                mainLoop();
            }
        }.start();

        MainGame.stage.show();

    }

    public static boolean collisionDetection(Rectangle2D object1, Rectangle2D object2) {
        return object1.intersects(object2);
    }

    public double getBallYPosition() {
        return ball.getPosY();
    }

    public double getBallHeight() {
        return ball.getHeight();
    }


}//End of class.
