package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;

public class GameMenu {

    //private Group menuGroup = new Group();

    private Image menuImage;
    private Image option1;
    private Image option2;
    private Image option3;

    private ImageView menuImageView;
    private ImageView[] imageViews = new ImageView[3];

    private VBox verticalBox;
    private Scene menuScene;
    //private Pane pane;

    private UserInput userInput = new UserInput();
    private List<String> menuInput = new ArrayList<>();

    private Glow glowEffect = new Glow();

    private int selectedItem = 0;


    public GameMenu() {
        init();
    }

    private void init() {
        menuImage = new Image("file:Images/MainMenu/main_menu.png");
        option1 = new Image("file:Images/MainMenu/one_player.png");
        option2 = new Image("file:Images/MainMenu/two_players.png");
        option3 = new Image("file:Images/MainMenu/exit_game.png");

        menuImageView = new ImageView();
        menuImageView.setImage(menuImage);
        menuImageView.setVisible(true);
        menuImageView.setFitWidth(menuImage.getWidth());
        menuImageView.setFitHeight(menuImage.getHeight());
        menuImageView.relocate(200, 200);

        imageViews[0] = new ImageView(option1);
        imageViews[1] = new ImageView(option2);
        imageViews[2] = new ImageView(option3);

        verticalBox = new VBox(menuImageView, imageViews[0], imageViews[1], imageViews[2]);
        //verticalBox = new VBox(menuImageView);
        verticalBox.setPrefSize(150, 125);
        verticalBox.relocate(325, 225);
        verticalBox.setVisible(true);
        System.out.println(verticalBox.getParent() + verticalBox.getChildren().toString());
        glowEffect.setLevel(1);
        menuScene = new Scene(verticalBox, MainGame.windowWidth, MainGame.windowHeight);

    }

    public void updateMenu(Scene scene, GraphicsContext gc) {
/*        userInput.getPlayerInput(scene);
        menuInput = userInput.getInputList();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, MainGame.windowWidth, MainGame.windowHeight);*/


        if (menuInput.contains("UP")) {
            selectedItem--;
            menuInput.remove("UP");
            if(selectedItem < 0) {
                selectedItem = 0;
            }
        }

        if (menuInput.contains("DOWN")) {
            selectedItem++;
            menuInput.remove("DOWN");
            if(selectedItem > 2) {
                selectedItem = 2;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (i != selectedItem) {
                imageViews[i].setEffect(null);
            } else {
                if (imageViews[i].getEffect() == null) {
                    imageViews[i].setEffect(glowEffect);
                }
            }
        }

        System.out.println(selectedItem);

/*        if(selectedItem == 0 && imageView1.getEffect() == null) {
            imageView1.setEffect(glowEffect);
        }
        if(selectedItem == 1 && imageView2.getEffect() == null) {
            imageView2.setEffect(glowEffect);
        }
        if(selectedItem == 2 && imageView3.getEffect() == null) {
            imageView3.setEffect(glowEffect);
        }*/


    }

    public VBox getVerticalBox() {
        return verticalBox;
    }
/*
    public Pane getPane() {
        return pane;
    }*/

    public ImageView getMenuImageView() {
        return menuImageView;
    }

}
