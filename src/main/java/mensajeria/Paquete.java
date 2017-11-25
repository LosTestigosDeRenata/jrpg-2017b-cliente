package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

import comandos.Nada;

/**
 * Clase utiilzada para identificar a que paquete se hace referencia.
 */
public class Paquete implements Serializable, Cloneable {

    public static String msjExito = "1";
    public static String msjFracaso = "0";

    private String mensaje;
    private String ip;
    private int comando;

    /**
     */
    public Paquete() {

    }

    /**
     * constructor parametrizado de la clase personaje
     * @param mensaje ** mensaje del paquete**
     * @param nick **nick del paquete**
     * @param ip **ip del paquete**
     * @param comando **comando del paquete**
     */
    public Paquete(final String mensaje, final String nick, final String ip, final int comando) {
	this.mensaje = mensaje;
	this.ip = ip;
	this.comando = comando;
    }

    /**
     * contructor parametrizado de la clase Personaeje
     * @param mensaje **mensaje del paquete**
     * @param comando **comando del paquete**
     */
    public Paquete(final String mensaje, final int comando) {
	this.mensaje = mensaje;
	this.comando = comando;
    }

    /**
     * contrcutor parametrizado de la clase Personaje
     * @param comando **comando del paquete**
     */
    public Paquete(final int comando) {
	this.comando = comando;
    }

    /**
     * setea el atributo mensaje
     * @param mensaje **mensaje para setear paquete**
     */
    public void setMensaje(final String mensaje) {
	this.mensaje = mensaje;
    }

    /**
     * setea el atributo IP
     * @param ip **ip para setear al paquete**
     */
    public void setIp(final String ip) {
	this.ip = ip;
    }

    /**
     * setea el atributo comando
     * @param comando **comando para setear al paquete**
     */
    public void setComando(final int comando) {
	this.comando = comando;
    }

    /**
     * devuelve una cadena con el mensaje
     * @return mensaje
     */
    public String getMensaje() {
	return mensaje;
    }

    /**
     * devuelve la IP
     * @return ip
     */
    public String getIp() {
	return ip;
    }

    /**
     * devuelve el atributo Comando
     * @return comando
     */
    public int getComando() {
	return comando;
    }

    @Override
    public Object clone() {
	Object obj = null;
	try {
	    obj = super.clone();
	} catch (final CloneNotSupportedException ex) {
	    JOptionPane.showMessageDialog(null, "Error al clonar");

	}
	return obj;
    }

    /**
     * devuelve un objeto Comando
     * @param nombrePaquete **nombre del paquete**
     * @return el comando del objeto, devuelve un comando vacío en caso de error
     */
    public Comando getObjeto(final String nombrePaquete) {
	try {
	    Comando c;
	    c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
	    return c;
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	    return new Nada();
	}

    }

    /**
     * devuelve un objeto Comando
     * @param nombrePaquete **nombre del paquete**
     * @param accion **comando a realizar**
     * @return el comando del objeto, devuelve un comando vacío en caso de error
     */
    public static Comando getObjetoSet(final String nombrePaquete, final int accion) {
	try {
	    Comando c;
	    c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
	    return c;
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	    return new Nada();
	}

    }

}