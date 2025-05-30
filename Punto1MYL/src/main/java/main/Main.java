package main;


import ui.AgregarParticipante;
import java.awt.*;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AgregarParticipante();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}
