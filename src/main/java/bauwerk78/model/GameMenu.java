package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


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

    private UserInput userInput = new UserInput();
    private List<String> menuInput = new ArrayList<>();

    private Glow glowEffect = new Glow();
    private Bloom bloomEffect = new Bloom();
    private double bloomThreshold;
    private boolean bloomThresholdGoingUp = true;

    private Glow glowEffectMenu = new Glow();
    private double glowLevel;
    private boolean glowLevelGoingUp = true;

    private int selectedItem = 0;
    private int numberOfPlayers = 1;

    private boolean startGame;


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

        imageViews[0] = new ImageView(option1);
        imageViews[1] = new ImageView(option2);
        imageViews[2] = new ImageView(option3);

        verticalBox = new VBox(menuImageView, imageViews[0], imageViews[1], imageViews[2]);
        verticalBox.setPrefSize(150, 125);
        verticalBox.relocate(325, 125);
        verticalBox.setVisible(true);

        glowEffect.setLevel(1);
        bloomEffect.setThreshold(bloomThreshold);
        menuImageView.setEffect(glowEffectMenu);
        menuScene = new Scene(verticalBox, MainGame.windowWidth, MainGame.windowHeight);
        menuScene.setFill(Color.BLACK);
    }

    public void pulsingBloomMenu() {
        if(bloomThresholdGoingUp) {
            bloomThreshold += 0.01;
            if(bloomThreshold >= 1) {
                bloomThreshold = 1;
                bloomThresholdGoingUp = false;
            }
        } else {
            bloomThreshold -= 0.01;
            if(bloomThreshold <= 0) {
                bloomThreshold = 0;
                bloomThresholdGoingUp = true;
            }
        }
        System.out.println(bloomThreshold);
    }

    public void pulsingGlowMenu() {
        if(glowLevelGoingUp) {
            glowLevel += 0.01;
            if(glowLevel >= 1) {
                glowLevel = 1;
                glowLevelGoingUp = false;
            }
        } else {
            glowLevel -= 0.01;
            if(glowLevel <= 0) {
                glowLevel = 0;
                glowLevelGoingUp = true;
            }
        }
        //System.out.println(glowLevel);
    }

    public void updateMenu(GraphicsContext gc) {
        userInput.getPlayerInput(menuScene);
        menuInput = userInput.getInputList();

/*        pulsingBloomMenu();
        bloomEffect.setThreshold(bloomThreshold);*/
        pulsingGlowMenu();
        glowEffectMenu.setLevel(glowLevel);

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

        //Remove glow effect and add to the correct selection.
        for (int i = 0; i < 3; i++) {
            if (i != selectedItem) {
                imageViews[i].setEffect(null);
            } else {
                if (imageViews[i].getEffect() == null) {
                    imageViews[i].setEffect(glowEffect);
                }
            }
        }

        if(menuInput.contains("ENTER")) {
            if(selectedItem == 0) {
                numberOfPlayers = 1;
                menuInput.remove("ENTER");
                startGame = true;
            }

            if(selectedItem == 1) {
                numberOfPlayers = 2;
                menuInput.remove("ENTER");
                startGame = true;
            }

            if(selectedItem == 2) {
                System.exit(0);
            }
        }


    }



    public VBox getVerticalBox() {
        return verticalBox;
    }

    public Scene getMenuScene() {
        return menuScene;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isStartGame() {
        return startGame;
    }

    /*
    public Pane getPane() {
        return pane;
    }*/

    public ImageView getMenuImageView() {
        return menuImageView;
    }

}
