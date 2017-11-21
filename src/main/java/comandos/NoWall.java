package comandos;


import estados.Estado;

/**
 * Comando del modo Dios : noclip Se activa y descativa con el mismo comando
 */
public class NoWall extends ComandosEscucha {

	@Override
	public void ejecutar() {
		if (!Estado.modoGod) {
			juego.getEstadoJuego().getMundoMundo().modoNoWallON();
			Estado.modoGod = true;
		} else {
			juego.getEstadoJuego().getMundoMundo().mundoAGrafo();
			Estado.modoGod = false;
		}
	}

}
