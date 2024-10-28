package juego;

import java.awt.*;
import java.util.ArrayList;

public class BloqueCristal implements Bloque {
    @Override
    public boolean movible() {
        return true;
    }

    @Override
    public ArrayList<String> interactuarConLaser(Point posicion_inicial, String direccion) {
        int x = (int) posicion_inicial.getX();
        int y = (int) posicion_inicial.getY();
        String direccion_alterada;

        if (x % 2 == 0) {
            if (direccion.startsWith("S")) {
                x += 2;
            } else {
                x -= 2;
            }
            direccion_alterada = direccion + "|";
        } else {
            if (direccion.endsWith("E")) {
                y += 2;
            } else {
                y -= 2;
            }
            direccion_alterada = "|" + direccion;
        }

        ArrayList<String> nueva_posicion = new ArrayList<>();
        nueva_posicion.add(String.valueOf(x));
        nueva_posicion.add(String.valueOf(y));
        nueva_posicion.add(direccion_alterada);

        return nueva_posicion;
    }
}
