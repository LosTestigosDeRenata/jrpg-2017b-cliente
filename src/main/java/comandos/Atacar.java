package comandos;

import mensajeria.PaqueteAtacar;
/**
 * Comando para atacar
 */
public class Atacar extends ComandosEscucha {

    @Override
    public void ejecutar() {
	PaqueteAtacar paqueteAtacar = gson.fromJson(cadenaLeida, PaqueteAtacar.class);
	juego.getEstadoBatalla().getEnemigo().actualizarAtributos(paqueteAtacar.getMapPersonaje());
	juego.getEstadoBatalla().getPersonaje().actualizarAtributos(paqueteAtacar.getMapEnemigo());
	juego.getEstadoBatalla().setMiTurno(true);

    }

}
