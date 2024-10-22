package juego;

import java.awt.*;

public interface Bloque {

    boolean bloqueVacio();

    boolean esUnBloqueNormal();

    boolean movible();

    String[] interactuarConLaser(Point posicion_inicial, String direccion);
}
