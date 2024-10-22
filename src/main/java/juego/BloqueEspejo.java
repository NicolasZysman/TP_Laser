package juego;

import java.awt.*;

public class BloqueEspejo implements Bloque {

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

        return new String[] {String.valueOf(x), String.valueOf(y), nueva_direccion};
    }


}
