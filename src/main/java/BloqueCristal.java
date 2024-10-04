public class BloqueCristal extends Bloque {

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

        if (x % 2 == 0) {
            return new String[] {String.valueOf(x + 1), String.valueOf(y + 3), direccion};
        }

        return new String[] {String.valueOf(x + 3), String.valueOf(y + 1), direccion};
    };
}
