package bauwerk78.model;

import bauwerk78.settings.GameOptions;
import bauwerk78.settings.StaticFinals;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class GameMenu {

    private final ImageView[] imageViewsSelection = new ImageView[4];

    private Scene sceneMenu;

    private final UserInput userInput = new UserInput();

    private final Glow effectGlow = new Glow();
    private final Glow effectGlowMenu = new Glow();
    private double glowLevel;
    private boolean glowLevelGoingUp = true;

    private int selectedItem = 0;
    private int numberOfPlayers = 1;

    private boolean startGame;


    public GameMenu() {
        init();
    }

    private void init() {
        Image imageMenu = new Image("file:Images/MainMenu/main_menu.png");
        Image imageOnePlayer = new Image("file:Images/MainMenu/one_player.png");
        Image imageTwoPlayer = new Image("file:Images/MainMenu/two_players.png");
        Image imageOptions = new Image("file:Images/MainMenu/options.png");
        Image imageExitGame = new Image("file:Images/MainMenu/exit_game.png");

        ImageView imageViewMenu = new ImageView();
        imageViewMenu.setImage(imageMenu);

        imageViewsSelection[0] = new ImageView(imageOnePlayer);
        imageViewsSelection[1] = new ImageView(imageTwoPlayer);
        imageViewsSelection[2] = new ImageView(imageOptions);
        imageViewsSelection[3] = new ImageView(imageExitGame);

        VBox verticalBox = new VBox(imageViewMenu, imageViewsSelection[0], imageViewsSelection[1], imageViewsSelection[2], imageViewsSelection[3]);
        verticalBox.setPrefSize(150, 125);
        verticalBox.relocate(325, 125);
        verticalBox.setVisible(true);

        effectGlow.setLevel(1);
        imageViewMenu.setEffect(effectGlowMenu);
        sceneMenu = new Scene(verticalBox, GameOptions.windowWidth, GameOptions.windowHeight);
        sceneMenu.setFill(Color.BLACK);
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
    }

    public void updateMenu() {
        userInput.getPlayerInput(sceneMenu);
        List<String> menuInput = userInput.getInputList();

        pulsingGlowMenu();
        effectGlowMenu.setLevel(glowLevel);

        if (menuInput.contains(StaticFinals.keyboardUp)) {
            selectedItem--;
            menuInput.remove(StaticFinals.keyboardUp);
            if(selectedItem < 0) {
                selectedItem = 0;
            }
        }

        if (menuInput.contains(StaticFinals.keyboardDown)) {
            selectedItem++;
            menuInput.remove(StaticFinals.keyboardDown);
            if(selectedItem > imageViewsSelection.length - 1) {
                selectedItem = imageViewsSelection.length - 1;
            }
        }

        //Remove glow effect and add to the correct selection.
        for (int i = 0; i < imageViewsSelection.length; i++) {
            if (i != selectedItem) {
                imageViewsSelection[i].setEffect(null);
            } else {
                if (imageViewsSelection[i].getEffect() == null) {
                    imageViewsSelection[i].setEffect(effectGlow);
                }
            }
        }

        if(menuInput.contains(StaticFinals.keyboardSelect)) {
            if(selectedItem == 0) {
                numberOfPlayers = 1;
                menuInput.remove(StaticFinals.keyboardSelect);
                startGame = true;
            }

            if(selectedItem == 1) {
                numberOfPlayers = 2;
                menuInput.remove(StaticFinals.keyboardSelect);
                startGame = true;
            }
            //TODO
            if(selectedItem == 2) {
                System.out.println("Options menu, not integrated yet.");
                menuInput.remove(StaticFinals.keyboardSelect);
            }

            if(selectedItem == 3) {
                System.exit(0);
            }
        }


    }


    public Scene getSceneMenu() {
        return sceneMenu;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isStartGame() {
        return startGame;
    }


}
