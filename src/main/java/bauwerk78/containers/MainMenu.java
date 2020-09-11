package bauwerk78.containers;

import bauwerk78.model.UserInput;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class MainMenu {

    private final Text[] textSelection = new Text[5];

    private Scene sceneMenu;
    private VBox verticalBox;

    private final UserInput userInput = new UserInput();

    private final Glow effectGlow = new Glow();
    private final Glow effectGlowMenu = new Glow();
    private double glowLevel;
    private boolean glowLevelGoingUp = true;

    private int selectedItem = 0;
    private int numberOfPlayers;

    private boolean startGame;


    public MainMenu() {
        init();
    }

    private void init() {

        //Testing Text instead of images.
        Text textMenuHeader = new Text(GameVariables.mainMenuHeader);
        textMenuHeader.setId("menu-header");

        textSelection[0] = new Text(GameVariables.mainMenu0);
        textSelection[1] = new Text(GameVariables.mainMenu1);
        textSelection[2] = new Text(GameVariables.mainMenu2);
        textSelection[3] = new Text(GameVariables.mainMenu3);
        textSelection[4] = new Text(GameVariables.mainMenu4);

        verticalBox = new VBox(textMenuHeader, textSelection[0], textSelection[1], textSelection[2], textSelection[3], textSelection[4]);
        verticalBox.setAlignment(Pos.TOP_CENTER);
        verticalBox.setVisible(true);
        verticalBox.setId("verticalBoxArray");
        verticalBox.getStylesheets().add("file:CSS/menu.css");

        HBox horizontalBox = new HBox();
        horizontalBox.setPrefSize(GameOptions.windowWidth, GameOptions.windowHeight - 125);
        horizontalBox.setAlignment(Pos.CENTER);
        horizontalBox.relocate(0, 125);
        horizontalBox.getChildren().add(verticalBox);

        effectGlow.setLevel(1);
        textMenuHeader.setEffect(effectGlowMenu);
        sceneMenu = new Scene(horizontalBox, GameOptions.windowWidth, GameOptions.windowHeight);
        sceneMenu.setFill(Color.BLACK);
    }

    public void pulsingGlowMenu() {
        if (glowLevelGoingUp) {
            glowLevel += 0.01;
            if (glowLevel >= 1) {
                glowLevel = 1;
                glowLevelGoingUp = false;
            }
        } else {
            glowLevel -= 0.01;
            if (glowLevel <= 0) {
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

        if (menuInput.contains(GameVariables.keyboardUp)) {
            selectedItem--;
            menuInput.remove(GameVariables.keyboardUp);
            if (selectedItem < 0) {
                selectedItem = 0;
            }
        }

        if (menuInput.contains(GameVariables.keyboardDown)) {
            selectedItem++;
            menuInput.remove(GameVariables.keyboardDown);
            if (selectedItem > textSelection.length - 1) {
                selectedItem = textSelection.length - 1;
            }
        }

        //Remove glow effect and add to the correct selection.
        for (int i = 0; i < textSelection.length; i++) {
            if (i != selectedItem) {
                textSelection[i].setEffect(null);
            } else {
                if (textSelection[i].getEffect() == null) {
                    textSelection[i].setEffect(effectGlow);
                }
            }
        }

        if (menuInput.contains(GameVariables.keyboardSelect)) {
            if (selectedItem == 0) {
                numberOfPlayers = 1;
                menuInput.remove(GameVariables.keyboardSelect);
                startGame = true;
            }

            if (selectedItem == 1) {
                numberOfPlayers = 2;
                menuInput.remove(GameVariables.keyboardSelect);
                startGame = true;
            }
            //TODO
            if (selectedItem == 2) {
                System.out.println("Options menu, not integrated yet.");
                menuInput.remove(GameVariables.keyboardSelect);
            }
            //TODO
            if (selectedItem == 3) {
                System.out.println("Network gameplay, not integrated yet.");
                menuInput.remove(GameVariables.keyboardSelect);
            }

            if (selectedItem == 4) {
                System.exit(0);
            }
        }


    }

    public VBox getVerticalBox() {
        return verticalBox;
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

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }
}


