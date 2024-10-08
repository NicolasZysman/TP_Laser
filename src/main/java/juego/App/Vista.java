package juego.App;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
            levelButton.setOnAction(_ -> {
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

        ArrayList<Pair<Integer, Integer>> posicionesLaser = grilla.devolverPosicionesLaser();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = grilla.getCelda(i, j);
                StackPane cellPane = createCeldaPane(celda, i, j);

                if (celda.identificador == 'E') {
                    gridPane.add(cellPane, j, i);
                } else if (posicionesLaser.contains(new Pair<>(i, j))) {
                    Rectangle rect = new Rectangle(40, 40);
                    rect.setFill(Color.RED);
                    gridPane.add(rect, j, i);
                } else {
                    gridPane.add(cellPane, j, i);
                }
            }
        }
    }

    public void resetearNivel(int nivel) {
        if (juego.getNiveles().isEmpty()) {
            juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"));
        } else {
            juego.getNiveles().removeFirst();
            juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"));
        }
    }

    //Mira de que tipo es la celda y le asigna un color
    private StackPane createCeldaPane(Celda celda, int fila, int columna) {
        Rectangle rect = new Rectangle(40, 40);
        rect.setStroke(Color.BLACK);
        switch (celda.identificador) {
            case 'F':
                rect.setFill(Color.BLACK);
                break;
            case 'B':
                rect.setFill(Color.SADDLEBROWN);
                break;
            case 'R':
                rect.setFill(Color.DARKCYAN);
                break;
            case 'G':
                rect.setFill(Color.LIGHTCYAN);
                break;
            case 'C':
                rect.setFill(Color.LIGHTSEAGREEN);
                break;
            case 'E':
                rect.setFill(Color.PURPLE);
                break;
            case 'O':
                rect.setFill(Color.RED);
                break;
            case 'g':
                rect.setFill(Color.GOLD);
                break;
            case '.':
                if (fila % 2 == 0) {
                    rect.setFill(Color.GRAY);
                } else {
                    if (columna % 2 == 0) {
                        rect.setFill(Color.GRAY);
                    } else {
                        rect.setFill(Color.LIGHTGRAY);
                    }
                }
                break;
            default:
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.TRANSPARENT);
                break;
        }

        StackPane pane = new StackPane();
        pane.getChildren().add(rect);

        //primer if es el primer click, el else es el segundo click
        pane.setOnMouseClicked(_ -> {
            if (!bloqueSeleccionado) {
                posicionBloque = new int[]{fila, columna};
                bloqueSeleccionado = true;
            } else {
                int[] nuevaPosicion = {fila, columna};

                if (!juego.nivelTermiando(1)) {
                    juego.moverBloque(posicionBloque, nuevaPosicion, 1);
                    actualizarVista(juego.getNiveles().getFirst().grilla);
                }

                bloqueSeleccionado = false;
            }
        });
        return pane;
    }

    private void actualizarVista(Grilla grilla) {
        gridPane.getChildren().clear();
        renderGrilla(grilla);
        if (nivelGanado()) {
            Rectangle rect = new Rectangle(gridPane.getWidth(), gridPane.getHeight());
            Text texto = new Text("Nivel Ganado");
            texto.setFont(Font.font(48));
            texto.setFill(Color.RED);
            texto.setStroke(Color.BLACK);
            texto.setStrokeWidth(2);
            texto.setTranslateX(250);
            texto.setTranslateY(250);
            rect.setFill(Color.GREEN);
            rect.setOpacity(0.1);
            gridPane.getChildren().add(rect);
            gridPane.getChildren().add(texto);
        }
    }

    private boolean nivelGanado() {
        return juego.nivelTermiando(1);
    }
}