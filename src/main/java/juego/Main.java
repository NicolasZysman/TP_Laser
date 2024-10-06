package juego;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Juego juego = new Juego();
        juego.crearNivel(new File("../resources/levels/level1.dat"));
        juego.crearNivel(new File("../resources/levels/level2.dat"));
        juego.crearNivel(new File("../resources/levels/level3.dat"));
        juego.crearNivel(new File("../resources/levels/level4.dat"));
        juego.crearNivel(new File("../resources/levels/level5.dat"));
        juego.crearNivel(new File("../resources/levels/level6.dat"));

        // Nivel 1
//        juego.moverBloque(new int[] {5, 3}, new int[] {5, 5}, 1);
//        juego.moverBloque(new int[] {1, 5}, new int[] {7, 1}, 1);
//        juego.moverBloque(new int[] {11, 3}, new int[] {11, 5}, 1);
//        juego.moverBloque(new int[] {5, 7}, new int[] {7, 7}, 1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Ingrese un nivel: ");
            int nivel = Integer.parseInt(reader.readLine());
            System.out.print("\n");
            while (true) {
                juego.printearLaser(nivel);
                System.out.print("Ingrese la posicion que queres mover: ");
                String posicion_actual = reader.readLine();
                int x1 = posicion_actual.charAt(0) - '0';
                int y1 = posicion_actual.charAt(1) - '0';

                System.out.print("\nIngrese la nueva posicion: ");
                String nueva_posicion = reader.readLine();
                int x2 = nueva_posicion.charAt(0) - '0';
                int y2 = nueva_posicion.charAt(1) - '0';
                System.out.print("\n");
                juego.moverBloque(new int[] {x1, y1}, new int[] {x2, y2}, nivel);
                System.out.print("\n");
            }
        }
    }
}
