package juego;

import java.awt.*;

public class BloqueCristal implements Bloque {
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
        return true;
    }

    @Override
    public String[] interactuarConLaser(Point posicion_inicial, String direccion) {
        int x = (int) posicion_inicial.getX();
        int y = (int) posicion_inicial.getY();

        if (x % 2 == 0) {
            if (direccion.startsWith("S")) {
                x += 2;
            } else {
                x -= 2;
            }
        } else {
            if (direccion.endsWith("E")) {
                y += 2;
            } else {
                y -= 2;
            }
        }

        return new String[] {String.valueOf(x), String.valueOf(y), direccion};
    }
}
