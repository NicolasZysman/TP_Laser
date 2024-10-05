package juego;

public class Laser {
    private int[] posicion_inicial;
    private int[] posicion_final;
    private String direccion;
    private Grilla grilla;

    public Laser(int[] posicion_inicial, String direccion, Grilla grilla) {
        this.posicion_inicial = posicion_inicial;
        this.grilla = grilla;
        String[] nueva_posicion = verificarBloque(direccion);

        if (nueva_posicion.length == 0) {
            this.posicion_final = null;
            this.direccion = null;
        } else {
            this.posicion_final = new int[] {Integer.parseInt(nueva_posicion[0]), Integer.parseInt(nueva_posicion[1])};
            this.direccion = nueva_posicion[2];
        }
    }

    private String[] verificarBloque(String direccion) {
        Celda celda_del_medio = null;
        if (posicion_inicial[0] % 2 == 0) {
            if (direccion.startsWith("S")) {
                celda_del_medio = grilla.getCelda(posicion_inicial[0]+1, posicion_inicial[1]);
            } else {
                celda_del_medio = grilla.getCelda(posicion_inicial[0]-1, posicion_inicial[1]);
            }
        } else {
            if (direccion.endsWith("E")) {
                celda_del_medio = grilla.getCelda(posicion_inicial[0], posicion_inicial[1]+1);
            } else {
                celda_del_medio = grilla.getCelda(posicion_inicial[0], posicion_inicial[1]-1);
            }
        }

        String[] nueva_posicion = celda_del_medio.interactuar(this.posicion_inicial, direccion);

        return nueva_posicion;
    }

    public int[] getPosicionInicial() {
        return this.posicion_inicial;
    }

    public int[] getPosicionFinal() {
        return this.posicion_final;
    }

    public String getDireccion() {
        return this.direccion;
    }

    // cada trazado verifica el proixmo bloque

    // preguntamos tipo de bloque

    // Si va al norte, posicion par, fijar arriba
    // Si va al sur, posicion par, fijar abajo
    // Si va  al este, es impar, miro a la derecha
    // Si va al oeste, es impar, miro a la izquierda

}
