package juego;

public class BloqueOpaco implements Bloque {
    private boolean movible;

    public BloqueOpaco(boolean movible) {
        this.movible = movible;
    }

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
        return movible;
    }

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        return null;
    }
}
