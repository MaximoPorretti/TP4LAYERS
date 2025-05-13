package database;

import model.Empleado;
import model.RegistroEmpleados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jdbcEmpleadosService implements RegistroEmpleados {

    private Conn conn;

    public jdbcEmpleadosService(String conn, String username, String pwd) {
        this.conn = new Conn(conn, username, pwd);
    }
@Override
    public void nuevoEmpleado(Empleado empleado) {
        try (var conexion = this.conn.open();
             var stmt = conexion.prepareStatement(
                     "INSERT INTO Empleados(apellido, nombre, fecha_nacimiento, email) " +
                             "VALUES(?,?,?,?)")) {
            stmt.setString(1, empleado.getApellido());
            stmt.setString(2, empleado.getNombre());
            stmt.setDate(3, java.sql.Date.valueOf(empleado.getFecha_nacimiento()));
            stmt.setString(4, empleado.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public List<Empleado> empleados() {
        final String SQL_TODOS_LOS_EMPLEADOS = "SELECT apellido, nombre, fecha_nacimiento, email FROM Empleados";
        var listaEmpleados = new ArrayList<Empleado>();
        try (var conexion = this.conn.open();
             var stmt = conexion.createStatement();
             var resultSet = stmt.executeQuery(SQL_TODOS_LOS_EMPLEADOS)) {
            while (resultSet.next()) {
                listaEmpleados.add(new Empleado(
                        resultSet.getString("apellido"),
                        resultSet.getString("nombre"),
                        resultSet.getString("email").trim(),
                        resultSet.getDate("fecha_nacimiento").toLocalDate()
                ));
            }
            return listaEmpleados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean yaExiste(Empleado empleado) {
        final String SQL_EXISTE_EMPLEADO = "SELECT COUNT(*) FROM Empleados WHERE apellido = ? AND nombre = ? AND email = ?";
        try (var conexion = this.conn.open();
             var stmt = conexion.prepareStatement(SQL_EXISTE_EMPLEADO)) {
            stmt.setString(1, empleado.getApellido());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getEmail());
            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}