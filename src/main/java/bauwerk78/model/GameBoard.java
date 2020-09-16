package bauwerk78.model;

import bauwerk78.settings.GameOptions;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GameBoard {

    private final Pane paneGameBackground = new Pane();

    public GameBoard() {
        initGameBoard();
    }

    private void initGameBoard() {
        Background background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));

        paneGameBackground.setPrefSize(GameOptions.windowWidth, GameOptions.windowHeight);
        paneGameBackground.setBackground(background);

        Line lineBoardMiddle = new Line(GameOptions.windowWidth / 2d, 0, GameOptions.windowWidth / 2d, GameOptions.windowHeight);
        lineBoardMiddle.setStroke(Color.WHITE);
        lineBoardMiddle.setStrokeWidth(1);
        lineBoardMiddle.getStrokeDashArray().addAll(10d, 5d);

        paneGameBackground.getChildren().add(lineBoardMiddle);
    }

    public Pane getPaneBackground() {
        return paneGameBackground;
    }
}
