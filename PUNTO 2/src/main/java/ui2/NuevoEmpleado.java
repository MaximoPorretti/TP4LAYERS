package ui2;
import model2.Empleado;
import model2.GestionDeUsuarios;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NuevoEmpleado extends JFrame {
    public NuevoEmpleado(GestionDeUsuarios servicio) {
        setTitle("Nuevo Empleado");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JTextField apellidoField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField fechaNacimientoField = new JTextField();

        add(new JLabel("Apellido:"));
        add(apellidoField);
        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Fecha Nacimiento (YYYY-MM-DD):"));
        add(fechaNacimientoField);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(e -> {
            try {

                servicio.nuevoEmpleado(apellidoField.getText(),
                        nombreField.getText(),
                        emailField.getText(),
                        LocalDate.parse(fechaNacimientoField.getText()));


                JOptionPane.showMessageDialog(this, "Empleado agregado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(new JLabel());
        add(agregarButton);

        setSize(400, 200);
        setVisible(true);
    }


}
