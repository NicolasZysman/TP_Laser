package juego;

import java.awt.*;

public class Celda {
    public Bloque bloque;
    public char identificador;

    public Celda(char tipoBloque) {
        this.identificador = tipoBloque;
        switch (tipoBloque) {
            case 'F':
                this.bloque = new BloqueOpaco(false);
                break;
            case 'B':
                this.bloque = new BloqueOpaco(true);
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
                this.bloque = new BloqueNormal(false);
                break;
            default:
                this.bloque = new BloqueNormal(true);
        }
    }

    public String[] interactuar(Point posicion_inicial, String direccion) {
        return this.bloque.interactuarConLaser(posicion_inicial, direccion);
    }
}
