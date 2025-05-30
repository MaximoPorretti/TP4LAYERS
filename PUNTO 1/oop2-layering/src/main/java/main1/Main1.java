package main1;
import database1.JdbcRegistroDeVentas;
import model1.DefaultEstacionDeServicio;
import model1.EstacionDeServicio;
import model1.TipoDeCombustible;
import model1.Venta;
import ui1.MainView1;

import java.util.List;

import static main2.Main.*;

public class Main1 {

    public static void main(String[] args) {
        inicializarDatabase();
        launchApplication();
    }

    private static void launchApplication() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JdbcRegistroDeVentas registroVentas = new JdbcRegistroDeVentas(CONNSTR, USERNAME, PWD);
                   EstacionDeServicio estacion = new DefaultEstacionDeServicio(registroVentas) {
                   };
                   new MainView1(estacion).launch();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static void inicializarDatabase() {
        var jdbc = new SetUpDatabase1(CONNSTR,
                USERNAME,
                PWD);
        jdbc.inicializar();
    }
}