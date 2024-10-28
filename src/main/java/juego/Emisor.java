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

    public int contarObjetivosCompletados(Laser actual, ArrayList<Point> finales, ArrayList<Point> finalesObtenidos) {
        int contador = 0;
        if (actual == null)
            return contador;

        for (Point finalPosicion : finales) {
            Point objetivo = actual.getPosicionFinal();
            if (finalPosicion.equals(objetivo) && !finalesObtenidos.contains(objetivo)) {
                finalesObtenidos.add(objetivo);
                contador++;
            }
        }
        
        contador += contarObjetivosCompletados(actual.getSiguiente(), finales, finalesObtenidos);
        contador += contarObjetivosCompletados(actual.getAlternativo(), finales, finalesObtenidos);
        
        return contador;
    }
}
