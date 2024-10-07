package juego.App;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.util.Pair;
import juego.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;

public class Vista extends Application {

    private Juego juego;
    private GridPane gridPane;
    private int[] posicionBloque;
    private boolean bloqueSeleccionado;
//    private int nivelActual;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        juego = new Juego();

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(false);

        VBox levelButtons = new VBox(10);
        levelButtons.setAlignment(Pos.TOP_LEFT);

        //crear los botones
        for (int i = 0; i < 6; i++) {
            Button levelButton = new Button("Level " + (i + 1));
            int nivel = i;
            levelButton.setOnAction(event -> {
//                nivelActual = nivel;
                resetearNivel(nivel + 1); //habria que separalo en dos metodos un resetarNivel y otra crearNivel()
                Grilla grilla = juego.getNivel(1).grilla; //get(0) porque niveles siempre va a tener solo 1 nivel
                actualizarVista(grilla);
            });
            levelButtons.getChildren().add(levelButton);
        }

        BorderPane root = new BorderPane();
        root.setLeft(levelButtons);
        root.setCenter(gridPane);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Laser");
        stage.setScene(scene);
        stage.show();
    }

    //creaa la grilla mirando que es cada Celda
    private void renderGrilla(Grilla grilla) {
        int filas = grilla.getFila();
        int columnas = grilla.getColumna();

        ArrayList<Pair<Integer, Integer>> laserPositions = grilla.printearLaser();

        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < filas; i++) {
                Celda celda = grilla.getCelda(i, j);
                StackPane cellPane = createCeldaPane(celda, i, j);

                if (laserPositions.contains(new Pair<>(i, j))) {
                    Line line = new Line(0, 0, 40, 40); // Ajusta las coordenadas según el tamaño de tus celdas
                    line.setStrokeWidth(2);
                    line.setStroke(Color.RED);
                    gridPane.add(line, j, i);
                } else {
                    gridPane.add(cellPane, j, i);
                }
            }
        }
    }

    public void resetearNivel(int nivel) {
        if (juego.getNiveles().isEmpty()) {
            juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"));
            System.out.println("Primer nivel: " + juego.getNiveles());
        } else {
            System.out.println("Antes de eliminar nivel: " + juego.getNiveles());
            juego.getNiveles().removeFirst();
            juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"));
            System.out.println("Despues de eliminar nivel: " + juego.getNiveles());
        }
    }

    //Mira de que tipo es la celda y le asigna un color
    private StackPane createCeldaPane(Celda celda, int fila, int columna) {
        Rectangle rect = new Rectangle(40, 40);
        rect.setStroke(Color.BLACK);
        switch (celda.getIdentificador()) {
            case 'F':
                rect.setFill(Color.BLACK);
                break;
            case 'B':
                rect.setFill(Color.GRAY);
                break;
            case 'R':
                rect.setFill(Color.TURQUOISE);
                break;
            case 'G':
                rect.setFill(Color.GREEN);
                break;
            case 'C':
                rect.setFill(Color.LIGHTCYAN);
                break;
            case 'E':
                rect.setFill(Color.RED);
                break;
            case 'O':
                rect.setFill(Color.RED);
                break;
            case '.':
                rect.setFill(Color.WHITE);
                break;
            default:
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.TRANSPARENT);
                break;
        }

        StackPane pane = new StackPane();
        pane.getChildren().add(rect);

        //primer if es el primer click, el else es el segundo click
        pane.setOnMouseClicked(event -> {
            if (!bloqueSeleccionado) {
                posicionBloque = new int[]{fila, columna};
                bloqueSeleccionado = true;
            } else {
                int[] nuevaPosicion = {fila, columna};

                juego.moverBloque(posicionBloque, nuevaPosicion, 1);

                actualizarVista(juego.getNiveles().get(0).grilla);
                bloqueSeleccionado = false;
            }
        });
        return pane;
    }

    private void actualizarVista(Grilla grilla) {
        gridPane.getChildren().clear();
        renderGrilla(grilla);
    }
}