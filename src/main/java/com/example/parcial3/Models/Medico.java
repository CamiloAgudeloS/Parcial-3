package com.example.parcial3.Models;

public class Medico extends Persona {
      private String TarjetaProfesional;
      private String especialidad;

    public Medico(String nombre, String apellido, String edad, String documento, String especialidad, String tarjetaProfesional) {
        super(nombre, apellido, edad, documento);
        this.especialidad = especialidad;
        TarjetaProfesional = tarjetaProfesional;
    }

    public String getTarjetaProfesional() {
        return TarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        TarjetaProfesional = tarjetaProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" + getNombre()+
                "especialidad='" + especialidad + '\'' +
                ", TarjetaProfesional='" + TarjetaProfesional + '\'' +
                '}';
    }
}
