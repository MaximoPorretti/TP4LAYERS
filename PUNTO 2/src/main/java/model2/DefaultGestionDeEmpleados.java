package model2;

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
    public void nuevoEmpleado(String text, String text1, String text2, LocalDate parse) {
        Empleado empleado = crearEmpleado(text, text1, text2, parse);
        if (casillaVacia(empleado) || yaExiste(text, text1,  text2,  parse)) {
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
    public boolean yaExiste(String text, String text1, String text2, LocalDate parse) {
        Empleado empleado = crearEmpleado(text, text1, text2, parse);
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
    private Empleado crearEmpleado(String text, String text1, String text2, LocalDate parse) {
        return new Empleado(text, text1, text2, parse);
    }
}
