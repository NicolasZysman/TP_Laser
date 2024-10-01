import java.util.ArrayList;

public class Grilla {
    private Celda[][] matriz;

    public Grilla(ArrayList<String> lineas, ArrayList<String> posiciones, int fila, int columna) {
        this.matriz = new Celda[columna][fila];
        inicializarMatriz(lineas, fila, columna);
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }

    private void inicializarMatriz(ArrayList<String> lineas, int fila, int columna) {

        for (int i = 0; i < columna; i++) {
            String lineaActual = lineas.get(i);
            for (int j = 0; j < fila; j++) {
                char tipoBloque = lineaActual.charAt(j);
                matriz[i][j] = new Celda(tipoBloque); //hay que mandar el tipo de bloque aca
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
