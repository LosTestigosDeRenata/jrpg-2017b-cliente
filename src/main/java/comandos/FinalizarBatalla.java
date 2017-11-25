package comandos;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

/**
 * Comando utilizado para finalizar la batalla entre usuario y npc o entre
 * usuario y otro usuario
 */
public class FinalizarBatalla extends ComandosEscucha {

    @Override
    public void ejecutar() {
	PaqueteFinalizarBatalla paqueteFinalizarBatalla = gson.fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);

	// Batalló contra un npc
	if (paqueteFinalizarBatalla.getIdEnemigo() < 0) {
	    // si ganó el humano
	    if (paqueteFinalizarBatalla.getGanadorBatalla() != paqueteFinalizarBatalla.getIdEnemigo() * -1) {
		juego.getNpcManager().despawnNpc(paqueteFinalizarBatalla.getIdEnemigo() * -1);
	    } else {
		juego.getPaquetesNpcs().get(paqueteFinalizarBatalla.getIdEnemigo() * -1)
			.setEstado(Estado.getEstadoJuego());
	    }

	} else {
	    // Batalló contra un humano
	    juego.getPersonaje().setEstado(Estado.getEstadoJuego());
	    Estado.setEstado(juego.getEstadoJuego());
	}

    }

}
