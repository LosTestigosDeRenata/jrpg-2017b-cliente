package comandos;

import estados.Estado;
import mundo.Mundo;

/**
 * Comando del modo Dios : noclip 
 * Se activa y descativa con el mismo comando
 */
public class GodMod extends ComandosEscucha {

    @Override
    public void ejecutar() {
	if(Estado.modoGod == false){
	    juego.getEstadoJuego().getMundoMundo().modoGodOn();
	    Estado.modoGod = true;
	}else{
	    juego.getEstadoJuego().getMundoMundo().mundoAGrafo();
	}
    }

}
