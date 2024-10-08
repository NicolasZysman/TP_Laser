package juego;

import java.io.File;
import java.util.ArrayList;

public class Juego {
    private final ArrayList<Nivel> niveles;

    public Juego() {
        this.niveles = new ArrayList<>();
    }

    public void crearNivel(File informacion) {
        Nivel nivel_actual = new Nivel(informacion);
        niveles.add(nivel_actual);
    }

    public Nivel getNivel(int numero) {
        return niveles.get(numero-1);
    }

    public ArrayList<Nivel> getNiveles() {
        return this.niveles;
    }

    public void moverBloque(int[] posicion_bloque, int[] nueva_posicion, int numero_nivel) {
        Nivel nivel_actual = niveles.get(numero_nivel-1);
        nivel_actual.intercambiarBloques(posicion_bloque, nueva_posicion);
    }

    public boolean nivelTermiando(int numero_nivel) {
        Nivel nivel = niveles.get(numero_nivel-1);
        return nivel.nivelTerminado();
    }
}
