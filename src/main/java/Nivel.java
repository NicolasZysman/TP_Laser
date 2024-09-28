import java.io.*;
import java.util.Objects;

public class Nivel {
    private File archivo;
    public Grilla grilla;

    public Nivel(File informacion) {
        this.archivo = informacion;
        int[] fila_y_columna = obtenerFilasColumnas(archivo);
        int fila = fila_y_columna[0];
        int columna = fila_y_columna[1];
        this.grilla = new Grilla(fila, columna);
    }

    private int[] obtenerFilasColumnas(File archivo) {
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

        fila = (fila * 2) + 1;
        columna = (columna * 2) + 1;

        return new int[]{fila, columna};
    }
}
