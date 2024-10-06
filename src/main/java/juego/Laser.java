package juego;

public class Laser {
    private int[] posicion_inicial;
    private int[] posicion_final;
    private String direccion;
    private Grilla grilla;
    private Laser siguiente;
    private Laser alternativo;

    public Laser(int[] posicion_inicial, String direccion, Grilla grilla) {
        this.posicion_inicial = posicion_inicial;
        this.grilla = grilla;
        String[] nueva_posicion = verificarBloque(direccion);
        int x = 0;
        int y = 0;

        if (nueva_posicion == null) {
            this.posicion_final = null;
            this.direccion = null;
            this.siguiente = null;
            this.alternativo = null;
        } else {
            x = Integer.parseInt(nueva_posicion[0]);
            y = Integer.parseInt(nueva_posicion[1]);
            this.posicion_final = new int[] {x, y};
            this.direccion = nueva_posicion[2];
            this.siguiente = new Laser(new int[] {x, y}, nueva_posicion[2], grilla);

            if (nueva_posicion.length > 3) {
                int x2 = Integer.parseInt(nueva_posicion[3]);
                int y2 = Integer.parseInt(nueva_posicion[4]);
                this.alternativo = new Laser(new int[] {x2, y2}, nueva_posicion[5], grilla);
            }
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

        if (celda_del_medio == null) {
            return null;
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

    public Laser getSiguiente() {
        return this.siguiente;
    }

    public Laser getAlternativo() {
        return this.alternativo;
    }



    // cada trazado verifica el proixmo bloque

    // preguntamos tipo de bloque

    // Si va al norte, posicion par, fijar arriba
    // Si va al sur, posicion par, fijar abajo
    // Si va  al este, es impar, miro a la derecha
    // Si va al oeste, es impar, miro a la izquierda

}
