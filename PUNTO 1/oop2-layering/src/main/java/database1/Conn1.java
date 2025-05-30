package database1;

import java.sql.Connection;
import java.sql.DriverManager;

class Conn1 {

    private final String username;
    private final String pwd;
    private String conn;
    private Connection connection;

    public Conn1(String conn, String username, String pwd) {
        this.conn = conn;
        this.username = username;
        this.pwd = pwd;
    }

    Connection open() {
        try {
            return DriverManager.getConnection(this.conn, username, pwd);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
