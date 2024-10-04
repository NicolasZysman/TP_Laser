public class BloqueNormal extends Bloque {
    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

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
