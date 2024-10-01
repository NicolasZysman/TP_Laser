import java.util.ArrayList;

public class Grilla {
    private Celda[][] matriz;

    public Grilla(ArrayList<String> lineas, ArrayList<String> posiciones, int fila, int columna) {
        fila = (fila * 2) + 1;
        columna = (columna * 2) + 1;
        this.matriz = new Celda[fila][columna];
        //System.out.printf("fila: %d columna: %d", fila, columna);
        inicializarMatriz(lineas, fila, columna);
        printearMatriz(fila, columna);
    }

    private void inicializarMatriz(ArrayList<String> lineas, int fila, int columna) {

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
                    matriz[i][j] = new Celda('.');
                }
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

    private void printearMatriz(int fila, int columna) {
        for (int i=0; i < fila; i++) {
            for (int j=0; j < columna; j++) {
                Celda elemento = matriz[i][j];
                System.out.printf("%c", elemento.getIdentificador());
            }
            System.out.print("\n");
        }
    }
}
