package bauwerk78.implementer;

import bauwerk78.effects.GlowEffect;
import bauwerk78.model.UserInput;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class GameMenu {

    private final HBox horizontalBox = new HBox();
    private final UserInput userInput = new UserInput();
    private final GlowEffect glowEffectSelection = new GlowEffect(1);
    private final GlowEffect glowEffectHeader = new GlowEffect();

    private VBox verticalBox;
    private Text[] textArray;
    private Scene sceneMenu;
    private int selectedItem = 1;
    private int numberOfPlayers;

    private boolean startGame;
    private boolean mainMenu = true;

    public GameMenu() {
        init();
    }

    private void init() {
        textArray = GameVariables.getTextArray(GameVariables.mainMenu);
        textArray[0].setEffect(glowEffectHeader.getGlowEffect());

        verticalBox = GameVariables.getMenuVbox(textArray);

        horizontalBox.setPrefSize(GameOptions.windowWidth, GameOptions.windowHeight - 125);
        horizontalBox.setAlignment(Pos.CENTER);
        horizontalBox.relocate(0, 125);
        horizontalBox.getChildren().add(verticalBox);

        sceneMenu = new Scene(horizontalBox, GameOptions.windowWidth, GameOptions.windowHeight);
        sceneMenu.setFill(Color.BLACK);
    }

    public void updateMenu() {
        userInput.getPlayerInput(sceneMenu);
        List<String> menuInput = userInput.getInputList();

        glowEffectHeader.pulsingGlowEffect();

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
                    textArray[i].setEffect(glowEffectSelection.getGlowEffect());
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


