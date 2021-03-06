package comandos;

import mensajeria.PaquetePersonaje;
/**
 * Comando para actualizar al personaje
 */

public class ActualizarPersonaje extends ComandosEscucha {

    @Override
    public void ejecutar() {
	PaquetePersonaje paquetePersonaje = gson.fromJson(cadenaLeida, PaquetePersonaje.class);

	juego.getPersonajesConectados().remove(paquetePersonaje.getId());
	juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);

	if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
	    juego.actualizarPersonaje();
	    juego.getEstadoJuego().actualizarPersonaje();
	    juego.getCliente().actualizarItems(paquetePersonaje);
	    juego.getCliente().actualizarPersonaje(juego.getPersonajesConectados().get(paquetePersonaje.getId()));

	}

    }

}
