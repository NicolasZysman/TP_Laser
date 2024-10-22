package juego;

import java.awt.*;

public class BloqueOpaco implements Bloque {
    private final boolean es_movible;

    public BloqueOpaco(boolean movible) {
        this.es_movible = movible;
    }

    @Override
    public boolean bloqueVacio() {
        return false;
    }

    @Override
    public boolean esUnBloqueNormal() {
        return false;
    }

    @Override
    public boolean movible() {
        return es_movible;
    }

    @Override
    public String[] interactuarConLaser(Point posicion_inicial, String direccion) {
        return null;
    }
}
