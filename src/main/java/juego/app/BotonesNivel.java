package juego.app;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BotonesNivel {
    private final VBox levelButtons;

    public BotonesNivel() {
        this.levelButtons = new VBox(10);
        this.levelButtons.setAlignment(Pos.TOP_LEFT);
        createLevelButtons();
    }

    private void createLevelButtons() {
        for (int i = 1; i <= 6; i++) {
            Button levelButton = new Button("Level " + i);
            levelButton.setUserData(i);
            levelButtons.getChildren().add(levelButton);
        }
    }

    public VBox getLevelButtons() {
        return levelButtons;
    }
}