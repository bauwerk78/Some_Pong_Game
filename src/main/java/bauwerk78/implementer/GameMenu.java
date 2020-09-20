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
    private final GlowEffect glowEffectHeader = new GlowEffect(0);

    private VBox verticalBox;
    private Text[] textArray;
    private Scene sceneMenu;
    private int selectedItem = 1;
    private int numberOfPlayers;

    private boolean startGame;
    private boolean mainMenu;
    private boolean optionsMenu;

    public GameMenu() {
        init();
    }

    private void init() {
        mainMenu = true;

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

        if (menuInput.contains(GameVariables.keyboardDown) || selectedItem == 0) {
            selectedItem++;
            menuInput.remove(GameVariables.keyboardDown);
            if (selectedItem > textArray.length - 1) {
                selectedItem = textArray.length - 1;
            }
        }

        //Remove static glow effect and add to the correct selection.
        for (int i = 1; i < textArray.length; i++) {
            if (i != selectedItem) {
                textArray[i].setEffect(null);
            } else {
                if (textArray[i].getEffect() == null) {
                    textArray[i].setEffect(glowEffectSelection.getGlowEffect());
                }
            }
        }

        //Switch to selected option if selection button is pressed.
        if (menuInput.contains(GameVariables.keyboardSelect)) {
            if (mainMenu) {
                mainMenu(selectedItem);
            }
            if (optionsMenu) {
                optionsMenu(selectedItem);
            }
            menuInput.remove(GameVariables.keyboardSelect);
        }

    }

    private void mainMenu(int selectedItem) {
        switch (selectedItem) {
            case 1:
                numberOfPlayers = 1;
                startGame = true;
                break;
            case 2:
                numberOfPlayers = 2;
                startGame = true;
                break;
            case 3:
                initMenu(GameVariables.optionsMenu);
                mainMenu = false;
                optionsMenu = true;
                break;
            case 4:
                //TODO
                System.out.println("Network gameplay, not integrated yet.");
                break;
            case 5:
                System.exit(0);
                break;
        }
    }
    //TODO
    private void optionsMenu(int selectedItem) {
        switch (selectedItem) {
            case 1:
                System.out.println("Resolution menu not implemented yet.");
                break;
            case 2:
                System.out.println("Player name menu not implemented yet.");
                break;
            case 3:
                System.out.println("Controls menu not implemented yet.");
                break;
            case 4:
                initMenu(GameVariables.mainMenu);
                mainMenu = true;
                optionsMenu = false;
                break;
        }
    }
    //TODO
    private void resolutionMenu(int selectedItem) {
        switch (selectedItem) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
    }

    //TODO
    private void playerNameMenu(int selectedItem) {
        switch (selectedItem) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
    }

    //TODO
    private void controlsMenu(int selectedItem) {
        switch (selectedItem) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
    }

    //TODO
    private void networkMenu(int selectedItem) {
        switch (selectedItem) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
    }

    private void initMenu(String[] stringArray) {
        horizontalBox.getChildren().removeAll(verticalBox);
        verticalBox.getChildren().removeAll(textArray);
        textArray = GameVariables.getTextArray(stringArray);
        textArray[0].setEffect(glowEffectHeader.getGlowEffect());
        verticalBox = GameVariables.getMenuVbox(textArray);
        horizontalBox.getChildren().addAll(verticalBox);
        selectedItem = 0;
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


