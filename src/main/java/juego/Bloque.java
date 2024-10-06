package juego;

public interface Bloque {

    public boolean BloqueVacio();

    public boolean EsUnBloqueNormal();

    public boolean SePuedeMover();

    public String[] interactuarConLaser(int[] posicion_inicial, String direccion);
}
