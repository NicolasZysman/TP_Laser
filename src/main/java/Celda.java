public class Celda {
    private Bloque bloque;

    public Celda(char tipoBloque) {
        switch (tipoBloque) {
            case 'F':
                //this.bloque = new BloqueFijo();
                break;
            case 'B':
                //this.bloque = new BloqueMovil();
                break;
            case 'R':
                //this.bloque = new BloqueEspejo();
                break;
            case 'G':
                //this.bloque = new BloqueVidrio();
                break;
            case 'C':
                //this.bloque = new BloqueCristal();
                break;
            case '.':
                this.bloque = null; // Celda vacía
                break;
            default:
                // espacio vacio
        }
    }


    @Override
    public String toString() {
        return bloque != null ? bloque.toString() : ".";
    }
}
