package ar.unrn.oop2.programa.main3;

import java.io.FileWriter;
import java.io.PrintWriter;

public class SetUpDatabase {
    private final String rutaConcursos;
    private final String rutaInscriptos;

    public SetUpDatabase(String rutaConcursos, String rutaInscriptos) {
        this.rutaConcursos = rutaConcursos;
        this.rutaInscriptos = rutaInscriptos;
    }

    public void inicializar() {
        crearArchivoConcursos();
        crearArchivoInscriptos();
    }

    private void crearArchivoConcursos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaConcursos))) {
            pw.println("1, Concurso X, 2024-05-01, 2025-06-01");
            pw.println("2, Concurso Y, 2024-01-01, 2024-03-01");
            pw.println("3, Concurso Z, 2025-05-01, 2025-06-01");
        } catch (Exception e) {
            throw new RuntimeException("Error creando archivo de concursos", e);
        }
    }

    private void crearArchivoInscriptos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaInscriptos))) {
            pw.println("Young, Angus, 4444-898789, angus@acdc.com, 1");
        } catch (Exception e) {
            throw new RuntimeException("Error creando archivo de inscriptos", e);
        }
    }
}