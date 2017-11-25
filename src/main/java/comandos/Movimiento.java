package comandos;

import mensajeria.PaqueteDeMovimientos;
/**
 * Comando para mover al usuario
 *
 */
public class Movimiento extends ComandosEscucha {

    @Override
    public void ejecutar() {
	PaqueteDeMovimientos pdm = gson.fromJson(cadenaLeida, PaqueteDeMovimientos.class);
	juego.setUbicacionPersonajes(pdm.getPersonajes());

    }

}
