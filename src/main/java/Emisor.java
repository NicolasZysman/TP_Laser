import java.util.ArrayList;
import java.util.LinkedList;

public class Emisor {
    private int x;
    private int y;
    private String direccion;
    private Grilla grilla;
    private ArrayList<int[]> finales;
    private LinkedList<Laser> laser;

    public Emisor(int x, int y, String direccion, Grilla grilla) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.laser = new LinkedList<Laser>();
        this.finales = new ArrayList<int[]>();
        this.grilla = grilla;
        crearLaser(this.x, this.y, this.direccion);
    }

    public void crearLaser(int x, int y, String direccion) {
        int fila = grilla.getFila();
        int columna = grilla.getColumna();

        if (x >= fila || y >= columna || x < 0 || y < 0) {
            return;
        }

        Laser nuevo_laser = new Laser(new int[] {x, y}, direccion, grilla);

        int[] posicion_final = nuevo_laser.getPosicionFinal();

        if (posicion_final == null) {
            return;
        }

        laser.add(nuevo_laser);
        crearLaser(posicion_final[0], posicion_final[1], nuevo_laser.getDireccion());
    }

    public LinkedList<Laser> getLaser() {
        return this.laser;
    }

}
