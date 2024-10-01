public class Celda {
    private Bloque bloque;
    private char identificador;
    private boolean esEmisor;
    private boolean esObjetivo;
    private int x;
    private int y;

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
            case 'E':
                this.bloque = null;
                break;
            default:
                this.bloque = null;
                // espacio vacio
        }
    }

    public int[] getPosicion() {
        return new int[] {x, y};
    }

    public char getIdentificador() {
        return this.identificador;
    }

    public int[] interactuar(Laser laser) {
        return this.bloque.interactuarConLaser(laser);
    }

    @Override
    public String toString() {
        return bloque != null ? bloque.toString() : ".";
    }
}
