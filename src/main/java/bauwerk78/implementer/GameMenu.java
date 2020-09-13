package bauwerk78.implementer;

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

public class GameMenu {

    private Text[] textArray;
    private final HBox horizontalBox = new HBox();

    private Scene sceneMenu;

    private final UserInput userInput = new UserInput();

    private final Glow effectGlowSelection = new Glow();
    private final Glow effectGlowHeader = new Glow();
    private double glowLevel;
    private boolean glowLevelGoingUp = true;

    private int selectedItem = 1;
    private int numberOfPlayers;

    private boolean startGame;
    private boolean mainMenu = true;


    public GameMenu() {
        init();
    }

    private void init() {
        textArray = GameVariables.getTextArray(GameVariables.mainMenu);

        VBox verticalBox = new VBox(textArray);

        verticalBox.setAlignment(Pos.TOP_CENTER);
        verticalBox.setId("verticalBoxArray");
        verticalBox.getStylesheets().add("file:CSS/menu.css");

        horizontalBox.setPrefSize(GameOptions.windowWidth, GameOptions.windowHeight - 125);
        horizontalBox.setAlignment(Pos.CENTER);
        horizontalBox.relocate(0, 125);
        horizontalBox.getChildren().add(verticalBox);

        effectGlowSelection.setLevel(1);
        textArray[0].setEffect(effectGlowHeader);
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
        effectGlowHeader.setLevel(glowLevel);

        if (menuInput.contains(GameVariables.keyboardUp)) {
            selectedItem--;
            menuInput.remove(GameVariables.keyboardUp);
            if (selectedItem < 1) {
                selectedItem = 1;
            }
        }

        if (menuInput.contains(GameVariables.keyboardDown)) {
            selectedItem++;
            menuInput.remove(GameVariables.keyboardDown);
            if (selectedItem > textArray.length - 1) {
                selectedItem = textArray.length - 1;
            }
        }

        //Remove glow effect and add to the correct selection.
        for (int i = 1; i < textArray.length; i++) {
            if (i != selectedItem) {
                textArray[i].setEffect(null);
            } else {
                if (textArray[i].getEffect() == null) {
                    textArray[i].setEffect(effectGlowSelection);
                }
            }
        }

        if (menuInput.contains(GameVariables.keyboardSelect)) {
            if (selectedItem == 1) {
                numberOfPlayers = 1;
                menuInput.remove(GameVariables.keyboardSelect);
                startGame = true;
            }
            if (selectedItem == 2) {
                numberOfPlayers = 2;
                menuInput.remove(GameVariables.keyboardSelect);
                startGame = true;
            }
            //TODO
            if (selectedItem == 3) {
                System.out.println("Options menu, not integrated yet.");
                menuInput.remove(GameVariables.keyboardSelect);
            }
            //TODO
            if (selectedItem == 4) {
                System.out.println("Network gameplay, not integrated yet.");
                menuInput.remove(GameVariables.keyboardSelect);
            }

            if (selectedItem == 5) {
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

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }
}


