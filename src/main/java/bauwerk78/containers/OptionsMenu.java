package bauwerk78.containers;

import bauwerk78.settings.GameVariables;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class OptionsMenu {

    private VBox verticalBox;


    public OptionsMenu() {
        init();
    }

    private void init() {

        Text[] textSelection = GameVariables.getTextArray(GameVariables.optionsMenu);

        verticalBox = new VBox(textSelection);
        verticalBox.setAlignment(Pos.TOP_CENTER);
        verticalBox.setId("verticalBoxArray");
        verticalBox.getStylesheets().add("file:CSS/menu.css");

    }

    public VBox getVerticalBox() {
        return verticalBox;
    }


}//End of class.
