package juego.app;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;
import juego.Grilla;
import juego.Juego;
import juego.Celda;

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
        handlerBotones();
    }

    public void start() {
        resetearNivel(1);
    }

    private void handlerBotones() {
        VBox botonesNiveles = componentesVista.getLevelButtons();
        for (var nodo : botonesNiveles.getChildren()) {
            if (nodo instanceof Button botonNivel) {
                botonNivel.setOnAction(_ -> {
                    int nivel = (int) botonNivel.getUserData();
                    resetearNivel(nivel);
                    Grilla grilla = juego.getNivel(1).grilla;
                    actualizarVista(grilla);
                });
            }
        }
    }

    private void resetearNivel(int nivel) {
        if (!juego.getNiveles().isEmpty()) {
            juego.getNiveles().removeFirst();
        }

        if (!juego.crearNivel(new File("../../resources/levels/level" + nivel + ".dat"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error en la lectura del archivo");
            alert.showAndWait();
            Platform.exit();
        }
    }

    private void actualizarVista(Grilla grilla) {
        GridPane gridPane = componentesVista.getGridPane();
        gridPane.getChildren().clear();
        renderGrilla(grilla);

        if (juego.nivelTermiando(1)) {
            Text textoNivelGanado = new Text("Nivel Ganado!");
            textoNivelGanado.setFont(new Font(40));
            textoNivelGanado.setFill(Color.WHITE);
            textoNivelGanado.setOpacity(0.5);

            Rectangle rect = new Rectangle();
            rect.setFill(Color.GREEN);
            rect.setOpacity(0.6);
            rect.setWidth(textoNivelGanado.getBoundsInLocal().getWidth() + 10);
            rect.setHeight(textoNivelGanado.getBoundsInLocal().getHeight() + 10);

            gridPane.add(rect, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
            GridPane.setHalignment(rect, HPos.CENTER);
            GridPane.setValignment(rect, VPos.CENTER);

            gridPane.add(textoNivelGanado, 0, 0, gridPane.getColumnCount(), gridPane.getRowCount());
            GridPane.setHalignment(textoNivelGanado, HPos.CENTER);
            GridPane.setValignment(textoNivelGanado, VPos.CENTER);
        }
    }

    private void renderGrilla(Grilla grilla) {
        int filas = grilla.getFila();
        int columnas = grilla.getColumna();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = grilla.getCelda(i, j);
                Group group = crearCelda(celda, i, j);

                if (i % 2 != 0 && j % 2 != 0) {
                    componentesVista.getGridPane().add(group, j, i);
                    char bloqueAnterior = tieneLaserAAID(grilla,i , j);
                    if (tieneLaserAAID(grilla,i, j) != '\0') {
                        Line linea = new Line(0,0,40,40);
                        linea.setStrokeWidth(3);
                        linea.setStroke(Color.RED);
                        if (celda.identificador == 'R') {
                            if (bloqueAnterior == 'N') {
                                componentesVista.getGridPane().add(linea, j, i-2);
                            } else if (bloqueAnterior == 'E') {
                                componentesVista.getGridPane().add(linea, j + 2, i);
                            } else if (bloqueAnterior == 'O') {
                                componentesVista.getGridPane().add(linea, j - 2, i);
                            } else {
                                componentesVista.getGridPane().add(linea, j, i + 2);
                            }
                        }
                        else if (celda.identificador != 'F') {
                            componentesVista.getGridPane().add(linea, j, i);
                        }
                    }
                } else if (celda.identificador == 'E') {
                    Circle circle = new Circle(7, Color.RED);
                    group.getChildren().add(circle);
                    componentesVista.getGridPane().add(circle, j, i);
                    circle.toFront();
                }
            }
        }
    }

    private char tieneLaserAAID(Grilla grilla, int iPosicion, int jPosicion) {
        ArrayList<Pair<Double, Double>> posicionesLaser = grilla.devolverPosicionesLaser();
        char direccion = ' ';

        for (int k = 0; k < 4; k++) {
            int x, y = 0;
            if (k == 0){
                x = iPosicion - 1;
                y = jPosicion;
                direccion = 'N';
            } else if (k == 1) {
                x = iPosicion;
                y = jPosicion + 1;
                direccion = 'E';
            } else if (k == 2) {
                x = iPosicion + 1;
                y = jPosicion;
                direccion = 'S';
            } else {
                x = iPosicion;
                y = jPosicion - 1;
                direccion = 'O';
            }

            if (posicionesLaser.contains(new Pair<>((double) x, (double) y))) {return direccion;}
        }

        return '\0';
    }

    private Group crearCelda(Celda celda, int fila, int columna) {
        VistaCelda vistaCelda = new VistaCelda(celda, fila, columna);
        Group group = vistaCelda.getGroup();
        group.setOnMouseClicked(_ -> handleClickCelda(fila, columna));
        return group;
    }

    private void handleClickCelda(int fila, int columna) {
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
    }
}
