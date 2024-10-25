package juego.app;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ComponentesVista {
    private final Stage stage;
    private final GridPane gridPane;
    private final VBox botonNiveles;
    private final Group group;

    public ComponentesVista(Stage stage) {
        this.stage = stage;
        this.group = new Group();
        this.gridPane = new GridPane();
        this.botonNiveles = new VBox(10);

        escena();
    }

    private void escena() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(false);

        group.getChildren().add(gridPane);

        botonNiveles.setAlignment(Pos.TOP_LEFT);
        crearBotonesNiveles();

        BorderPane root = new BorderPane();
        root.setLeft(botonNiveles);
        root.setCenter(group);

        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setTitle("Laser");
        stage.setScene(scene);
        stage.show();
    }

    private void crearBotonesNiveles() {
        for (int i = 1; i <= 6; i++) {
            Button botonNivel = new Button("Nivel " + i);
            botonNivel.setUserData(i);
            botonNiveles.getChildren().add(botonNivel);
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Group getGroupGrande() { return group; }

    public VBox getLevelButtons() {
        return botonNiveles;
    }
}
