package com.example.parcial3.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private LocalDate fecha;
    private LocalTime hora;
    private double precio;
    private Medico medico;
    private Paciente paciente;

    public Cita(Medico medico, Paciente paciente, LocalDate fecha, LocalTime hora, double precio) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
