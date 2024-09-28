import java.io.*;
import java.util.Objects;

public class Nivel {
    private File archivo;
    public Grilla grilla;

    public Nivel(File info) {
        this.archivo = info;
        ClassLoader classLoader = getClass().getClassLoader();

        int fila = 0;
        int columna = 0;
        String fileName = archivo.getName();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("levels/" + fileName))))) {
            String linea;
            while ((linea = reader.readLine()) != null && !linea.isEmpty()) {
                if (linea.length() > fila) {
                    fila = linea.length();
                }
                columna++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        fila *= 2;
        columna *= 2;

        this.grilla = new Grilla(fila, columna);
        this.grilla.mostrarMatriz();
    }
}
