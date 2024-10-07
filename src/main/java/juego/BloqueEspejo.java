package juego;

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
    public boolean sePuedeMover() {
        return true;
    }

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        String nueva_direccion;
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

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
