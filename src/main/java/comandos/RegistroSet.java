package comandos;

import mensajeria.Comando;
/**
 * Comando para setear el comando registro
 *
 */
public class RegistroSet extends ComandosCliente {

    @Override
    public void ejecutar() {
	cliente.getPaqueteUsuario().setComando(Comando.REGISTRO);

    }

}
