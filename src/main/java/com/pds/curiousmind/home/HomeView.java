package com.pds.curiousmind.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pds/curiousmind/home-view.fxml"));
        Scene scene = new Scene(loader.load(), 740, 580);
        stage.setX(300);  // X = 500 píxeles desde la parte izquierda de la pantalla
        stage.setY(100);  // Y = 300 píxeles desde la parte superior de la pantalla
        stage.setTitle("Curious Minds");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
