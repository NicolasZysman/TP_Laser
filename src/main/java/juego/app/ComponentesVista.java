package juego.app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import juego.Juego;

public class ComponentesVista {
    private final Stage stage;
    private final Juego juego;
    private final GridPane gridPane;
    private final VBox levelButtons;

    public ComponentesVista(Stage stage, Juego juego) {
        this.stage = stage;
        this.juego = juego;
        this.gridPane = new GridPane();
        this.levelButtons = new VBox(10);

        setupLayout();
    }

    private void setupLayout() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(false);

        levelButtons.setAlignment(Pos.TOP_LEFT);
        createLevelButtons();

        BorderPane root = new BorderPane();
        root.setLeft(levelButtons);
        root.setCenter(gridPane);

        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setTitle("Laser");
        stage.setScene(scene);
        stage.show();
    }

    private void createLevelButtons() {
        for (int i = 1; i <= 6; i++) {
            Button levelButton = new Button("Level " + i);
            levelButton.setUserData(i);
            levelButtons.getChildren().add(levelButton);
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public VBox getLevelButtons() {
        return levelButtons;
    }
}
