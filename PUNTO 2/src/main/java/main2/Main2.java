package main2;

import database2.*;
import model2.*;
import notificacionEmail.*;
import ui2.MainView;


public class Main2 {
    public static final String CONNSTR = "jdbc:derby:memory:empleados;create=true";
    public static final String USERNAME = "app";
    public static final String PWD = "app";

    public static void main(String[] args) {
        var repo = new jdbcEmpleadosService(CONNSTR, USERNAME, PWD);
        var notificador = new EnvioEmail();
        var servicio = new DefaultGestionDeEmpleados(repo, notificador);

        var db = new SetUpDatabase(CONNSTR, USERNAME, PWD);
        db.inicializar();

        javax.swing.SwingUtilities.invokeLater(() -> new MainView( servicio));
    }
}