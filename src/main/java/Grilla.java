import java.util.ArrayList;
import java.util.LinkedList;

public class Grilla {
    private int fila;
    private int columna;
    private Celda[][] matriz;
    private int[] inicio;
    private ArrayList<int[]> finales;
    private LinkedList<Laser> laser;

    public Grilla(ArrayList<String> lineas, ArrayList<String> posiciones, int fila, int columna) {
        this.fila = (fila * 2) + 1;
        this.columna = (columna * 2) + 1;
        this.matriz = new Celda[this.fila][this.columna];
        //System.out.printf("fila: %d columna: %d", fila, columna);
        inicializarMatriz(lineas, posiciones);
        String direccion = agregarEmisorObjetivo(posiciones);
        printearMatriz();
        crearLaser(inicio[0], inicio[1], direccion);
    }

    private void inicializarMatriz(ArrayList<String> lineas, ArrayList<String> posiciones) {

        int indice_lineas = 0;
        int indice_caracter = 0;
        String linea = "";

        for (int i = 0; i < fila; i++) {

            if (indice_lineas != lineas.size()) {
                linea = lineas.get(indice_lineas);
            }

            for (int j = 0; j < columna; j++) {
                if (i % 2 != 0 && j % 2 != 0) {

                    try {
                        matriz[i][j] = new Celda(linea.charAt(indice_caracter)); //hay que mandar el tipo de bloque aca
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        matriz[i][j] = new Celda(' ');
                    }

                    indice_caracter++;

                    if (indice_caracter == (columna - 1) / 2) {
                        indice_lineas++;
                        indice_caracter = 0;
                    }

                } else {
                    matriz[i][j] = new Celda('.'); // Celda vacía por defecto
                }
            }
        }
    }

    public Celda getCelda(int x, int y) { return matriz[x][y]; }

    public void crearLaser(int x, int y, String direccion) {
        if (x >= fila || y >= columna) {
            return;
        }

        Laser nuevo_laser = new Laser(new int[] {x, y}, direccion, this);
        String nueva_dirreccion = nuevo_laser.verificarBloque();

        if (nueva_dirreccion.isEmpty()) {
            return;
        }

        laser.add(nuevo_laser);
        int[] posicion_final = nuevo_laser.getPosicionFinal();
        crearLaser(posicion_final[0], posicion_final[1], nueva_dirreccion);
    }

    public void mostrarMatriz() {
        for (Celda[] fila : matriz) {
            for (Celda celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    private void printearMatriz() {
        for (int i=0; i < fila; i++) {
            for (int j=0; j < columna; j++) {
                Celda elemento = matriz[i][j];
                System.out.printf("%c", elemento.getIdentificador());
            }
            System.out.print("\n");
        }
    }

    private String agregarEmisorObjetivo(ArrayList<String> posiciones) {
        String dirreccion = "";
        for (String posicion : posiciones) {
            String[] partes = posicion.split(" ");
            char tipo = partes[0].charAt(0);
            int y = Integer.parseInt(partes[1]);
            int x = Integer.parseInt(partes[2]);

            if (tipo == 'E') {
                this.inicio = new int[]{x, y};
                this.finales = new ArrayList<int[]>();
                dirreccion = partes[3];
            } else {
                finales.add(new int[] {x, y});
            }

            System.out.println(posicion);

            System.out.println("x: " + x + ", y: " + y);

            matriz[x][y] = new Celda(tipo);
        }
        return dirreccion;
    }
}
