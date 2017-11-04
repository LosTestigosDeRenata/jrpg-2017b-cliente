package comandos;

import javax.swing.JOptionPane;

import mensajeria.PaquetePersonaje;
/**
 * Clase crear personaje
 */
public class CrearPersonaje extends ComandosCliente {

    @Override
    public void ejecutar() {
	JOptionPane.showMessageDialog(null, "Registro exitoso.");
	cliente.setPaquetePersonaje(gson.fromJson(cadenaLeida, PaquetePersonaje.class));
	cliente.getPaqueteUsuario().setInicioSesion(true);

    }

}
