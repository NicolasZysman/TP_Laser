import java.util.ArrayList;
import java.util.LinkedList;

public class Emisor {
    private String x;
    private String y;
    private String direccion;
    private Grilla grilla;
//    private ArrayList<Emisor[]> inicio;
    private ArrayList<int[]> finales;
    private LinkedList<Laser> laser;

    public Emisor(String x, String y, String direccion, Grilla grilla) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.grilla = grilla;
        this.laser = new LinkedList<Laser>();
        this.finales = new ArrayList<int[]>();
//        this.inicio = new ArrayList<Emisor[]>();
//        printearLaser();
        inicializarEmisores();
        grilla.printearLaser(laser);
    }

    public void inicializarEmisores() {
        crearLaser(Integer.parseInt(x), Integer.parseInt(y), direccion);
    }

    public void crearLaser(int x, int y, String direccion) {
        int fila = grilla.getFila();
        int columna = grilla.getColumna();

        if (x >= fila-1 || y >= columna-1) {
            return;
        }

        Laser nuevo_laser = new Laser(new int[] {x, y}, direccion, grilla);
        int[] posicion_final = nuevo_laser.getPosicionFinal();

        if (posicion_final == null) {
            return;
        }

//        grilla.printearLaser(laser);

        laser.add(nuevo_laser);
        crearLaser(posicion_final[0], posicion_final[1], nuevo_laser.getDireccion());
    }

}
