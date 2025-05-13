package main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDatabase {
    private final String username;
    private final String pwd;
    private String conn;

    public SetUpDatabase(String conn, String username, String pwd) {
        this.conn = conn;
        this.username = username;
        this.pwd = pwd;
    }

    public void inicializar() {
        try (var connection = DriverManager.getConnection(conn, username, pwd)) {
            var stmt = connection.createStatement();
            dropTableEmpleados(stmt);
            createTableEmpleados(stmt);
            insertSampleData(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dropTableEmpleados(Statement stmt) {
        try {
            stmt.executeUpdate("DROP TABLE Empleados");
        } catch (Exception e) {
            // No hacer nada si la tabla no existe
        }
    }

    private void createTableEmpleados(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE Empleados ("
                + "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                + "apellido VARCHAR(50), "
                + "nombre VARCHAR(50), "
                + "fecha_nacimiento DATE, "
                + "email VARCHAR(100))");
    }

    private void insertSampleData(Statement stmt) throws SQLException {
        stmt.executeUpdate("INSERT INTO Empleados(apellido, nombre, fecha_nacimiento, email) "
                + "VALUES('Perez', 'Juan', '1990-05-15', 'juan.perez@example.com')");
        stmt.executeUpdate("INSERT INTO Empleados(apellido, nombre, fecha_nacimiento, email) "
                + "VALUES('Gomez', 'Ana', '1985-03-22', 'ana.gomez@example.com')");
    }
}