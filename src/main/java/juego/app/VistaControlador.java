package juego.app;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import juego.Grilla;
import juego.Juego;
import juego.Celda;
import javafx.util.Pair;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.util.ArrayList;

public class VistaControlador {
    private final Juego juego;
    private final ComponentesVista componentesVista;
    private int[] posicionBloque;
    private boolean bloqueSeleccionado = false;

    public VistaControlador(Juego juego, ComponentesVista componentesVista) {
        this.juego = juego;
        this.componentesVista = componentesVista;
        setupEventHandlers();
    }

    public void start() {
        resetearNivel(1);
    }

    private void setupEventHandlers() {
        VBox levelButtons = componentesVista.getLevelButtons();
        for (var node : levelButtons.getChildren()) {
            if (node instanceof Button) {
                Button levelButton = (Button) node;
                levelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int nivel = (int) levelButton.getUserData();
                        resetearNivel(nivel);
                        Grilla grilla = juego.niveles.getFirst().grilla;
                        actualizarVista(grilla);
                    }
                });
            }
        }
    }

    private void resetearNivel(int nivel) {
        if (juego.niveles.isEmpty()) {
            juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"));
        } else {
            juego.niveles.removeFirst();
            juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"));
        }
    }

    private void actualizarVista(Grilla grilla) {
        GridPane gridPane = componentesVista.getGridPane();
        gridPane.getChildren().clear();
        renderGrilla(grilla);

        if (juego.nivelTermiando(1)) {
            Rectangle rect = new Rectangle(gridPane.getWidth(), gridPane.getHeight(), Color.GREEN);
            rect.setOpacity(0.1);

            Text texto = new Text("Nivel Ganado");
            texto.setFont(Font.font(48));
            texto.setFill(Color.RED);
            texto.setStroke(Color.BLACK);
            texto.setStrokeWidth(2);
            texto.setTranslateX(250);
            texto.setTranslateY(250);

            gridPane.getChildren().addAll(rect, texto);
        }
    }

    private void renderGrilla(Grilla grilla) {
        int filas = grilla.getFila();
        int columnas = grilla.getColumna();

        ArrayList<Pair<Integer, Integer>> posicionesLaser = grilla.printearLaser();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = grilla.getCelda(i, j);
                StackPane cellPane = crearCelda(celda, i, j);

                if (celda.getIdentificador() == 'E') {
                    componentesVista.getGridPane().add(cellPane, j, i);
                } else if (posicionesLaser.contains(new Pair<>(i, j))) {
                    Rectangle rect = new Rectangle(40, 40, Color.RED);
                    componentesVista.getGridPane().add(rect, j, i);
                } else {
                    componentesVista.getGridPane().add(cellPane, j, i);
                }
            }
        }
    }

    private StackPane crearCelda(Celda celda, int fila, int columna) {
        VistaCelda cellView = new VistaCelda(celda, fila, columna);
        StackPane pane = cellView.getPane();
        pane.setOnMouseClicked(event -> handleCellClick(fila, columna));
        return pane;
    }

    private void handleCellClick(int fila, int columna) {
        if (!bloqueSeleccionado) {
            posicionBloque = new int[]{fila, columna};
            bloqueSeleccionado = true;
        } else {
            int[] nuevaPosicion = {fila, columna};

            if (!juego.nivelTermiando(1)) {
                juego.moverBloque(posicionBloque, nuevaPosicion, 1);
                actualizarVista(juego.niveles.getFirst().grilla);
            }

            bloqueSeleccionado = false;
        }
    }
}
