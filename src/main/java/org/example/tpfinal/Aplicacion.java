package org.example.tpfinal;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Dialogo dialogos = new Dialogo();
        dialogos.mostrarVentanaPrincipal(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}