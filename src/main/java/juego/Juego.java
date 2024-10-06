package juego;

import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;

public class Juego {
    public ArrayList<Nivel> niveles;

    public Juego() {
        this.niveles = new ArrayList<Nivel>();
    }

    public void crearNivel(File informacion) {
        Nivel nivel_actual = new Nivel(informacion);
        niveles.add(nivel_actual);
    }

    public void moverBloque(int[] posicion_bloque, int[] nueva_posicion, int numero_nivel) {
        Nivel nivel_actual = niveles.get(numero_nivel-1);
        nivel_actual.intercambiarBloques(posicion_bloque, nueva_posicion);
    }

    public void printearLaser(int nivel_actual) {
        Nivel nivel = niveles.get(nivel_actual - 1);
        nivel.grilla.printearLaser();
    }

    public boolean nivelTermiando(int numero_nivel) {
        //Nivel nivel = niveles.get(numero_nivel-1);
        return false;
    }
}
