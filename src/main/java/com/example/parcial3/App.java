package com.example.parcial3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/views/Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/Principal.css")).toExternalForm());
        stage.setTitle("Parcial 3");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
