

import model2.Empleado;
import model2.RegistroEmpleados;

import java.util.ArrayList;
import java.util.List;

    public class fakeRegistro implements RegistroEmpleados {
        public List<Empleado> empleados = new ArrayList<>();

        @Override
        public void nuevoEmpleado(Empleado empleado) {
            empleados.add(empleado);
        }

        @Override
        public List<Empleado> empleados() {
            return empleados;
        }

        @Override
        public boolean yaExiste(Empleado empleado) {
            return empleados.contains(empleado);
        }
    }


