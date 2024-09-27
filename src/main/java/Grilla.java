public class Grilla {
    private Celda[][] matriz;

    public Grilla(int fila, int columna) {
        this.matriz = new Celda[fila][columna];
        System.out.printf("fila: %d columna: %d", fila, columna);
    }
}
