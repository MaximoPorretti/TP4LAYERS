

import model2.NotificarUsuario;

import java.util.ArrayList;
import java.util.List;

public class fakeNotificador implements NotificarUsuario {
    public List<String> enviados = new ArrayList<>();

    @Override
    public void notificar(String destinatario, String asunto, String mensaje) {
        enviados.add(destinatario + ": " + asunto);
    }
}
