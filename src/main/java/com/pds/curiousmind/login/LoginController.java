package com.pds.curiousmind.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtTelefonoEmail;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;  // Para la contraseña visible

    @FXML
    private Button togglePassword;

    private boolean isPasswordVisible = false;
    private boolean modificadoTelefonoEmail = false;
    private boolean modificadoContrasena = false;

    @FXML
    protected void onLoginButtonClick() {
        // Lógica para el botón de login
        String telefonoEmail = txtTelefonoEmail.getText();
        String password = passwordField.getText();
        System.out.println("Login clicked: " + telefonoEmail + " / " + password);

        // Verifica el login, por ejemplo, si las credenciales son correctas (esto es solo un ejemplo)
        if (!telefonoEmail.isEmpty() && password.equals("1234")) {
            // Si las credenciales son correctas, abrir la ventana HomeView

            // Cargar la nueva ventana (HomeView)
            try {
                // Cargar el archivo FXML de la vista HomeView
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pds/curiousmind/home-view.fxml"));
                Parent root = loader.load();

                // Crear una nueva escena con la vista HomeView
                Scene homeScene = new Scene(root,740, 580);

                // Obtener la ventana actual (stage)
                Stage currentStage = (Stage) togglePassword.getScene().getWindow();


                // Establecer la nueva escena
                currentStage.setScene(homeScene);

                // Establecer la posición deseada en la pantalla (X, Y)
                currentStage.setX(300);  // X = 500 píxeles desde la parte izquierda de la pantalla
                currentStage.setY(100);  // Y = 300 píxeles desde la parte superior de la pantalla

                // Mostrar la nueva ventana de Login
                currentStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si las credenciales son incorrectas, muestra un mensaje de error (opcional)
            System.out.println("Credenciales incorrectas");
        }
    }


    @FXML
    protected void onSignupButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pds/curiousmind/signup-view.fxml"));
            Parent root = loader.load();

            Scene homeScene = new Scene(root,740, 580);
            Stage currentStage = (Stage) togglePassword.getScene().getWindow();

            currentStage.setScene(homeScene);
            currentStage.setX(300);
            currentStage.setY(100);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Configuración inicial de los campos de texto
        txtTelefonoEmail.setText("Introduce tu teléfono o email...");
        //passwordField.setText("Introduce tu contraseña...");

        // Inicialmente, mostrar el TextField (que contiene el texto real) para la contraseña
        passwordTextField.setVisible(true);  // Hacemos visible el TextField
        passwordField.setVisible(false);     // Ocultamos el PasswordField

        txtTelefonoEmail.setStyle("-fx-text-fill: gray;");
        passwordTextField.setStyle("-fx-text-fill: gray;");

        // Configurar el botón para alternar la visibilidad de la contraseña
        togglePassword.setText("👁");

        // Foco en el campo de teléfono/email
        txtTelefonoEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (!modificadoTelefonoEmail) {
                    txtTelefonoEmail.setText("");
                }
            } else {
                if (txtTelefonoEmail.getText().isEmpty()) {
                    txtTelefonoEmail.setText("Introduce tu teléfono o email...");
                    modificadoTelefonoEmail = false;
                }
            }
        });

        // Foco en el campo de contraseña
        passwordTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Cuando el campo obtiene el foco, cambiamos a PasswordField
                passwordTextField.setVisible(false);  // Ocultamos el TextField
                passwordField.setVisible(true);  // Mostramos el PasswordField
            }
        });
    }

    private String originalPassword = "";  // Para guardar la contraseña original

    @FXML
    private void onTogglePasswordClick() {
        if (isPasswordVisible) {
            // Cambiar a modo oculto (mostrar puntos)
            passwordTextField.setVisible(false);  // Ocultamos el TextField
            passwordField.setVisible(true);  // Mostramos el PasswordField
            passwordField.setText(originalPassword);  // Restauramos el texto original en PasswordField
            togglePassword.setText("👁");  // Cambiar el icono a ojo cerrado
        } else {
            // Cambiar a modo visible (mostrar el texto real)
            originalPassword = passwordField.getText();  // Guardamos el texto original
            passwordField.setVisible(false);  // Ocultamos el PasswordField
            passwordTextField.setVisible(true);  // Mostramos el TextField
            passwordTextField.setText(originalPassword);  // Restauramos el texto original en TextField
            togglePassword.setText("🙈");  // Cambiar el icono a ojo abierto
        }
        isPasswordVisible = !isPasswordVisible;
    }

    // Actualizar la variable cuando se modifique el campo de teléfono/email
    @FXML
    private void onTelefonoEmailKeyTyped() {
        modificadoTelefonoEmail = true;
    }

    // Actualizar la variable cuando se modifique el campo de contraseña
    @FXML
    private void onPasswordKeyTyped() {
        modificadoContrasena = true;
    }
}
