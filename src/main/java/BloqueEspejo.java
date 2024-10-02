public class BloqueEspejo extends Bloque {

    @Override
    public String[] interactuarConLaser(Laser laser) {
        // SE: (x+1, y-1)
        // SO: (x+1, y+1)
        // NE: (x-1, y-1)
        // NO: (x-1, y+1)
        String direccion_actual = laser.getDireccion();
        String nueva_direccion = "";
        int[] posicion_actual = laser.getPosicionInicial();
        int x = posicion_actual[0];
        int y = posicion_actual[1];

        if (x % 2 == 0) {
            if (direccion_actual.startsWith("S")) {
                nueva_direccion += "N";
            } else {
                nueva_direccion += "S";
            }
            nueva_direccion += direccion_actual.charAt(1);
        } else {
            nueva_direccion += direccion_actual.charAt(0);
            if (direccion_actual.endsWith("E")) {
                nueva_direccion += "O";
            } else {
                nueva_direccion += "E";
            }
        }

        return new String[] {String.valueOf(x), String.valueOf(y), nueva_direccion};
    };
}
