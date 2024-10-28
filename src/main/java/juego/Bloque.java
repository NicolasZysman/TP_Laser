package juego;

import java.awt.*;
import java.util.ArrayList;

public interface Bloque {

    boolean movible();

    ArrayList<String> interactuarConLaser(Point posicion_inicial, String direccion);
}
