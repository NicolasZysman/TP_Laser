package juego.app;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import juego.Celda;

public class VistaCelda {
    private final Celda celda;
    private final int fila;
    private final int columna;
    private final Group group;

    public VistaCelda(Celda celda, int fila, int columna) {
        this.celda = celda;
        this.fila = fila;
        this.columna = columna;
        this.group = new Group();
        renderCelda();
    }

    private void renderCelda() {
        Rectangle rect = new Rectangle(80, 80);
//        rect.setStroke(Color.BLACK);
        switch (celda.identificador) {
            case 'F':
                rect.setFill(Color.BLACK);
                rect.setStroke(Color.BLACK);
                break;
            case 'B':
                rect.setFill(Color.SADDLEBROWN);
                rect.setStroke(Color.BLACK);
                break;
            case 'R':
                rect.setFill(Color.DARKCYAN);
                rect.setStroke(Color.BLACK);
                break;
            case 'G':
                rect.setFill(Color.LIGHTCYAN);
                rect.setStroke(Color.BLACK);
                break;
            case 'C':
                rect.setFill(Color.LIGHTSEAGREEN);
                rect.setStroke(Color.BLACK);
                break;
            case 'E':
                rect.setFill(Color.PURPLE);
                rect.setStroke(Color.BLACK);
                break;
            case 'O':
                rect.setFill(Color.RED);
                rect.setStroke(Color.BLACK);
                break;
            case 'g':
                rect.setFill(Color.GOLD);
                rect.setStroke(Color.BLACK);
                break;
            case '.':
                if (fila % 2 == 0) {
                    rect.setFill(Color.TRANSPARENT);
                } else {
                    if (columna % 2 == 0) {
                        rect.setFill(Color.TRANSPARENT);
                    } else {
                        rect.setFill(Color.GRAY);
                        rect.setStroke(Color.BLACK);
                    }
                }
                break;
            default:
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.TRANSPARENT);
                break;
        }
        group.getChildren().add(rect);
    }

    public Group getGroup(int x, int y) {
        if (x == fila && y == columna)
            return group;

        return null;
    }
}

