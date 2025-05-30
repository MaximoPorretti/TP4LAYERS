package ui2;


import model2.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmpleadoView extends JFrame {
    private final DefaultTableModel tableModel;

    public EmpleadoView(List<Empleado> empleados) {
        this.tableModel = new DefaultTableModel(new String[]{"Apellido", "Nombre", "Email", "Fecha Nacimiento"}, 0);
        setupUI();
        cargarEmpleados(empleados);
    }

    private void setupUI() {
        setTitle("Lista de Empleados");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setVisible(true);
    }

    private void cargarEmpleados(List<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            tableModel.addRow(new Object[]{
                    empleado.getApellido(),
                    empleado.getNombre(),
                    empleado.getEmail(),
                    empleado.getFecha_nacimiento()
            });
        }
    }
}