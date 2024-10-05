import java.util.ArrayList;
import java.util.LinkedList;

public class Grilla {
    private int fila;
    private int columna;
    private Celda[][] matriz;
//    private ArrayList<Emisor[]> inicio;
//    private ArrayList<int[]> finales;
//    private LinkedList<Laser> laser;
    // lista: 1, 2, 3, 4, 5, 6, 7, 8, 5, 6, 7, 8

    public Grilla(ArrayList<String> lineas, ArrayList<String> posiciones, int fila, int columna) {
        this.fila = (fila * 2) + 1;
        this.columna = (columna * 2) + 1;
        this.matriz = new Celda[this.fila][this.columna];
//        System.out.printf("fila: %d columna: %d\n", this.fila, this.columna);
        inicializarMatriz(lineas, posiciones);
        agregarEmisorObjetivo(posiciones);
//        printearMatriz();
//         printearLaser();
    }

    private void inicializarMatriz(ArrayList<String> lineas, ArrayList<String> posiciones) {

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
        return matriz[x][y];
    }

    public int getFila() { return fila; }

    public int getColumna() { return columna; }

    public void mostrarMatriz() {
        for (Celda[] fila : matriz) {
            for (Celda celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    private void printearMatriz() {
        int indice_laser = 0;
        for (int i=0; i < fila; i++) {
            for (int j=0; j < columna; j++) {
                Celda elemento = matriz[i][j];
                System.out.printf("%c", elemento.getIdentificador());
            }
            System.out.print("\n");
        }
    }

    private String agregarEmisorObjetivo(ArrayList<String> posiciones) {
        String dirreccion = "";
        for (String posicion : posiciones) {
            String[] partes = posicion.split(" ");
            char tipo = partes[0].charAt(0);
            int y = Integer.parseInt(partes[1]);
            int x = Integer.parseInt(partes[2]);

            if (tipo == 'E') {
//                inicio.add(new Emisor(String.valueOf(x), String.valueOf(y), partes[3]));
                new Emisor(String.valueOf(x), String.valueOf(y), partes[3], this);
            } else {
//                finales.add(new int[] {x, y});
                System.out.println("x: " + x + ", y: " + y);
            }

//            System.out.println(posicion);

//            System.out.println("x: " + x + ", y: " + y);

            matriz[x][y] = new Celda(tipo);
        }
        return dirreccion;
    }

    public void printearLaser(LinkedList<Laser> laser) {
        Laser ultimo_laser = laser.get(laser.size()-1);
        int[] posicion_final = ultimo_laser.getPosicionFinal();
        int x = posicion_final[0];
        int y = posicion_final[1];

        for (int i=0; i < fila; i++) {
            for (int j=0; j < columna; j++) {
                int[] posicion_laser = {};
                for(int k=0; k < laser.size(); k++) {
                    Laser laser_actual = laser.get(k);
                    int[] posicion = laser_actual.getPosicionInicial();
                    if (posicion[0] == i && posicion[1] == j) {
                        posicion_laser = new int[] {i, j};
                    }
                }

                if (posicion_laser.length > 0 || (x == i && y == j)) {
                    System.out.printf("\u001B[31m" + matriz[i][j].getIdentificador() + "\u001B[0m");
                } else {
                    System.out.print(matriz[i][j].getIdentificador());
                }
            }
            System.out.print("\n");
        }
    }
}
