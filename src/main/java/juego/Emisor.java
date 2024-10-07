package juego;

import java.util.ArrayList;
import java.util.Arrays;

public class Emisor {
    private Laser primer_laser;

    public Emisor(int x, int y, String direccion, Grilla grilla) {
        this.primer_laser = new Laser(new int[] {x, y}, direccion, grilla);
    }

    public Laser getPrimerLaser() {
        return this.primer_laser;
    }

    public int contarObjetivos(Laser actual, ArrayList<int[]> finales) {
        int contador = 0;
        if (actual == null) {
            return contador;
        }

        for (int[] finalPosicion : finales) {
            if (Arrays.equals(finalPosicion, actual.getPosicionFinal())) {
                contador++;
            }
        }
        
        contador += contarObjetivos(actual.getSiguiente(), finales);
        contador += contarObjetivos(actual.getAlternativo(), finales);
        
        return contador;
    }
}
