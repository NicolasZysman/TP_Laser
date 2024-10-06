package juego;

public class BloqueCristal extends Bloque {

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

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
    };
}
