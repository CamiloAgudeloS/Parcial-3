package com.example.parcial3.Controllers;

import com.example.parcial3.Models.Cita;
import com.example.parcial3.Models.Medico;
import com.example.parcial3.Models.Paciente;
import com.example.parcial3.Repositories.CitaRepository;
import com.example.parcial3.Repositories.MedicoRepository;
import com.example.parcial3.Repositories.Pacienterepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CitaController {

    @FXML
    private Label lblFecha;
    @FXML private Label lblHora; // HORA
    @FXML private ComboBox<Paciente> cmbPaciente;   // Paciente
    @FXML private ComboBox<Medico> cmbMedico;    // Médico
    @FXML private TextField txtPrecio;

    @FXML private TableView<Cita> tablaCitas;
    @FXML private TableColumn<Cita, String> colFecha;
    @FXML private TableColumn<Cita, String> colPaciente;
    @FXML private TableColumn<Cita, String> colMedico;
    @FXML private TableColumn<Cita, Double> colPrecio;
    @FXML private TableColumn<Cita, String> colHora;

    private final CitaRepository citaRepo = CitaRepository.getInstancia();
    private final Pacienterepository pacienteRepo = Pacienterepository.getInstancia();
    private final MedicoRepository medicoRepo = MedicoRepository.getInstancia();

    private LocalDate fechaActual;
    private LocalTime horaActual;

    @FXML
    public void initialize() {

        // Fecha y lblHora por defecto
        fechaActual = LocalDate.now();
        horaActual = LocalTime.now().withSecond(0).withNano(0);

        lblFecha.setText(fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblHora.setText(horaActual.toString());

        cargarCombos();
        configurarTabla();
    }

    private void cargarCombos() {
        cmbPaciente.setItems(FXCollections.observableArrayList(
                pacienteRepo.getPacientes().stream()
                        .filter(p -> pacienteDisponible(p))
                        .toList()
        ));

        cmbMedico.setItems(FXCollections.observableArrayList(
                medicoRepo.getMedicos().stream()
                        .filter(m -> medicoDisponible(m))
                        .toList()
        ));
    }

    // Un paciente ya usado en una cita → no se muestra
    private boolean pacienteDisponible(Paciente p) {
        return citaRepo.getCitas().stream().noneMatch(c -> c.getPaciente().equals(p));
    }

    // Igual para el medico
    private boolean medicoDisponible(Medico m) {
        return citaRepo.getCitas().stream().noneMatch(c -> c.getMedico().equals(m));
    }

    private void configurarTabla() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        colMedico.setCellValueFactory(new PropertyValueFactory<>("medico"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaCitas.setItems(FXCollections.observableArrayList(citaRepo.getCitas()));
    }

    @FXML
    private void onAgendar() {
        Paciente p = cmbPaciente.getValue();
        Medico m = cmbMedico.getValue();
        String precioTxt = txtPrecio.getText();

        if (p == null || m == null || precioTxt.isEmpty()) {
            mostrarAlerta("Campos incompletos", "Debe completar todos los campos.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTxt);
        } catch (NumberFormatException e) {
            mostrarAlerta("Precio inválido", "El precio debe ser numérico.");
            return;
        }

        Cita cita = new Cita(
                m,p,fechaActual,horaActual,
                precio
        );

        citaRepo.agendarCita(cita);

        // Actualizar tabla y combos
        tablaCitas.getItems().add(cita);
        cargarCombos();
        txtPrecio.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

