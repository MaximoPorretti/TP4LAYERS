
import model2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultGestionDeEmpleadosTest {

    private fakeRegistro registro;
    private fakeNotificador notificador;
    private GestionDeUsuarios gestion;

    @BeforeEach
    void setUp() {
        registro = new fakeRegistro();
        notificador = new fakeNotificador();
        gestion = new DefaultGestionDeEmpleados(registro, notificador);
    }

    @Test
    void seAgregaEmpleadoCorrectamente() {
        Empleado emp = new Empleado("Perez", "Ana", "ana@correo.com", LocalDate.now());
        gestion.nuevoEmpleado(emp);

        assertTrue(registro.empleados.contains(emp));
        assertTrue(notificador.enviados.contains("ana@correo.com: Feliz Cumpleaños"));
    }

    @Test
    void noSePuedeAgregarEmpleadoConDatosIncompletos() {
        Empleado emp = new Empleado(null, "Ana", "ana@correo.com", LocalDate.of(1990, 5, 15));
        assertThrows(RuntimeException.class, () -> gestion.nuevoEmpleado(emp));
    }

    @Test
    void noSePuedeAgregarEmpleadoDuplicado() {
        Empleado emp = new Empleado("Perez", "Ana", "ana@correo.com", LocalDate.of(1990, 5, 15));
        registro.empleados.add(emp);
        assertThrows(RuntimeException.class, () -> gestion.nuevoEmpleado(emp));
    }

    @Test
    void noSeNotificaSiNoEsCumpleanos() {
        Empleado emp = new Empleado("Lopez", "Carlos", "carlos@correo.com", LocalDate.of(1990, 1, 1));
        gestion.nuevoEmpleado(emp);
        assertTrue(registro.empleados.contains(emp));
        assertTrue(notificador.enviados.isEmpty());
    }
    @Test
    void seNotificaSiEsCumpleanos() {
        Empleado emp = new Empleado("Lopez", "Carlos", "carlos@correo.com", LocalDate.now());
        gestion.nuevoEmpleado(emp);
        assertTrue(registro.empleados.contains(emp));
        assertTrue(notificador.enviados.contains("carlos@correo.com: Feliz Cumpleaños"));
    }
    @Test
    void empleadosDevuelveListaCompleta() {
        Empleado emp1 = new Empleado("A", "B", "ab@mail.com", LocalDate.of(1980, 4, 10));
        Empleado emp2 = new Empleado("C", "D", "cd@mail.com", LocalDate.of(1985, 8, 20));

        registro.nuevoEmpleado(emp1);
        registro.nuevoEmpleado(emp2);

        List<Empleado> empleados = gestion.empleados();
        assertEquals(2, empleados.size());
        assertTrue(empleados.contains(emp1));
        assertTrue(empleados.contains(emp2));
    }

    @Test
    void yaExisteDevuelveTrueSiEmpleadoYaFueRegistrado() {
        Empleado emp = new Empleado("Perez", "Ana", "ana@correo.com", LocalDate.of(1990, 5, 15));
        registro.nuevoEmpleado(emp);
        assertTrue(gestion.yaExiste(emp));
    }

    @Test
    void yaExisteDevuelveFalseSiEmpleadoNoFueRegistrado() {
        Empleado emp = new Empleado("Perez", "Ana", "ana@correo.com", LocalDate.of(1990, 5, 15));
        assertFalse(gestion.yaExiste(emp));
    }
}
