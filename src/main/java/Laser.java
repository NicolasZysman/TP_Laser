public class Laser {
    private int[] posicion_inicial;
    private int[] posicion_final;
    private String direccion;
    private Grilla grilla;

    public Laser(int[] posicion_inicial, String direccion, Grilla grilla) {
        this.posicion_inicial = posicion_inicial;
        this.direccion = direccion;
        this.grilla = grilla;
    }

    public String verificarBloque() {
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
        String[] nueva_posicion = celda_del_medio.interactuar(this);
        int y = Integer.parseInt(nueva_posicion[0]);
        int x = Integer.parseInt(nueva_posicion[1]);
        posicion_final = new int[] {x, y};
        return nueva_posicion[2];
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
