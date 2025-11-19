package com.example.parcial3.Controllers;

import com.example.parcial3.Models.Paciente;
import com.example.parcial3.Repositories.Pacienterepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PacienteController {

    @FXML
    private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtEdad;
    @FXML private TextField txtCarnet;
    @FXML private TextField txtEnfermedades;


    @FXML private TableView<Paciente> tablaPaciente;
    @FXML private TableColumn<Paciente, String> colNombre;
    @FXML private TableColumn<Paciente, String> colApellido;
    @FXML private TableColumn<Paciente, String> colDocumento;
    @FXML private TableColumn<Paciente, String> colEdad;
    @FXML private TableColumn<Paciente, String> colCarnet;
    @FXML private TableColumn<Paciente, String> colEnfermedades;

    private Pacienterepository pacienterepository;
    private ObservableList<Paciente> listaPacientes;

    private Paciente pacienteseleccionado = null;

    @FXML
    public void initialize() {
        pacienterepository = Pacienterepository.getInstancia();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colCarnet.setCellValueFactory(new PropertyValueFactory<>("carnetPaciente"));
        colEnfermedades.setCellValueFactory(new PropertyValueFactory<>("enfermedades"));


        // Cargar datos iniciales
        listaPacientes = FXCollections.observableArrayList(pacienterepository.getPacientes());
        tablaPaciente.setItems(listaPacientes);
        tablaPaciente.setItems(listaPacientes);
        // Detectar selección
        tablaPaciente.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> {
                    if (newSel != null) {
                        pacienteseleccionado = newSel;
                        cargarCamposPaciente(newSel);
                    }
                }
        );
    }

    private void cargarCamposPaciente(Paciente c) {
        txtNombre.setText(c.getNombre());
        txtApellido.setText(c.getApellido());
        txtDocumento.setText(c.getDocumento());
        txtEdad.setText(c.getEdad());
        txtCarnet.setText(c.getCarnetPaciente());
        txtEnfermedades.setText(c.getEnfermedades());
    }

    @FXML
    private void onGuardar() {
        if (!validarCampos()) return;

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String documento = txtDocumento.getText();
        String edad = txtDocumento.getText();
        String carnet=txtCarnet.getText();
        String enfermedades=txtEnfermedades.getText();

        // Evitar duplicados por documento
        if (pacienterepository.buscarPorDocumento(documento) != null) {
            mostrarAlerta("Error", "Ya existe un paciente con ese documento.", Alert.AlertType.ERROR);
            return;
        }

        Paciente nuevo = new Paciente(nombre, apellido, documento, edad,carnet,enfermedades);

        pacienterepository.registrarPaciente(nuevo);
        listaPacientes.add(nuevo);
        limpiarCampos();

        mostrarAlerta("Éxito", "Paciente registrado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onActualizar() {
        if (pacienteseleccionado == null) {
            mostrarAlerta("Error", "Seleccione un cliente para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        if (!validarCampos()) return;

        pacienteseleccionado.setNombre(txtNombre.getText());
        pacienteseleccionado.setApellido(txtApellido.getText());
        pacienteseleccionado.setDocumento(txtDocumento.getText());
        pacienteseleccionado.setEdad(txtEdad.getText());
        pacienteseleccionado.setCarnetPaciente(txtCarnet.getText());
        pacienteseleccionado.setEnfermedades(txtEnfermedades.getText());

        tablaPaciente.refresh();
        limpiarCampos();
        pacienteseleccionado = null;

        mostrarAlerta("Éxito", "Paciente  actualizado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onEliminar() {
        Paciente seleccionado = tablaPaciente.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Error", "Seleccione un paciente para dar de alta.", Alert.AlertType.WARNING);
            return;
        }

        pacienterepository.darAltaPaciente(seleccionado);
        listaPacientes.remove(seleccionado);

        mostrarAlerta("Éxito", "Paciente  dado de alrta correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onLimpiar() {
        limpiarCampos();
        tablaPaciente.getSelectionModel().clearSelection();
        pacienteseleccionado = null;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtDocumento.clear();
        txtEdad.clear();
        txtCarnet.clear();
        txtEnfermedades.clear();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() ||
                txtApellido.getText().isEmpty() ||
                txtDocumento.getText().isEmpty() ||
                txtEdad.getText().isEmpty()||
                 txtCarnet.getText().isEmpty()||
                txtEnfermedades.getText().isEmpty()){

            mostrarAlerta("Campos vacíos", "Todos los campos marcados con * son obligatorios.",
                    Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
