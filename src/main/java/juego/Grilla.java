package juego;

import javafx.util.Pair;

import java.util.ArrayList;

public class Grilla {
    private int fila;
    private int columna;
    private Celda[][] matriz;
    private ArrayList<Emisor> emisores;
    private ArrayList<String> posiciones;
    private ArrayList<int[]> finales;

    public Grilla(ArrayList<String> lineas, ArrayList<String> posiciones, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.matriz = new Celda[this.fila][this.columna];
        this.emisores = new ArrayList<>();
        this.posiciones = posiciones;
        this.finales = new ArrayList<>();
        inicializarMatriz(lineas);
        agregarEmisorObjetivo();
    }

    private void inicializarMatriz(ArrayList<String> lineas) {

        int indice_lineas = 0;
        int indice_caracter = 0;
        String linea = "";

        for (int i = 0; i < fila; i++) {

            if (indice_lineas != lineas.size()) {
                linea = lineas.get(indice_lineas);
            }

            for (int j = 0; j < columna; j++) {
                if (i % 2 != 0 && j % 2 != 0) {

                    try {
                        matriz[i][j] = new Celda(linea.charAt(indice_caracter)); //hay que mandar el tipo de bloque aca
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        matriz[i][j] = new Celda(' ');
                    }

                    indice_caracter++;

                    if (indice_caracter == (columna - 1) / 2) {
                        indice_lineas++;
                        indice_caracter = 0;
                    }

                } else {
                    matriz[i][j] = new Celda('.'); // Celda vacía por defecto
                }
            }
        }
    }

    public Celda getCelda(int x, int y) {
        if (x >= fila || y >= columna || x < 0 || y < 0) {
            return null;
        }
        return matriz[x][y];
    }

    public int getFila() { return fila; }

    public int getColumna() { return columna; }

    public ArrayList<String> getPosiciones() {
        return this.posiciones;
    }

    private void agregarEmisorObjetivo() {
        for (String posicion : posiciones) {
            String[] partes = posicion.split(" ");
            char tipo = partes[0].charAt(0);
            int y = Integer.parseInt(partes[1]);
            int x = Integer.parseInt(partes[2]);

            if (tipo == 'E') {
                this.emisores.add(new Emisor(x, y, partes[3], this));
            } else {
                finales.add(new int[] {x, y});
            }

            if (tipo == 'G') {
                matriz[x][y] = new Celda(Character.toLowerCase(tipo));
            } else {
                matriz[x][y] = new Celda(tipo);
            }
        }
    }

    public ArrayList<Pair<Integer, Integer>> printearLaser() {
        ArrayList<Pair<Integer, Integer>> posiciones = new ArrayList<>();
        for (Emisor emisor : emisores) {
            Laser laser_actual = emisor.getPrimerLaser();
            PrintearLaserRecursivo(posiciones, laser_actual);
        }

        for (int i=0; i<fila; i++) {
            for (int j=0; j<columna; j++) {
                if (posiciones.contains(new Pair<>(i, j))) {
                    System.out.print("\u001B[31m" + matriz[i][j].getIdentificador() + "\u001B[0m");
                } else {
                    System.out.print(matriz[i][j].getIdentificador());
                }
            }
            System.out.print("\n");
        }

        return posiciones;
    }

    private void PrintearLaserRecursivo(ArrayList<Pair<Integer, Integer>> posiciones, Laser actual) {
        if (actual == null) {
            return;
        }

        Pair<Integer, Integer> posicion_inicial = new Pair<>(actual.getPosicionInicial()[0], actual.getPosicionInicial()[1]);
        posiciones.add(posicion_inicial);
        PrintearLaserRecursivo(posiciones, actual.getSiguiente());
        PrintearLaserRecursivo(posiciones, actual.getAlternativo());
    }

    public void intercambiar(Celda bloque1, Celda bloque2) {
        char indentificador_auxiliar = bloque1.identificador;
        Bloque bloque_auxiliar = bloque1.bloque;
        bloque1.identificador = bloque2.identificador;
        bloque1.bloque = bloque2.bloque;
        bloque2.identificador = indentificador_auxiliar;
        bloque2.bloque = bloque_auxiliar;
    }

    public void regenerarLaser() {
        emisores.clear();
        finales.clear();
        agregarEmisorObjetivo();
    }

    public boolean CantidadObjetivosCompletados() {
        int contador = 0;
        for (Emisor emisor : emisores) {
            contador += emisor.contarObjetivos(emisor.getPrimerLaser(), finales);
        }

        if (contador == finales.size()) {
            return true;
        }

        return false;
    }
}
