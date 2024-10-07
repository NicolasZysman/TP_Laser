package juego;

public interface Bloque {

    boolean bloqueVacio();

    boolean esUnBloqueNormal();

    boolean sePuedeMover();

    String[] interactuarConLaser(int[] posicion_inicial, String direccion);
}
