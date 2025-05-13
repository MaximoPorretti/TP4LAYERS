package model;

import java.time.LocalDate;
import java.util.List;

public class DefaultGestionDeEmpleados implements GestionDeUsuarios {
    private final RegistroEmpleados registroEmpleados;
    private final NotificarUsuario notificador;

    public DefaultGestionDeEmpleados(RegistroEmpleados registroEmpleados, NotificarUsuario notificador) {
        this.registroEmpleados = registroEmpleados;
        this.notificador = notificador;
    }

    @Override
    public void nuevoEmpleado(Empleado empleado) {
        if (casillaVacia(empleado) || yaExiste(empleado)) {
            throw new RuntimeException("El empleado ya existe o le faltan datos.");
        }
        registroEmpleados.nuevoEmpleado(empleado);

        if (esCumpleaños(empleado)) {
            notificador.notificar(
                    empleado.getEmail(),
                    "Feliz Cumpleaños",
                    "Feliz cumpleaños " + empleado.getNombre()
            );
        }
    }

    @Override
    public List<Empleado> empleados() {
        return registroEmpleados.empleados();
    }

    @Override
    public boolean yaExiste(Empleado empleado) {
        return registroEmpleados.yaExiste(empleado);
    }

    private static boolean casillaVacia(Empleado empleado) {
        return empleado.getApellido() == null || empleado.getNombre() == null ||
                empleado.getEmail() == null || empleado.getFecha_nacimiento() == null;
    }

    private static boolean esCumpleaños(Empleado empleado) {
        LocalDate hoy = LocalDate.now();
        return hoy.getMonthValue() == empleado.getFecha_nacimiento().getMonthValue() &&
                hoy.getDayOfMonth() == empleado.getFecha_nacimiento().getDayOfMonth();
    }
}
