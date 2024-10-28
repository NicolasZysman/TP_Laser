package juego;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Celda {
    public Bloque bloque;
    public char identificador;
    private boolean vacio;
    public boolean ocupado;

    public Celda(char tipoBloque) {
        this.identificador = tipoBloque;
        this.vacio = false;
        this.ocupado = true;
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
                this.bloque = null;
                break;
            case 'C':
                this.bloque = new BloqueCristal();
                break;
            case '.':
                this.bloque = null;
                this.ocupado = false;
                break;
            default:
                this.bloque = null;
                this.vacio = true;
                this.ocupado = false;
        }
    }

    public ArrayList<String> interactuar(Point posicion_inicial, String direccion) {
        ArrayList<String> nueva_posicion = new ArrayList<>();
        if (identificador == 'G') {
            BloqueEspejo bifurcacion = new BloqueEspejo();
            nueva_posicion = bifurcacion.interactuarConLaser(posicion_inicial, direccion);
        } else if (ocupado) {
            return this.bloque.interactuarConLaser(posicion_inicial, direccion);
        }

        int x = (int) posicion_inicial.getX();
        int y = (int) posicion_inicial.getY();

        if (direccion.startsWith("S")) {
            x += 1;
        } else {
            x -= 1;
        }

        if (direccion.endsWith("E")) {
            y += 1;
        } else {
            y -= 1;
        }

        nueva_posicion.add(String.valueOf(x));
        nueva_posicion.add(String.valueOf(y));
        nueva_posicion.add(direccion);

        if (nueva_posicion.size() > 3) {
            Collections.addAll(nueva_posicion);
            Collections.rotate(nueva_posicion, -3);
        }

        return nueva_posicion;
    }

    public boolean celdaVacia() {
        return this.vacio;
    }

    public boolean celdaOcupada() {
        return this.ocupado;
    }
}
