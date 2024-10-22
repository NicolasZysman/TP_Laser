package juego;

import java.awt.*;

public class Laser {
    private final Point posicion_inicial;
    private final Point posicion_final;
    private final Grilla grilla;
    private final Laser siguiente;
    private final Laser alternativo;

    public Laser(Point posicion_inicial, String direccion, Grilla grilla) {
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
            this.posicion_final = new Point(x, y);
            this.siguiente = new Laser(new Point(x, y), nueva_posicion[2], grilla);

            if (nueva_posicion.length > 3) {
                this.alternativo = new Laser(new Point((int) posicion_inicial.getX(), (int) posicion_inicial.getY()), nueva_posicion[5], grilla);
            } else {
                this.alternativo = null;
            }
        }
    }

    private String[] verificarBloque(String direccion) {
        Celda celda_del_medio;
        if (posicion_inicial.getX() % 2 == 0) {
            if (direccion.startsWith("S")) {
                celda_del_medio = grilla.getCelda((int) (posicion_inicial.getX()+1), (int) posicion_inicial.getY());
            } else {
                celda_del_medio = grilla.getCelda((int) (posicion_inicial.getX()-1), (int) posicion_inicial.getY());
            }
        } else {
            if (direccion.endsWith("E")) {
                celda_del_medio = grilla.getCelda((int) posicion_inicial.getX(), (int) (posicion_inicial.getY()+1));
            } else {
                celda_del_medio = grilla.getCelda((int) posicion_inicial.getX(), (int) (posicion_inicial.getY()-1));
            }
        }

        if (celda_del_medio == null) {
            return null;
        }

        return celda_del_medio.interactuar(this.posicion_inicial, direccion);
    }

    public Point getPosicionInicial() {
        return this.posicion_inicial;
    }

    public Point getPosicionFinal() {
        return this.posicion_final;
    }

    public Laser getSiguiente() {
        return this.siguiente;
    }

    public Laser getAlternativo() {
        return this.alternativo;
    }
}
