package com.example.parcial3.Repositories;

import com.example.parcial3.Models.Paciente;

import java.util.ArrayList;

public class Pacienterepository {

    private static Pacienterepository instancia;
    private final ArrayList<Paciente> pacientes;

    private Pacienterepository() {
        pacientes = new ArrayList<>();
        cargarDatosEjemplo();
    }

    public static Pacienterepository getInstancia() {
        if (instancia == null) {
            instancia = new Pacienterepository();
        }
        return instancia;
    }

    // Datos de prueba
    private void cargarDatosEjemplo() {
        pacientes.add(new Paciente("Jesus","Gonzales","12","1048294983","10939593759","Sida"));


    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public boolean darAltaPaciente(Paciente paciente) {
        return pacientes.remove(paciente);
    }

    public Paciente buscarPorDocumento(String documento) {
        return pacientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    public int getPaciente() {
        return pacientes.size();
    }
}
