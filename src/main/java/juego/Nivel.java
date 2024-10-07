package juego;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Nivel {
    public Grilla grilla;
    private final ArrayList<String> datos;

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
        ArrayList<String> lineas = new ArrayList<>();
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

        ArrayList<String> posiciones = new ArrayList<>(datos.subList(filas, datos.size()));

        datos.subList(filas, datos.size()).clear();

        return posiciones;
    }

    public void intercambiarBloques(int[] posicion_bloque, int[] nueva_posicion) {
        Celda bloque1 = grilla.getCelda(posicion_bloque[0], posicion_bloque[1]);
        Bloque tipo_bloque1 = bloque1.getBloque();
        Celda bloque2 = grilla.getCelda(nueva_posicion[0], nueva_posicion[1]);
        Bloque tipo_bloque2 = bloque2.getBloque();

        if (posicionesInvalidas(posicion_bloque, nueva_posicion) || !tipo_bloque1.movible() || tipo_bloque1.esUnBloqueNormal() || !tipo_bloque2.esUnBloqueNormal() || tipo_bloque2.bloqueVacio()) {
            return;
        }

        grilla.intercambiar(bloque1, bloque2);
        grilla.regenerarLaser();
        grilla.devolverPosicionesLaser();
    }

    private boolean posicionesInvalidas(int[] posicion_bloque, int[] nueva_posicion) {
        int x1 = posicion_bloque[0];
        int y1 = posicion_bloque[1];
        int x2 = nueva_posicion[0];
        int y2 = nueva_posicion[1];

        return x1 % 2 == 0 || y1 % 2 == 0 || x2 % 2 == 0 || y2 % 2 == 0;
    }

    public boolean resetear() {
        //            int filas = grilla.getFila();
        //            int columnas = grilla.getColumna();
        //            ArrayList<String> posiciones = grilla.getPosiciones();
        //            this.grilla = new Grilla(datos, posiciones, filas, columnas);
        return grilla.cantidadObjetivosCompletados();
    }
}