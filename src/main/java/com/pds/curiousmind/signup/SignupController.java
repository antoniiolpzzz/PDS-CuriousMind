package com.pds.curiousmind.signup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField txtTelefonoEmail;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;  // Para la contraseña visible

    @FXML
    private Button togglePassword;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private TextField passwordTextField2;  // Para la contraseña visible

    @FXML
    private Button togglePassword2;

    private boolean isPasswordVisible = false;
    private boolean isPasswordVisible2 = false;
    private boolean modificadoNombre = false;
    private boolean modificadoTelefonoEmail = false;


    @FXML
    protected void onLoginButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pds/curiousmind/login-view.fxml"));
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
    protected void onCreateAccountButtonClick() {
        String nombre = name.getText();
        String telefonoEmail = txtTelefonoEmail.getText();
        String password = passwordField.getText();
        String password2 = passwordField2.getText();


        // Verifica el login, por ejemplo, si las credenciales son correctas (esto es solo un ejemplo)
        if (!telefonoEmail.isEmpty() && !password.isEmpty() && !password2.isEmpty() && password.equals(password2) && !nombre.isEmpty()) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pds/curiousmind/login-view.fxml"));
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
    private void initialize() {

        name.setText("Introduce tu nombre...");
        txtTelefonoEmail.setText("Introduce tu teléfono o email...");

        // Inicialmente, mostrar el TextField (que contiene el texto real) para la contraseña
        passwordTextField.setVisible(true);  // Hacemos visible el TextField
        passwordField.setVisible(false);     // Ocultamos el PasswordField

        passwordTextField2.setVisible(true);  // Hacemos visible el TextField
        passwordField2.setVisible(false);     // Ocultamos el PasswordField

        name.setStyle("-fx-text-fill: gray;");
        txtTelefonoEmail.setStyle("-fx-text-fill: gray;");
        passwordTextField.setStyle("-fx-text-fill: gray;");
        passwordTextField2.setStyle("-fx-text-fill: gray;");

        // Configurar el botón para alternar la visibilidad de la contraseña
        togglePassword.setText("👁");
        togglePassword2.setText("👁");

        // Foco en el campo de teléfono/email
        name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (!modificadoNombre) {
                    name.setText("");
                }
            } else {
                if (name.getText().isEmpty()) {
                    name.setText("Introduce tu teléfono o email...");
                    modificadoNombre = false;
                }
            }
        });

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

        passwordTextField2.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Cuando el campo obtiene el foco, cambiamos a PasswordField
                passwordTextField2.setVisible(false);  // Ocultamos el TextField
                passwordField2.setVisible(true);  // Mostramos el PasswordField
            }
        });
    }

    private String originalPassword = "";  // Para guardar la contraseña original
    private String originalPassword2 = "";  // Para guardar la contraseña original

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

    @FXML
    private void onTogglePasswordClick2() {
        if (isPasswordVisible2) {
            // Cambiar a modo oculto (mostrar puntos)
            passwordTextField2.setVisible(false);  // Ocultamos el TextField
            passwordField2.setVisible(true);  // Mostramos el PasswordField
            passwordField2.setText(originalPassword2);  // Restauramos el texto original en PasswordField
            togglePassword2.setText("👁");  // Cambiar el icono a ojo cerrado
        } else {
            // Cambiar a modo visible (mostrar el texto real)
            originalPassword2 = passwordField2.getText();  // Guardamos el texto original
            passwordField2.setVisible(false);  // Ocultamos el PasswordField
            passwordTextField2.setVisible(true);  // Mostramos el TextField
            passwordTextField2.setText(originalPassword2);  // Restauramos el texto original en TextField
            togglePassword2.setText("🙈");  // Cambiar el icono a ojo abierto
        }
        isPasswordVisible2 = !isPasswordVisible2;
    }


}
