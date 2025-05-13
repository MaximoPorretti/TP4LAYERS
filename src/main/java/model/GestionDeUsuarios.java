package model;

import java.util.List;

public interface GestionDeUsuarios {
    void nuevoEmpleado(Empleado empleado) ;

    List<Empleado> empleados();   // Implementación para agregar un nuevo empleado


    boolean yaExiste(Empleado empleado);
}
