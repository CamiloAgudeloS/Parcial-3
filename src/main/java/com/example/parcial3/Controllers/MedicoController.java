package com.example.parcial3.Controllers;

import com.example.parcial3.Models.Medico;
import com.example.parcial3.Repositories.MedicoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicoController {

    @FXML
    private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtEdad;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtTarjetaProfesional;


    @FXML private TableView<Medico> tablaMedico;
    @FXML private TableColumn<Medico, String> colNombre;
    @FXML private TableColumn<Medico, String> colApellido;
    @FXML private TableColumn<Medico, String> colDocumento;
    @FXML private TableColumn<Medico, String> colEspecialidad;
    @FXML private TableColumn<Medico, String> colTarjetaProfesional;
    @FXML private TableColumn<Medico, String> colEdad;

    private MedicoRepository medicorepository;
    private ObservableList<Medico> listaMedicos;

    private Medico medicoseleccionado = null;

    @FXML
    public void initialize() {
        medicorepository = MedicoRepository.getInstancia();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colTarjetaProfesional.setCellValueFactory(new PropertyValueFactory<>("tarjetaProfesional"));


        // Cargar datos iniciales
        listaMedicos = FXCollections.observableArrayList(medicorepository.getMedicos());
        tablaMedico.setItems(listaMedicos);
        tablaMedico.setItems(listaMedicos);
        // Detectar selección
        tablaMedico.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> {
                    if (newSel != null) {
                        medicoseleccionado = newSel;
                        cargarCamposPaciente(newSel);
                    }
                }
        );
    }

    private void cargarCamposPaciente(Medico c) {
        txtNombre.setText(c.getNombre());
        txtApellido.setText(c.getApellido());
        txtDocumento.setText(c.getDocumento());
        txtEdad.setText(c.getEdad());
        txtEspecialidad.setText(c.getEspecialidad());
        txtTarjetaProfesional.setText(c.getTarjetaProfesional());
    }

    @FXML
    private void onGuardar() {
        if (!validarCampos()) return;

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String documento = txtDocumento.getText();
        String edad = txtDocumento.getText();
        String especialidad=txtEspecialidad.getText();
        String tarjetaProfesional= txtTarjetaProfesional.getText();

        // Evitar duplicados por documento
        if (medicorepository.buscarPorDocumento(documento) != null) {
            mostrarAlerta("Error", "Ya existe un Medico  con ese documento.", Alert.AlertType.ERROR);
            return;
        }

        Medico nuevo = new Medico(nombre, apellido, documento, edad,especialidad,tarjetaProfesional);

        medicorepository.registrarMedico(nuevo);
        listaMedicos.add(nuevo);
        limpiarCampos();

        mostrarAlerta("Éxito", "Cliente registrado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onActualizar() {
        if (medicoseleccionado == null) {
            mostrarAlerta("Error", "Seleccione un cliente para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        if (!validarCampos()) return;

        medicoseleccionado.setNombre(txtNombre.getText());
        medicoseleccionado.setApellido(txtApellido.getText());
        medicoseleccionado.setDocumento(txtDocumento.getText());
        medicoseleccionado.setEdad(txtEdad.getText());
        medicoseleccionado.setEspecialidad(txtEspecialidad.getText());
        medicoseleccionado.setTarjetaProfesional(txtTarjetaProfesional.getText());

        tablaMedico.refresh();
        limpiarCampos();
        medicoseleccionado = null;

        mostrarAlerta("Éxito", "Medico actualizado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onEliminar() {
        Medico seleccionado = tablaMedico.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Error", "Seleccione un medico para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        medicorepository.eliminarMedico(seleccionado);
        listaMedicos.remove(seleccionado);

        mostrarAlerta("Éxito", "Medico eliminado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onLimpiar() {
        limpiarCampos();
        tablaMedico.getSelectionModel().clearSelection();
        medicoseleccionado = null;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtDocumento.clear();
        txtEdad.clear();
        txtEspecialidad.clear();
        txtTarjetaProfesional.clear();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() ||
                txtApellido.getText().isEmpty() ||
                txtDocumento.getText().isEmpty() ||
                txtEdad.getText().isEmpty() ||
                txtEspecialidad.getText().isEmpty()||
                txtTarjetaProfesional.getText().isEmpty()) {

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
