package com.example.parcial3.Repositories;

import com.example.parcial3.Models.Medico;

import java.util.ArrayList;

public class MedicoRepository {
    private static MedicoRepository instancia;
    private final ArrayList<Medico> medicos;

    private MedicoRepository() {
        medicos = new ArrayList<>();
        cargarDatosEjemplo();
    }

    public static MedicoRepository getInstancia() {
        if (instancia == null) {
            instancia = new MedicoRepository();
        }
        return instancia;
    }

    // Datos de prueba
    private void cargarDatosEjemplo() {
        medicos.add(new Medico("Carlos","Gonzales","45","1048294983","Cirujano","405496894"));


    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public void registrarMedico(Medico medico) {
        medicos.add(medico);
    }

    public boolean eliminarMedico(Medico medico) {
        return medicos.remove(medico);
    }

    public Medico buscarPorDocumento(String documento) {
        return medicos.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    public int getMedico() {
        return medicos.size();
    }
}
