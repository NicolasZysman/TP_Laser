package juego;

public class Laser {
    private final int[] posicion_inicial;
    private final int[] posicion_final;
    private final Grilla grilla;
    private final Laser siguiente;
    private final Laser alternativo;

    public Laser(int[] posicion_inicial, String direccion, Grilla grilla) {
        this.posicion_inicial = posicion_inicial;
        this.grilla = grilla;
        String[] nueva_posicion = verificarBloque(direccion);
        int x;
        int y;

        if (nueva_posicion == null) {
            this.posicion_final = null;
            this.siguiente = null;
            this.alternativo = null;
        } else {
            x = Integer.parseInt(nueva_posicion[0]);
            y = Integer.parseInt(nueva_posicion[1]);
            this.posicion_final = new int[] {x, y};
            this.siguiente = new Laser(new int[] {x, y}, nueva_posicion[2], grilla);

            if (nueva_posicion.length > 3) {
                this.alternativo = new Laser(new int[] {posicion_inicial[0], posicion_inicial[1]}, nueva_posicion[5], grilla);
            } else {
                this.alternativo = null;
            }
        }
    }

    private String[] verificarBloque(String direccion) {
        Celda celda_del_medio;
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

        return celda_del_medio.interactuar(this.posicion_inicial, direccion);
    }

    public int[] getPosicionInicial() {
        return this.posicion_inicial;
    }

    public int[] getPosicionFinal() {
        return this.posicion_final;
    }

    public Laser getSiguiente() {
        return this.siguiente;
    }

    public Laser getAlternativo() {
        return this.alternativo;
    }
}
