import java.io.File;
import java.util.ArrayList;

public class Juego {
    public ArrayList<Nivel> niveles;

    public Juego() {
        this.niveles = new ArrayList<Nivel>();
    }

    public void crearNivel(File nivel) {
        Nivel nivel_actual = new Nivel(nivel);
        niveles.add(nivel_actual);
    }
}
