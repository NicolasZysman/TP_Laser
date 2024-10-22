package juego;

import java.awt.*;

public class BloqueNormal implements Bloque {
    private final boolean vacio;

    public BloqueNormal(boolean vacio) {
        this.vacio = vacio;
    }

    @Override
    public boolean bloqueVacio() {
        return vacio;
    }

    @Override
    public boolean esUnBloqueNormal() {
        return true;
    }

    @Override
    public boolean movible() {
        return false;
    }

    @Override
    public String[] interactuarConLaser(Point posicion_inicial, String direccion) {
        int x = (int) posicion_inicial.getX();
        int y = (int) posicion_inicial.getY();

        if (direccion.startsWith("S")) {
            x += 1;
        } else {
            x -= 1;
        }

        if (direccion.endsWith("E")) {
            y += 1;
        } else {
            y -= 1;
        }

        return new String[] {String.valueOf(x), String.valueOf(y), direccion};
    }
}
