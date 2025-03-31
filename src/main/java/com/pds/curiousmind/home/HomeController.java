package com.pds.curiousmind.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button logOutButton;

    @FXML
    private Label userLabel;

    @FXML
    protected void onLogOutButtonClick() {
        System.out.println("Log Out clicked");

        try {
            // Cargar la vista de Login nuevamente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pds/curiousmind/login-view.fxml"));
            Parent root = loader.load();  // Cargar la vista FXML del Login

            // Crear la nueva escena con la vista Login
            Scene loginScene = new Scene(root,740, 580);

            // Obtener el Stage actual (el Stage de la ventana actual)
            Stage currentStage = (Stage) logOutButton.getScene().getWindow(); // Asegúrate de usar el nombre correcto del botón

            // Establecer la nueva escena
            currentStage.setScene(loginScene);

            // Establecer la posición deseada en la pantalla (X, Y)
            currentStage.setX(300);  // X = 500 píxeles desde la parte izquierda de la pantalla
            currentStage.setY(100);  // Y = 300 píxeles desde la parte superior de la pantalla

            // Mostrar la nueva ventana de Login
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();  // Manejar cualquier excepción si el archivo FXML no se carga correctamente
        }
    }


}
