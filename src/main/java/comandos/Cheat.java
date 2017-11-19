package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Comando;
import mensajeria.PaquetePersonaje;

public class Cheat extends ComandosEscucha {

    @Override
    public void ejecutar() {
	PaquetePersonaje nuevoPaquete = gson.fromJson(cadenaLeida, PaquetePersonaje.class);
	
	juego.getPersonajesConectados().remove(nuevoPaquete.getId());
	juego.getPersonajesConectados().put(nuevoPaquete.getId(), nuevoPaquete);
	
	if(nuevoPaquete.getId() == juego.getCliente().getPaquetePersonaje().getId())
	    juego.getCliente().setPaquetePersonaje(nuevoPaquete);
    }

}