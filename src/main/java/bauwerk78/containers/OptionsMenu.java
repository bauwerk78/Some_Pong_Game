package bauwerk78.containers;

import bauwerk78.settings.GameVariables;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class OptionsMenu {

    private VBox verticalBox;


    public OptionsMenu() {
        init();
    }

    private void init() {
        Text textMenuHeader = new Text(GameVariables.optionsMenuHeader);
        textMenuHeader.setId("menu-header");

        Text[] textSelection = new Text[GameVariables.optionsMenuSelections];

        verticalBox = new VBox(textMenuHeader);

    }

    public VBox getVerticalBox() {
        return verticalBox;
    }


}//End of class.
