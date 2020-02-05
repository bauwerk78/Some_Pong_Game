package bauwerk78.implementer;


import bauwerk78.model.Ball;
import bauwerk78.model.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static Long startNanoTime = System.nanoTime();
    public static double elapsedTime;



    public MainGame() {
        init();
    }

    public void init() {
        initGraphics();
        ball = new Ball(windowWidth / 2d, windowHeight / 2d);
        player1 = new Player(20, (windowHeight / 2d));

    }

    public void render() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,windowWidth, windowHeight);
        ball.render(gc);
        player1.render(gc);
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

}
