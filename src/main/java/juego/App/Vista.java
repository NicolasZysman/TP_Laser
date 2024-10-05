package juego.App;

import juego.Juego;
import javafx.application.Application;
import javafx.stage.Stage;

public class Vista extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        var juego = new Juego();
        stage.show();
    }
}
