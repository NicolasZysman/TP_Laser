package juego;

public class BloqueNormal implements Bloque {
    private final boolean vacio;

    public BloqueNormal(boolean vacio) {
        this.vacio = vacio;
    }

    @Override
    public boolean bloqueVacio() {
        return vacio;
    }

    @Override
    public boolean esUnBloqueNormal() {
        return true;
    }

    @Override
    public boolean movible() {
        return false;
    }

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
