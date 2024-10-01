public class Celda {
    private Bloque bloque;
    private char identificador;
    private boolean esEmisor;
    private boolean esObjetivo;

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
                this.bloque = null; // Celda vacía
                break;
            default:
                this.bloque = null;
                // espacio vacio
        }
    }

    public char getIdentificador() {
        return this.identificador;
    }

    public void pepe() {
        this.bloque.interactuarConLaser();
    }

    @Override
    public String toString() {
        return bloque != null ? bloque.toString() : ".";
    }
}
