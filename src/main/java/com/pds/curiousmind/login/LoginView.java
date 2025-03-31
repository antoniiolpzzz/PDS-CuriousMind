package com.pds.curiousmind.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar la vista FXML
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("/com/pds/curiousmind/login-view.fxml"));
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
