public class Laser {
    private int[] posicion_inicial;
    private int[] posicion_final;
    private String direccion;
    private Grilla grilla;

    public Laser(int[] posicion_inicial, String direccion, Grilla grilla) {
        this.posicion_inicial = posicion_inicial;
        this.direccion = direccion;
        this.grilla = grilla;
        verificarBloque();
    }

    private void verificarBloque() {
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
        posicion_final = celda_del_medio.interactuar(this);
    }


    // cada trazado verifica el proixmo bloque

    // preguntamos tipo de bloque

    // Si va al norte, posicion par, fijar arriba
    // Si va al sur, posicion par, fijar abajo
    // Si va  al este, es impar, miro a la derecha
    // Si va al oeste, es impar, miro a la izquierda

}
