package juego.app;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import juego.Celda;

public class VistaCelda{
    private final Celda celda;
    private final int fila;
    private final int columna;
    private final StackPane pane;

    public VistaCelda(Celda celda, int fila, int columna) {
        this.celda = celda;
        this.fila = fila;
        this.columna = columna;
        this.pane = new StackPane();
        renderCell();
    }

    private void renderCell() {
        Rectangle rect = new Rectangle(40, 40);
        rect.setStroke(Color.BLACK);
        switch (celda.getIdentificador()) {
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
        pane.getChildren().add(rect);
    }

    public StackPane getPane() {
        return pane;
    }
}
