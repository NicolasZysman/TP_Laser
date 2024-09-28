public class Grilla {
    private Celda[][] matriz;

    public Grilla(int fila, int columna) {
        this.matriz = new Celda[columna][fila];
//        System.out.printf("fila: %d columna: %d", fila, columna);
        inicializarMatriz();
    }

    private void inicializarMatriz() {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = new Celda('.'); //hay que mandar el tipo de bloque aca
            }
        }
    }

    public void mostrarMatriz() {
        for (Celda[] fila : matriz) {
            for (Celda celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
