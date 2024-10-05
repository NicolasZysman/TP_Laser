package juego;

public class Celda {
    private Bloque bloque;
    private char identificador;
    private boolean esEmisor;
    private boolean esObjetivo;
    private boolean bloqueVacio;

    public Celda(char tipoBloque) {
        this.identificador = tipoBloque;
        switch (tipoBloque) {
            case 'F':
                this.bloque = new BloqueOpacoFijo();
                break;
            case 'B':
                this.bloque = new BloqueOpacoMovil();
                break;
            case 'R':
                this.bloque = new BloqueEspejo();
                break;
            case 'G':
                this.bloque = new BloqueVidrio();
                break;
            case 'C':
                this.bloque = new BloqueCristal();
                break;
            case '.':
                this.bloque = new BloqueNormal(); // juego.Celda vacía
                break;
            default:
                this.bloque = new BloqueNormal();
                // espacio vacio
        }
    }

    public char getIdentificador() {
        return this.identificador;
    }

    public String[] interactuar(int[] posicion_inicial, String direccion) {
        return this.bloque.interactuarConLaser(posicion_inicial, direccion);
    }
}
