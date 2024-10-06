package juego;

import java.util.ArrayList;
import java.util.LinkedList;

public class Emisor {
    private int x;
    private int y;
    private String direccion;
    private Grilla grilla;
    private ArrayList<int[]> finales;
    private Laser primer_laser;

    public Emisor(int x, int y, String direccion, Grilla grilla) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.finales = new ArrayList<int[]>();
        this.grilla = grilla;
        this.primer_laser = new Laser(new int[] {x, y}, direccion, grilla);
    }

    public Laser getPrimerLaser() {
        return this.primer_laser;
    }

}
