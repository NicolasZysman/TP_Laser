package juego;

public class BloqueEspejo extends Bloque {

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        // SE: (x+1, y-1)
        // SO: (x+1, y+1)
        // NE: (x-1, y-1)
        // NO: (x-1, y+1)
        String nueva_direccion = "";
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

        if (x % 2 == 0) {
            switch (direccion) {
                case "SE" -> {
                    nueva_direccion = "NE";
                    x -= 1;
                    y += 1;
                }
                case "SO" -> {
                    nueva_direccion = "NO";
                    x -= 1;
                    y -= 1;
                }
                case "NE" -> {
                    nueva_direccion = "SE";
                    x += 1;
                    y += 1;
                }
                case null, default -> {
                    nueva_direccion = "SO";
                    x += 1;
                    y -= 1;
                }
            }
        } else {
            switch (direccion) {
                case "SE" -> {
                    nueva_direccion = "SO";
                    x += 1;
                    y -= 1;
                }
                case "SO" -> {
                    nueva_direccion = "SE";
                    x += 1;
                    y += 1;
                }
                case "NE" -> {
                    nueva_direccion = "NO";
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
    };
}