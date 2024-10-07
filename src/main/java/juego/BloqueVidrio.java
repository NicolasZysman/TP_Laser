package juego;

public class BloqueVidrio implements Bloque {

    @Override
    public boolean bloqueVacio() {
        return false;
    }

    @Override
    public boolean esUnBloqueNormal() {
        return false;
    }

    @Override
    public boolean sePuedeMover() {
        return true;
    }

    @Override
    public String[] interactuarConLaser(int[] posicion_inicial, String direccion) {
        int x = posicion_inicial[0];
        int y = posicion_inicial[1];

//        int x1;
//        int x2;
//        int y1;
//        int y2;
//        String nueva_direccion = "";
//
//        if (direccion.startsWith("S")) {
//            x1 = x + 1;
//            if (x % 2 == 0) {
//                x2 = x - 1;
//                nueva_direccion += "N";
//            } else {
//                x2 = x + 1;
//                nueva_direccion += "S";
//            }
//        } else {
//            x1 = x - 1;
//            if (x % 2 == 0) {
//                x2 = x + 1;
//                nueva_direccion += "S";
//            } else {
//                x2 = x - 1;
//                nueva_direccion += "N";
//            }
//        }
//
//        if (direccion.endsWith("E")) {
//            y1 = y + 1;
//            if (y % 2 == 0) {
//                y2 = y - 1;
//                nueva_direccion += "W";
//            } else {
//                y2 = y + 1;
//                nueva_direccion += "E";
//            }
//        } else {
//            y1 = y - 1;
//            if (y % 2 == 0) {
//                y2 = y + 1;
//                nueva_direccion += "E";
//            } else {
//                y2 = y - 1;
//                nueva_direccion += "W";
//            }
//        }
//
//        return new String[] {String.valueOf(x1), String.valueOf(y1), direccion, String.valueOf(x2), String.valueOf(y2), nueva_direccion};
        BloqueNormal primera_direccion = new BloqueNormal(false);
        BloqueEspejo segunda_direccion = new BloqueEspejo();

        String[] primeraParte = primera_direccion.interactuarConLaser(posicion_inicial, direccion);
        String[] segundaParte = segunda_direccion.interactuarConLaser(posicion_inicial, direccion);

        String[] resultado = new String[primeraParte.length + segundaParte.length];

        System.arraycopy(primeraParte, 0, resultado, 0, primeraParte.length);
        System.arraycopy(segundaParte, 0, resultado, primeraParte.length, segundaParte.length);

        return resultado;
    }
}
