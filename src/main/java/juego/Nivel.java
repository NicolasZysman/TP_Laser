package juego;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Nivel {
    public Grilla grilla;
    private ArrayList<String> datos;

    public Nivel(File informacion) {
        this.datos = getLineas(informacion);
        int[] fila_y_columna = getFilasColumnas();
        int filas = fila_y_columna[0];
        int columnas = fila_y_columna[1];
        ArrayList<String> posiciones = getPosiciones(filas);
        filas = (filas * 2) + 1;
        columnas = (columnas * 2) + 1;
        this.grilla = new Grilla(datos, posiciones, filas, columnas);
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

    private int[] getFilasColumnas() {
        int fila = 0;
        int columna = 0;
        for (String elemento : datos) {
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

    private ArrayList<String> getPosiciones(int filas) {
        ArrayList<String> posiciones = new ArrayList<>();

        posiciones.addAll(datos.subList(filas, datos.size()));

        datos.subList(filas, datos.size()).clear();

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

    public boolean resetear() {
        if (grilla.CantidadObjetivosCompletados()) {
//            int filas = grilla.getFila();
//            int columnas = grilla.getColumna();
//            ArrayList<String> posiciones = grilla.getPosiciones();
//            this.grilla = new Grilla(datos, posiciones, filas, columnas);
            return true;
        }

        return false;
    }
}