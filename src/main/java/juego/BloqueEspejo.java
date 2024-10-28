package juego;

import java.awt.*;
import java.util.ArrayList;

public class BloqueEspejo implements Bloque {
    @Override
    public boolean movible() {
        return true;
    }

    @Override
    public ArrayList<String> interactuarConLaser(Point posicion_inicial, String direccion) {
        String nueva_direccion;
        int x = (int) posicion_inicial.getX();
        int y = (int) posicion_inicial.getY();

        if (x % 2 == 0) {
            switch (direccion) {
                case "SE" -> {
                    nueva_direccion = "NE";
                    x -= 1;
                    y += 1;
                }
                case "SW" -> {
                    nueva_direccion = "NW";
                    x -= 1;
                    y -= 1;
                }
                case "NE" -> {
                    nueva_direccion = "SE";
                    x += 1;
                    y += 1;
                }
                case null, default -> {
                    nueva_direccion = "SW";
                    x += 1;
                    y -= 1;
                }
            }
        } else {
            switch (direccion) {
                case "SE" -> {
                    nueva_direccion = "SW";
                    x += 1;
                    y -= 1;
                }
                case "SW" -> {
                    nueva_direccion = "SE";
                    x += 1;
                    y += 1;
                }
                case "NE" -> {
                    nueva_direccion = "NW";
                    x -= 1;
                    y -= 1;
                }
                case null, default -> {
                    nueva_direccion = "NE";
                    x -= 1;
                    y += 1;
                }
            }
        }

        ArrayList<String> nueva_posicion = new ArrayList<>();
        nueva_posicion.add(String.valueOf(x));
        nueva_posicion.add(String.valueOf(y));
        nueva_posicion.add(nueva_direccion);

        return nueva_posicion;
    }


}
