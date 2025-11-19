package com.example.parcial3.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private StackPane contenedorCentro;
    @FXML private Button btnPacientes;
    @FXML private Button btnMedicos;
    @FXML private Button btnCitas;


    @FXML
    private  void initialize(){
        cargarVistaEnCentro("/com/example/views/Pacientes.fxml");
    }


    private void cargarVistaEnCentro(String fxmlRuta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlRuta));
            Parent vista = loader.load();

            Object controller = loader.getController();

            try {
                controller.getClass()
                        .getMethod("Cambiarventana", PrincipalController.class)
                        .invoke(controller, this);
            }  catch (Exception ignored) {}


            // Limpiar el StackPane y agregar la nueva vista
            contenedorCentro.getChildren().clear();

            vista.getStylesheets().clear();
            if (fxmlRuta.contains("Pacientes.fxml")) {
                vista.getStylesheets().add(getClass().getResource("/Styles/Pacientes.css").toExternalForm());
            } else if (fxmlRuta.contains("Medicos.fxml")) {
                vista.getStylesheets().add(getClass().getResource("/Styles/Medicos.css").toExternalForm());
            } else if (fxmlRuta.contains("Citas.fxml")) {
                vista.getStylesheets().add(getClass().getResource("/Styles/Citas.css").toExternalForm());
            }


            contenedorCentro.getChildren().add(vista);


        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la vista: " + e.getMessage(), e);
        }
    }


    @FXML
    private void onPacientes(){
        cargarVistaEnCentro("/com/example/views/Pacientes.fxml");
    }
    @FXML
    private void onMedicos(){
        cargarVistaEnCentro("/com/example/views/Medicos.fxml");
    }
    @FXML
    private void onCitas(){
        cargarVistaEnCentro("/com/example/views/Citas.fxml");
    }


}

