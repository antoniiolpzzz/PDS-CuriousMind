package com.pds.curiousmind.signup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar la vista FXML
        FXMLLoader fxmlLoader = new FXMLLoader(SignupView.class.getResource("/com/pds/curiousmind/signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 740, 580);

        // Establecer el título de la ventana
        stage.setTitle("CuriousMinds");

        // Establecer la nueva escena
        stage.setScene(scene);

        stage.setX(300);
        stage.setY(100);

        // Mostrar la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
