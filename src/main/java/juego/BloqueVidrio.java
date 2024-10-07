package juego;

public class BloqueVidrio implements Bloque {

    @Override
    public boolean BloqueVacio() {
        return false;
    }

    @Override
    public boolean EsUnBloqueNormal() {
        return false;
    }

    @Override
    public boolean SePuedeMover() {
        return true;
    }

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        String nueva_direccion = "";

        if (direccion.startsWith("S")) {
            x1 = x + 1;
            if (x % 2 == 0) {
                x2 = x - 1;
                nueva_direccion += "N";
            } else {
                x2 = x + 1;
                nueva_direccion += "S";
            }
        } else {
            x1 = x - 1;
            if (x % 2 == 0) {
                x2 = x + 1;
                nueva_direccion += "S";
            } else {
                x2 = x - 1;
                nueva_direccion += "N";
            }
        }

        if (direccion.endsWith("E")) {
            y1 = y + 1;
            if (y % 2 == 0) {
                y2 = y - 1;
                nueva_direccion += "W";
            } else {
                y2 = y + 1;
                nueva_direccion += "E";
            }
        } else {
            y1 = y - 1;
            if (y % 2 == 0) {
                y2 = y + 1;
                nueva_direccion += "E";
            } else {
                y2 = y - 1;
                nueva_direccion += "W";
            }
        }

        return new String[] {String.valueOf(x1), String.valueOf(y1), direccion, String.valueOf(x2), String.valueOf(y2), nueva_direccion};
    };
}
