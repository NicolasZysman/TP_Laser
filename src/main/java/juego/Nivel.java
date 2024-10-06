package juego;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Nivel {
    public Grilla grilla;

    public Nivel(File informacion) {
        ArrayList<String> lineas = getLineas(informacion);
        int[] fila_y_columna = getFilasColumnas(lineas);
        int filas = fila_y_columna[0];
        int columnas = fila_y_columna[1];
        ArrayList<String> posiciones = getPosiciones(lineas, filas);
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

    private ArrayList<String> getPosiciones(ArrayList<String> lineas, int filas) {
        ArrayList<String> posiciones = new ArrayList<>();

        posiciones.addAll(lineas.subList(filas, lineas.size()));

        lineas.subList(filas, lineas.size()).clear();

        return posiciones;
    }

    public void intercambiarBloques(int[] posicion_bloque, int[] nueva_posicion) {
        Celda bloque1 = grilla.getCelda(posicion_bloque[0], posicion_bloque[1]);
        Bloque tipo_bloque1 = bloque1.getBloque();
        Celda bloque2 = grilla.getCelda(nueva_posicion[0], nueva_posicion[1]);
        Bloque tipo_bloque2 = bloque2.getBloque();

        if (!tipo_bloque1.SePuedeMover() || tipo_bloque1.EsUnBloqueNormal() || !tipo_bloque2.EsUnBloqueNormal() || tipo_bloque2.BloqueVacio()) {
            return;
        }

        grilla.intercambiar(bloque1, bloque2);
        grilla.regenerarLaser();
        grilla.printearLaser();
    }

    public void reset() {}
}