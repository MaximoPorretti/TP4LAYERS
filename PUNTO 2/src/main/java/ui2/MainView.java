package ui2;


import model2.GestionDeUsuarios;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView(GestionDeUsuarios servicio) {
        setTitle("Gestión de Empleados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JButton verListaButton = new JButton("Ver Lista de Empleados");
        verListaButton.addActionListener(e -> new EmpleadoView(servicio.empleados()));

        JButton nuevoEmpleadoButton = new JButton("Crear Nuevo Empleado");
        nuevoEmpleadoButton.addActionListener(e -> new NuevoEmpleado(servicio));

        add(verListaButton);
        add(nuevoEmpleadoButton);

        setSize(300, 200);
        setVisible(true);
    }
}
