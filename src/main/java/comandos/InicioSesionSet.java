package comandos;

import mensajeria.Comando;
/**
 * Comando para setear el comando iniciosesion
 *
 */
public class InicioSesionSet extends ComandosCliente {

    @Override
    public void ejecutar() {
	cliente.getPaqueteUsuario().setComando(Comando.INICIOSESION);
    }

}
