package juego;

import java.awt.*;
import java.util.ArrayList;

public class Laser {
    private final Point posicion_inicial;
    private final Point posicion_final;
    private final String direccion;
    private final Grilla grilla;
    private final Laser siguiente;
    private final Laser alternativo;

    public Laser(Point posicion_inicial, String direccion, Grilla grilla) {
        this.posicion_inicial = posicion_inicial;
        this.grilla = grilla;
        ArrayList<String> nueva_posicion = verificarBloque(direccion);
        int x;
        int y;

        if (nueva_posicion == null) {
            this.posicion_final = null;
            this.siguiente = null;
            this.alternativo = null;
            this.direccion = null;
        } else {
            x = Integer.parseInt(nueva_posicion.get(0));
            y = Integer.parseInt(nueva_posicion.get(1));
            this.direccion = nueva_posicion.get(2);
            this.posicion_final = new Point(x, y);
            this.siguiente = new Laser(new Point(x, y), nueva_posicion.get(2), grilla);

            if (nueva_posicion.size() > 3) {
                this.alternativo = new Laser(new Point((int) posicion_inicial.getX(), (int) posicion_inicial.getY()), nueva_posicion.get(5), grilla);
            } else {
                this.alternativo = null;
            }
        }
    }

    private ArrayList<String> verificarBloque(String direccion) {
        Celda celda_del_medio;

        if (direccion.startsWith("|") || direccion.endsWith("|"))
            direccion = direccion.replace("|", "");

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

    public String getDireccion() {return this.direccion;}
}
