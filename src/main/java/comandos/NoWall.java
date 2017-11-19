package comandos;

import javax.swing.JOptionPane;

import estados.Estado;
import mundo.Mundo;

/**
 * Comando del modo Dios : noclip Se activa y descativa con el mismo comando
 */
public class NoWall extends ComandosEscucha {

    @Override
    public void ejecutar() {
	if (Estado.modoGod == false) {
	    juego.getEstadoJuego().getMundoMundo().modoNoWallON();
	    Estado.modoGod = true;
	    JOptionPane.showMessageDialog(null, "A cruzar paredes !");
	} else {
	    juego.getEstadoJuego().getMundoMundo().mundoAGrafo();
	    Estado.modoGod = false;
	    JOptionPane.showMessageDialog(null, "Volvemos a la normalidad");
	}
    }

}
