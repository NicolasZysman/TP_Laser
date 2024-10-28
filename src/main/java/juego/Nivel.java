package juego;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Nivel {
    public Grilla grilla;

    public Nivel(File informacion) {
        ArrayList<String> datos = getLineas(informacion);
        int[] fila_y_columna = getFilasColumnas(datos);
        int filas = fila_y_columna[0];
        int columnas = fila_y_columna[1];
        ArrayList<String> datos_laser = getDatosLaser(filas, datos);
        filas = (filas * 2) + 1;
        columnas = (columnas * 2) + 1;
        this.grilla = new Grilla(datos, datos_laser, filas, columnas);
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
        } catch (IOException _) {

        }

        return lineas;
    }

    private int[] getFilasColumnas(ArrayList<String> datos) {
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

    private ArrayList<String> getDatosLaser(int filas, ArrayList<String> datos) {
        if (datos.isEmpty()) {
            return new ArrayList<>() {};
        }

        ArrayList<String> posiciones = new ArrayList<>(datos.subList(filas, datos.size()));

        datos.subList(filas, datos.size()).clear();

        return posiciones;
    }

    public void intercambiarBloques(Point posicion_bloque, Point nueva_posicion) {
        Celda origen = grilla.getCelda((int) posicion_bloque.getX(), (int) posicion_bloque.getY());
        Bloque bloque_origen = origen.bloque;
        Celda destino = grilla.getCelda((int) nueva_posicion.getX(), (int) nueva_posicion.getY());

        if ((bloque_origen != null && !bloque_origen.movible()) || !origen.celdaOcupada() || destino.celdaOcupada() || destino.celdaVacia()) {
            return;
        }

        grilla.intercambiar(origen, destino);
        grilla.regenerarLaser();
    }

    public boolean nivelTerminado() {
        return grilla.objetivosCompletados();
    }

    public boolean nivelCreadoConExito(){
        ArrayList<String> posiciones = this.grilla.getDatoslaser();

        return !posiciones.isEmpty();
    }
}