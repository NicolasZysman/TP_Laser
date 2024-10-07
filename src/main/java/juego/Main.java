package juego;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Juego juego = new Juego();
        juego.crearNivel(new File("../resources/levels/level1.dat"));
        juego.crearNivel(new File("../resources/levels/level2.dat"));
        juego.crearNivel(new File("../resources/levels/level3.dat"));
        juego.crearNivel(new File("../resources/levels/level4.dat"));
        juego.crearNivel(new File("../resources/levels/level5.dat"));
        juego.crearNivel(new File("../resources/levels/level6.dat"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("Ingrese un nivel: ");
            int nivel = Integer.parseInt(reader.readLine());
            System.out.print("\n");
            while (!juego.nivelTermiando(nivel)) {
                juego.printearLaser(nivel);
                System.out.print("Ingrese la posicion que queres mover: ");
                String posicion_actual = reader.readLine();
                String[] posicion_dividida = posicion_actual.split(" ");
                int x1 = Integer.parseInt(posicion_dividida[0]);
                int y1 = Integer.parseInt(posicion_dividida[1]);

                System.out.print("\nIngrese la nueva posicion: ");
                String nueva_posicion = reader.readLine();
                String[] nueva_posicion_dividida = nueva_posicion.split(" ");
                int x2 = Integer.parseInt(nueva_posicion_dividida[0]);
                int y2 = Integer.parseInt(nueva_posicion_dividida[1]);
                System.out.print("\n");
                juego.moverBloque(new int[] {x1, y1}, new int[] {x2, y2}, nivel);
                System.out.print("\n");
            }

            System.out.println("Juego terminado!!!");
        }
    }
}
