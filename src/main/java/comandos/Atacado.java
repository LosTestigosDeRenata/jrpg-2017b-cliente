package comandos;

import estados.EstadoBatalla;
import mensajeria.PaquetePelear;

public class Atacado extends ComandosEscucha {

    @Override
    public void ejecutar() {
	PaquetePelear pa = gson.fromJson(cadenaLeida, PaquetePelear.class);

	juego.getEstadoBatalla().getEnemigo().actualizarAtributos(pa.getPersonajeAtacante(), pa.getCastaAtacante());
	juego.getEstadoBatalla().getPersonaje().actualizarAtributos(pa.getPersonajeEnemigo(), pa.getCastaEnemigo());

	EstadoBatalla.paquetePelear.setPersonajeAtacante(pa.getPersonajeEnemigo());
	EstadoBatalla.paquetePelear.setPersonajeEnemigo(pa.getPersonajeAtacante());
	EstadoBatalla.paquetePelear.setCastaAtacante(pa.getCastaEnemigo());
	EstadoBatalla.paquetePelear.setCastaEnemigo(pa.getCastaAtacante());

	EstadoBatalla.miTurno = true;
	EstadoBatalla.menuBatalla.setHabilitado(true);
    }

}
