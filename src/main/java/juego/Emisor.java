package juego;

import java.awt.*;
import java.util.ArrayList;

public class Emisor {
    private final Laser primer_laser;

    public Emisor(int x, int y, String direccion, Grilla grilla) {
        this.primer_laser = new Laser(new Point(x, y), direccion, grilla);
    }

    public Laser getPrimerLaser() {
        return this.primer_laser;
    }

    public int contarObjetivosCompletados(Laser actual, ArrayList<Point> finales) {
        int contador = 0;
        if (actual == null) {
            return contador;
        }

        for (Point finalPosicion : finales) {
            if (finalPosicion.equals(actual.getPosicionFinal())) {
                contador++;
            }
        }
        
        contador += contarObjetivosCompletados(actual.getSiguiente(), finales);
        contador += contarObjetivosCompletados(actual.getAlternativo(), finales);
        
        return contador;
    }
}
