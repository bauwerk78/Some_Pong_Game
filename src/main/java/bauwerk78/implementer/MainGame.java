package bauwerk78.implementer;


import bauwerk78.model.Ball;
import bauwerk78.model.ComputerOpponent;
import bauwerk78.model.Player;
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

    Group root = new Group();
    Scene scene = new Scene(root, windowWidth, windowHeight);
    private GraphicsContext gc;
    private Canvas canvas;
    private Ball ball;
    private Player player1;
    private ComputerOpponent computerOpponent;
    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static Long startNanoTime = System.nanoTime();
    public static double elapsedTime;

    private int scoreP1;
    private int scoreP2;



    public MainGame() {
        init();
    }

    public void init() {
        initGraphics();
        ball = new Ball(windowWidth / 2d, windowHeight / 2d);
        player1 = new Player(20, (windowHeight / 2d));
        computerOpponent = new ComputerOpponent(windowWidth - 20, (windowHeight / 2d));
        computerOpponent.setPosX(computerOpponent.getPosX() - computerOpponent.getWidth());

    }

    public void update() {
        if(collisionDetection(ball.collidingBox(), player1.collidingBox()) || collisionDetection(ball.collidingBox(), computerOpponent.collidingBox())) {
            ball.setGoingRight(!ball.isGoingRight());
        }
    }

    public void render() {
        update();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,windowWidth, windowHeight);
        ball.render(gc);
        player1.update(scene);
        player1.render(gc);
        computerOpponent.update(this);
        computerOpponent.render(gc);
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
