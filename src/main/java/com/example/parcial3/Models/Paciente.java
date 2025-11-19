package com.example.parcial3.Models;

public class Paciente extends Persona{
    private String carnetPaciente;
    private String enfermedades;

    public Paciente(String nombre, String apellido, String edad, String documento, String carnetPaciente, String enfermedades) {
        super(nombre, apellido, edad, documento);
        this.carnetPaciente =carnetPaciente;
        this.enfermedades=enfermedades;
    }

    public String getCarnetPaciente() {
        return carnetPaciente;
    }

    public void setCarnetPaciente(String carnetPaciente) {
        this.carnetPaciente = carnetPaciente;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    @Override
    public String toString() {
        return  getNombre()+
                "---"+carnetPaciente+"--"+enfermedades;

    }
}
