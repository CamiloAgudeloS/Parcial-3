package com.example.parcial3.Repositories;

import com.example.parcial3.Models.Cita;

import java.util.ArrayList;

public class CitaRepository {
    private static CitaRepository instancia;
    private final ArrayList<Cita> citas;

    private CitaRepository() {
        citas = new ArrayList<>();
    }

    public static CitaRepository getInstancia() {
        if (instancia == null) {
            instancia = new CitaRepository();
        }
        return instancia;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void agendarCita(Cita cita) {
        citas.add(cita);
    }

    public int totalCitas() {
        return citas.size();
    }

}
