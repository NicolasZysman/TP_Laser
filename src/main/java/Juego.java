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

    public void moverBloque() {}

    public boolean nivelTermiando() { return false; }
}
