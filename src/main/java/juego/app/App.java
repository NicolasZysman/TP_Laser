package juego.App;

import javafx.application.Application;
import javafx.stage.Stage;
import juego.Juego;

public class App extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        Juego juego = new Juego();
        ComponentesVista vistaView = new ComponentesVista(stage);
        VistaControlador vistaController = new VistaControlador(juego, vistaView);
        vistaController.start();
    }
}