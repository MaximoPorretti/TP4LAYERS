package model;

import java.time.LocalDate;

public class Empleado {
    String apellido, nombre, email;
    LocalDate fecha_nacimiento;

    public Empleado(String apellido, String nombre, String email, LocalDate fecha_nacimiento) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getApellido() {
        return apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

}
