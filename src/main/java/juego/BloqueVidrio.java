package juego;

import java.awt.*;

public class BloqueVidrio implements Bloque {

    @Override
    public boolean bloqueVacio() {
        return false;
    }

    @Override
    public boolean esUnBloqueNormal() {
        return false;
    }

    @Override
    public boolean movible() {
        return true;
    }

    @Override
    public String[] interactuarConLaser(Point posicion_inicial, String direccion) {
        BloqueNormal primera_direccion = new BloqueNormal(false);
        BloqueEspejo segunda_direccion = new BloqueEspejo();

        String[] primeraParte = primera_direccion.interactuarConLaser(posicion_inicial, direccion);
        String[] segundaParte = segunda_direccion.interactuarConLaser(posicion_inicial, direccion);

        String[] resultado = new String[primeraParte.length + segundaParte.length];

        System.arraycopy(primeraParte, 0, resultado, 0, primeraParte.length);
        System.arraycopy(segundaParte, 0, resultado, primeraParte.length, segundaParte.length);

        return resultado;
    }
}
