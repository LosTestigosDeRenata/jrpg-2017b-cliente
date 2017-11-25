package mensajeria;

import java.io.Serializable;

/**
 * Crea un paquete con el nombre de usuario y su clave para ser enviada al server. Se utiliza
 *en el Menu de Inicio de Sesion. Se envia por Gson.
 */
public class PaqueteUsuario extends Paquete implements Serializable, Cloneable {

	private int idPj;
	private String username;
	private String password;
	private boolean inicioSesion;


	/**
	 * Constructor
	 */
	public PaqueteUsuario() {

	}

	/**
 * La variable booleana inicioSesion comienza en false y es seteada en true cuando un usuario se
 * loguear al juego
	 *
	 * @param pj parametro pj
	 * @param user parametro user
	 * @param pw parametro pw
	 */
	public PaqueteUsuario(final int pj, final String user, final String pw) {
		idPj = pj;
		username = user;
		password = pw;
		inicioSesion = false;
	}


	/**
	 * Retorna el id
	 *
	 * @return idPj retorna idpj
	 */
	public int getIdPj() {
		return idPj;
	}


	/**
	 * Setea el id
	 *
	 * @param idPj parametro idpj
	 */
	public void setIdPj(final int idPj) {
		this.idPj = idPj;
	}

	/**
	 * Devuelve el nombre de usuario
	 *
	 * @return username retorna el nombre de usuario
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Setea el nombre de usuario
	 *
	 * @param username parametro nombre de usuario
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Retorna el password
	 *
	 * @return password retorna el password
	 */

	public String getPassword() {
		return password;
	}


	/**
	 * Setea el password
	 *
	 * @param password parametro password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Verifica si es inicio de sesion
	 *
	 * @return inicioSesion retorna el inicio de sesion
	 */

	public boolean isInicioSesion() {
		return inicioSesion;
	}


	/**
	 * Setea el inicio de la sesion
	 *
	 * @param inicioSesion parametro inicioSesion
	 */
	public void setInicioSesion(final boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see mensajeria.Paquete#clone()
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}
