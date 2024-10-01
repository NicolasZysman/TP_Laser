import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Nivel {
    private File archivo;
    public Grilla grilla;

    public Nivel(File informacion) {
        this.archivo = informacion;
        ArrayList<String> lineas = getLineas(archivo);
        //get direccion
        int[] fila_y_columna = getFilasColumnas(lineas);
        int filas = fila_y_columna[0];
        int columnas = fila_y_columna[1];
        ArrayList<String> posiciones = getPosiciones(lineas, columnas);
        this.grilla = new Grilla(lineas, posiciones, filas, columnas);
    }

    private ArrayList<String> getLineas(File archivo) {
        ClassLoader classLoader = getClass().getClassLoader();

        String fileName = archivo.getName();
        ArrayList<String> lineas = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("levels/" + fileName))))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.isEmpty()) {
                    continue;
                }
                lineas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineas;
    }

    private int[] getFilasColumnas(ArrayList<String> lineas) {
        int fila = 0;
        int columna = 0;
        for (String elemento : lineas) {
            if (elemento.startsWith("E")) {
                return new int[]{fila, columna};
            }

            if (elemento.length() > fila) {
                fila = elemento.length();
            }
            columna++;
        }

        return new int[]{fila, columna};
    }

    private ArrayList<String> getPosiciones(ArrayList<String> lineas, int columna) {
        ArrayList<String> posiciones = new ArrayList<>();

        posiciones.addAll(lineas.subList(columna, lineas.size()));

        lineas.subList(columna, lineas.size()).clear();

        return posiciones;
    }

    public void reset() {}
}