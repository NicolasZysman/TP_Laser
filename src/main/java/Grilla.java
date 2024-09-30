import java.util.ArrayList;

public class Grilla {
    private Celda[][] matriz;

    public Grilla(ArrayList<String> lineas, ArrayList<String> posiciones, int fila, int columna) {
        this.matriz = new Celda[columna][fila];
        System.out.printf("fila: %d columna: %d", fila, columna);
        inicializarMatriz(fila, columna);
    }

    private void inicializarMatriz(int fila, int columna) {

        for (int i = 0; i < columna; i++) {
            for (int j = 0; j < fila; j++) {
                matriz[i][j] = new Celda('.'); //hay que mandar el tipo de bloque aca
            }
        }
    }

    public Celda getCelda(int x, int y) { return matriz[x][y]; }

    public void crearLaser() {}

    public void mostrarMatriz() {
        for (Celda[] fila : matriz) {
            for (Celda celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
