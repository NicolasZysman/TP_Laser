package juego;

public class BloqueOpaco implements Bloque {
    private final boolean movible;

    public BloqueOpaco(boolean movible) {
        this.movible = movible;
    }

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
        return movible;
    }

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        return null;
    }
}
