import java.io.File;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.crearNivel(new File("../resources/levels/level5.dat"));
    }
}
