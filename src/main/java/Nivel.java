import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Nivel {
    public Grilla grilla;

    public Nivel(File informacion) {
        ArrayList<String> lineas = getLineas(informacion);

        ArrayList<String> posiciones = getPosiciones(lineas);

        int[] fila_y_columna = getFilasColumnas(lineas);
        int filas = (fila_y_columna[0] * 2) + 1;
        int columnas = (fila_y_columna[1] * 2) + 1;

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
                columna = elemento.length();
            }
            fila++;
        }

        return new int[]{fila, columna};
    }

    private ArrayList<String> getPosiciones(ArrayList<String> lineas) {
        ArrayList<String> posiciones = new ArrayList<>();
        int tamaño = lineas.size();

        for (int i = tamaño-1; i >= 0; i--) {
            String linea = lineas.get(i);

            if (linea.startsWith("E") || linea.startsWith("G")) {
                posiciones.add(linea);
                lineas.remove(i);
            }
        }

        Collections.reverse(posiciones);
        return posiciones;
    }
}