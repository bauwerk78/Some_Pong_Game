package bauwerk78.implementer;


import bauwerk78.model.*;
import bauwerk78.scenes.GameMenu;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;
import bauwerk78.settings.StaticFinals;
import bauwerk78.tools.CollidingBox;
import bauwerk78.tools.CollisionDetection;
import bauwerk78.tools.Delayer;
import bauwerk78.tools.ElapsedTimeTimer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
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

    private void initGraphics() {
        Canvas canvas = new Canvas(GameOptions.windowWidth, GameOptions.windowHeight);
        gc = canvas.getGraphicsContext2D();

        GameBoard gameBoard = new GameBoard();
        rootGroup.getChildren().addAll(gameBoard.getPaneBackground(), canvas);
    }

    public void init() {
        initGraphics();
        player1 = new Player(1);
    }

    //TODO implement.
    private void resetGamePlay() {
        if(ball != null) {
            ball = null;
        }
        if(player1 != null) {
            player1 = null;
        }
        if(computerOpponent != null) {
            computerOpponent = null;
        }
        if(player2 != null) {
            player2 = null;
        }
        score.resetScore();
        player1 = new Player(1);
    }

    public void updateGamePlay() {
        userInput.getPlayerInput(sceneMainGame);

        if (collisionCheck) {
            //TODO collision check not working 100% regarding top bottom and edge.
            Rectangle2D ballCollidingBox = new CollidingBox().collidingBox(ball.getPosX(), ball.getPosY(), ball.getWidth(), ball.getHeight());

            Rectangle2D player1SideCollidingBox = new CollidingBox().collidingBox(player1.getPosX(), player1.getPosY() + StaticFinals.paddleTopBottomCollisionBox, player1.getWidth(), player1.getHeight() - StaticFinals.paddleTopBottomCollisionBox * 2);
            Rectangle2D player1UpperCollidingBox = new CollidingBox().collidingBox(player1.getPosX(), player1.getPosY(), player1.getWidth(), StaticFinals.paddleTopBottomCollisionBox);
            Rectangle2D player1LowerCollidingBox = new CollidingBox().collidingBox(player1.getPosX(), player1.getPosY() + player1.getHeight() - StaticFinals.paddleTopBottomCollisionBox, player1.getWidth(), StaticFinals.paddleTopBottomCollisionBox);

            Rectangle2D opponentSideCollidingBox;
            Rectangle2D opponentUpperCollidingBox;
            Rectangle2D opponentLowerCollidingBox;

            if (gameMenu.getNumberOfPlayers() == 1) {
                opponentSideCollidingBox = new CollidingBox().collidingBox(computerOpponent.getPosX(), computerOpponent.getPosY() + StaticFinals.paddleTopBottomCollisionBox, computerOpponent.getWidth(), computerOpponent.getHeight() - StaticFinals.paddleTopBottomCollisionBox * 2);
                opponentUpperCollidingBox = new CollidingBox().collidingBox(computerOpponent.getPosX(), computerOpponent.getPosY(), computerOpponent.getWidth(), StaticFinals.paddleTopBottomCollisionBox);
                opponentLowerCollidingBox = new CollidingBox().collidingBox(computerOpponent.getPosX(), computerOpponent.getPosY() + computerOpponent.getHeight() - StaticFinals.paddleTopBottomCollisionBox, computerOpponent.getWidth(), StaticFinals.paddleTopBottomCollisionBox);

            } else {
                opponentSideCollidingBox = new CollidingBox().collidingBox(player2.getPosX(), player2.getPosY() + StaticFinals.paddleTopBottomCollisionBox, player2.getWidth(), player2.getHeight() - StaticFinals.paddleTopBottomCollisionBox * 2);
                opponentUpperCollidingBox = new CollidingBox().collidingBox(player2.getPosX(), player2.getPosY(), player2.getWidth(), StaticFinals.paddleTopBottomCollisionBox);
                opponentLowerCollidingBox = new CollidingBox().collidingBox(player2.getPosX(), player2.getPosY() + player2.getHeight() - StaticFinals.paddleTopBottomCollisionBox, player2.getWidth(), StaticFinals.paddleTopBottomCollisionBox);
            }

            //Check front side of paddle collisions.
            if (collisionDetection.isCollided(ballCollidingBox, player1SideCollidingBox) ||
                    collisionDetection.isCollided(ballCollidingBox, opponentSideCollidingBox)) {

                ball.setGoingRight(!ball.isGoingRight());
                ball.setSpeedX(ball.getSpeedX() * GameVariables.ballSpeedMultiplier);
                collisionCheck = false;
            } else
                //Check top of paddle collisions.
                if (collisionDetection.isCollided(ballCollidingBox, player1UpperCollidingBox) ||
                        collisionDetection.isCollided(ballCollidingBox, opponentUpperCollidingBox)) {
                    if (!ball.isGoingUp()) {
                        ball.setGoingUp(true);
                    }
                    collisionCheck = false;

                } else
                    //Check bottom of paddle collisions.
                    if (collisionDetection.isCollided(ballCollidingBox, player1LowerCollidingBox) ||
                            collisionDetection.isCollided(ballCollidingBox, opponentLowerCollidingBox)) {
                        if (ball.isGoingUp()) {
                            ball.setGoingUp(false);
                        }
                        collisionCheck = false;
                    }
        }
        //Makes sure the collision detection does not happen several times in a row as in ball gets stuck to paddle.
        if (!collisionCheck) {
            collisionCheck = delayerPaddleCollision.delayTimer(0.2);
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
        updateGamePlay();

        gc.clearRect(0, 0, GameOptions.windowWidth, GameOptions.windowHeight);

        if (!ball.isBallOutOfBounds()) {
            ball.render();
        }

        player1.update();
        player1.render();

        if (gameMenu.getNumberOfPlayers() == 1) {
            computerOpponent.update(this);
            computerOpponent.render();
        } else {
            player2.update();
            player2.render();
        }

        score.showScore();
    }

    public void sceneControl() {
        //TODO include game pause for games played locally.
        //If not in menu, start the game.
        if (gameMenu.isStartGame()) {
            if (!stage.getScene().equals(sceneMainGame)) {
                stage.setScene(sceneMainGame);
            }
            if (gameMenu.getNumberOfPlayers() == 1 && computerOpponent == null) {
                computerOpponent = new ComputerOpponent();
                if(ball == null) {
                    ball = new Ball(1);
                }
            }
            if (gameMenu.getNumberOfPlayers() != 1 && player2 == null) {
                player2 = new Player(2);
                if(ball == null) {
                    ball = new Ball(2);
                }
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
