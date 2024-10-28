package juego;

import java.awt.*;
import java.util.ArrayList;

public class BloqueOpaco implements Bloque {
    private final boolean es_movible;

    public BloqueOpaco(boolean movible) {
        this.es_movible = movible;
    }

    @Override
    public boolean movible() {
        return es_movible;
    }

    @Override
    public ArrayList<String> interactuarConLaser(Point posicion_inicial, String direccion) {
        return null;
    }
}
