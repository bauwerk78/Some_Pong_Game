package bauwerk78.model;

import bauwerk78.implementer.MainGame;
import bauwerk78.settings.GameOptions;
import bauwerk78.settings.GameVariables;
import javafx.scene.paint.Color;

public class Score {

    private int scoreP1;
    private int scoreP2;

    public void showScore() {
        MainGame.gc.setFont(GameVariables.scoreFont);
        MainGame.gc.setFill(Color.WHITE);
        String scoreP1Text = GameOptions.playerOneName + " " + scoreP1;
        String scoreP2Text = GameOptions.playerTwoName + " " + scoreP2;
        MainGame.gc.fillText(scoreP1Text, GameOptions.windowWidth / 2d - (GameOptions.windowWidth / 4d), 30);
        MainGame.gc.fillText(scoreP2Text, GameOptions.windowWidth / 2d + (GameOptions.windowWidth / 4d) - (GameVariables.scoreFontSize * scoreP2Text.length()) / 2d, 30);
    }

    public void resetScore() {
        scoreP1 = 0;
        scoreP2 = 0;
    }

    public int getScoreP1() {
        return scoreP1;
    }

    public void setScoreP1(int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public void setScoreP2(int scoreP2) {
        this.scoreP2 = scoreP2;
    }
}
