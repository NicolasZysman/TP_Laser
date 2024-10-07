package juego;

public interface Bloque {

    boolean bloqueVacio();

    boolean esUnBloqueNormal();

    boolean movible();

    String[] interactuarConLaser(int[] posicion_inicial, String direccion);
}
