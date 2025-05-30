package model2;

import java.time.LocalDate;
import java.util.List;

public interface GestionDeUsuarios {
    void nuevoEmpleado(String text, String text1, String text2, LocalDate parse) ;

    List<Empleado> empleados();   // Implementación para agregar un nuevo empleado


    boolean yaExiste(String text, String text1, String text2, LocalDate parse);
}
