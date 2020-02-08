package bauwerk78.implementer;


import bauwerk78.model.Ball;
import bauwerk78.model.ComputerOpponent;
import bauwerk78.model.Player;
import bauwerk78.tools.Delayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class MainGame extends Application {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static Long startNanoTime = System.nanoTime();
    public static double elapsedTime;

    private Group root = new Group();
    private Scene scene = new Scene(root, windowWidth, windowHeight);
    private Delayer collisionDelayer = new Delayer();
    private Delayer resetDelayer = new Delayer();
    private GraphicsContext gc;
    private Canvas canvas;
    private Ball ball;
    private Player player1;
    private ComputerOpponent computerOpponent;
    private int fontSize = 20;
    private Font scoreFont = Font.font("Verdana", FontWeight.BOLD, fontSize);

    private boolean checkCollision = true;
    private boolean resetTimer = true;
    private int scoreP1;
    private int scoreP2;
    private String scoreP1Text;
    private String scoreP2Text;
    private double speedMultiplier = 1.05;



    public MainGame() {
        init();
    }

    public void init() {
        initGraphics();
        ball = new Ball(windowWidth / 2d, windowHeight / 2d);
        player1 = new Player(20, (windowHeight / 2d));
        player1.setPosY(player1.getPosY() - player1.getHeight() / 2);
        computerOpponent = new ComputerOpponent(windowWidth - 20, (windowHeight / 2d));
        computerOpponent.setPosY(computerOpponent.getPosY() - computerOpponent.getHeight() / 2);
        computerOpponent.setPosX(computerOpponent.getPosX() - computerOpponent.getWidth());

    }

    public void showScore() {
        gc.setFont(scoreFont);
        gc.setFill(Color.WHITE);
        scoreP1Text = "P1: " + scoreP1;
        scoreP2Text = "P2: " + scoreP2;
        gc.fillText(scoreP1Text, windowWidth/2d - (windowWidth / 4d), 30);
        gc.fillText(scoreP2Text, windowWidth/2d + (windowWidth / 4d) - (fontSize * scoreP2Text.length()) / 2d, 30);

    }

    public void update() {


        if (checkCollision) {
            if (collisionDetection(ball.collidingBox(), player1.collidingBox()) || collisionDetection(ball.collidingBox(), computerOpponent.collidingBox())) {
                ball.setGoingRight(!ball.isGoingRight());
                ball.setSpeedX(ball.getSpeedX() * speedMultiplier);
                checkCollision = false;
            }
        }
        //Make sure the intersects detection does not happen several times in a row as in ball gets stuck to paddle.
        if(!checkCollision) {
            checkCollision = collisionDelayer.delayTimer(0.5);
        }

        if(ball.isBallOutOfBounds() && resetTimer) {
            resetTimer = false;
            if(ball.isGoingRight()) {
                scoreP1++;
            } else {
                scoreP2++;
            }
        }

        if(!resetTimer) {
            resetTimer = resetDelayer.delayTimer(2);
            if(resetTimer) {
                ball.reset();
            }
        }


    }

    public void render() {
        update();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, windowWidth, windowHeight);
        if(!ball.isBallOutOfBounds()) {
            ball.render(gc);
        }

        player1.update(scene);
        player1.render(gc);
        computerOpponent.update(this);
        computerOpponent.render(gc);
        showScore();
    }

    public void mainLoop() {
        render();

    }

    public void initGraphics() {
        canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

    public static void nanoTimer(long currentNanoTime) {
        elapsedTime = (currentNanoTime - startNanoTime.doubleValue()) / 1_000_000_000d;
        startNanoTime = currentNanoTime;
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Some Pong Game");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                nanoTimer(currentNanoTime);
                mainLoop();
            }
        }.start();

        stage.show();


    }

    public static boolean collisionDetection(Rectangle2D object1, Rectangle2D object2) {
        return object1.intersects(object2);
    }

    public double getBallYPosition() {
        return ball.getPosY();
    }

    public double getBallXPosition() {
        return ball.getPosX();
    }

    public double getBallWidth() {
        return ball.getWidth();
    }

    public double getBallHeight() {
        return ball.getHeight();
    }


}//End of class.
