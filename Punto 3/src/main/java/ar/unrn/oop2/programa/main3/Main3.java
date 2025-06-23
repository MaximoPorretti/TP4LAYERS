package ar.unrn.oop2.programa.main3;

import ar.unrn.oop2.programa.database3.ArchivoRepositorioConcursos;
import ar.unrn.oop2.programa.database3.ArchivoServicioInscripciones;
import ar.unrn.oop2.programa.model3.RepositorioConcursos;
import ar.unrn.oop2.programa.model3.ServicioDeInscripciones;
import ar.unrn.oop2.programa.ui3.RadioCompetition;

import javax.swing.*;

public class Main3 {
    public static void main(String[] args) {
        // Inicializar archivos de datos
        new SetUpDatabase("concursos.txt", "inscriptos.txt").inicializar();

        SwingUtilities.invokeLater(() -> {
            RepositorioConcursos repo = new ArchivoRepositorioConcursos("concursos.txt");
            ServicioDeInscripciones inscripciones = new ArchivoServicioInscripciones("inscriptos.txt");
            new RadioCompetition(repo, inscripciones); // Cargar la UI con los servicios de archivos
        });
    }
}
