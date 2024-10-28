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
import juego.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class VistaControlador {
    private final Juego juego;
    private final ComponentesVista componentesVista;
    private int[] posicionBloque;
    private boolean bloqueSeleccionado = false;
    private final ArrayList<VistaCelda> celdas;

    public VistaControlador(Juego juego, ComponentesVista componentesVista) {
        this.juego = juego;
        this.componentesVista = componentesVista;
        this.celdas = new ArrayList<>();
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
        ArrayList<Point> finales = grilla.getFinales();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = grilla.getCelda(i, j);

                if (i % 2 != 0 && j % 2 != 0) {
                    Group group = crearCelda(celda, i, j);
                    componentesVista.getGridPane().add(group, j, i);
                }
            }
        }

        for (Emisor emisor : grilla.getEmisores()) {
            mostrarLaser(emisor.getPrimerLaser(), true);
        }

        for (Point objetivo : finales) {
            int objetivoY = (int) objetivo.getY();
            int objetivoX = (int) objetivo.getX();

            Group grupoObjetivo = null;
            Circle circle = new Circle(7, Color.RED);

            if (objetivoY % 2 != 0) {
                if (objetivoX == filas - 1) {
                    objetivoX -= 1;
                    circle.setLayoutX(40);
                    circle.setLayoutY(80);
                } else {
                    objetivoX += 1;
                    circle.setLayoutX(40);
                    circle.setLayoutY(0);
                }
            } else {
                if (objetivoY > 0) {
                    objetivoY -= 1;
                    circle.setLayoutX(80);
                    circle.setLayoutY(40);
                } else {
                    objetivoY += 1;
                    circle.setLayoutX(0);
                    circle.setLayoutY(40);
                }
            }


            for (int i=0; i < celdas.size() && grupoObjetivo == null; i++) {
                grupoObjetivo = celdas.get(i).getGroup(objetivoX, objetivoY);
            }

            if (grupoObjetivo != null)
                grupoObjetivo.getChildren().add(circle);
        }

        celdas.clear();
    }

    private void mostrarLaser(Laser actual, boolean primer_laser) {
        if (actual == null)
            return;

        int y1, y2, x1, x2;
        String direccion_actual = actual.getDireccion();
        Point posicion_inicial = actual.getPosicionInicial();

        if (direccion_actual == null)
            return;

        y1 = (int) posicion_inicial.getX();
        x1 = (int) posicion_inicial.getY();
        // Estan invertidas las posiciones

        Group grupo_actual = buscarGrupo(direccion_actual, x1, y1);

        Line linea = new Line();
        linea.setStrokeWidth(3);
        linea.setStroke(Color.RED);

        if (y1 % 2 != 0) {
            if (direccion_actual.endsWith("E"))
                x1 = 0;
            else x1 = 80;

            y1 = 40;
        } else {
            if (direccion_actual.startsWith("S"))
                y1 = 0;
            else y1 = 80;

            x1 = 40;
        }

        switch (direccion_actual) {
            case "SE" -> {
                y2 = y1 + 40;
                x2 = x1 + 40;
            }
            case "SW" -> {
                y2 = y1 + 40;
                x2 = x1 - 40;
            }
            case "NE" -> {
                y2 = y1 - 40;
                x2 = x1 + 40;
            }
            case "NW" -> {
                y2 = y1 - 40;
                x2 = x1 - 40;
            }
            case "SE|", "SW|" -> {
                y2 = 80;
                x2 = x1;
            }
            case "NE|", "NW|" -> {
                y2 = 0;
                x2 = x1;
            }
            case "|SE", "|NE" -> {
                y2 = y1;
                x2 = 80;
            }
            default -> {
                y2 = y1;
                x2 = 0;
            }
        }

        linea.setStartX(x1);
        linea.setStartY(y1);
        linea.setEndX(x2);
        linea.setEndY(y2);

        linea.toFront();

        grupo_actual.getChildren().add(linea);

        if (primer_laser) {
            Circle circle = new Circle(7, Color.RED);
            circle.setLayoutX(x1);
            circle.setLayoutY(y1);
            grupo_actual.getChildren().add(circle);
        }

        mostrarLaser(actual.getSiguiente(), false);
        mostrarLaser(actual.getAlternativo(), false);
    }

    private Group buscarGrupo(String direccion, int x, int y) {
        boolean grupo_encontrado = false;
        Group grupo_actual = null;
        int i, j;
        for (int k = 0; k < celdas.size() && !grupo_encontrado; k++) {
            if (y % 2 == 0) {
                if (direccion.startsWith("S")) {
                    i = y + 1;
                } else {
                    i = y - 1;
                }
                j = x;
            } else {
                i = y;
                if (direccion.endsWith("E")) {
                    j = x + 1;
                } else {
                    j = x - 1;
                }
            }
            grupo_actual = celdas.get(k).getGroup(i, j);
            if (grupo_actual != null)
                grupo_encontrado = true;
        }

        return grupo_actual;
    }

    private Group crearCelda(Celda celda, int fila, int columna) {
        VistaCelda vistaCelda = new VistaCelda(celda, fila, columna);
        Group group = vistaCelda.getGroup(fila, columna);
        this.celdas.add(vistaCelda);
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