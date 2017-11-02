package mensajeria;

import java.io.Serializable;

/**
* clase serializable utilizada por el Chat. Se encarga de armar el paquete a enviar
*Se carga el Id del user que desea enviar el mensaje, el Id de quien va a recibirlo
*y el mensaje a enviar. Se envía por Gson.
*/
public class PaqueteMensaje extends Paquete implements Serializable, Cloneable {

    private String userEmisor;
    private String userReceptor;
    private String msj;

    public PaqueteMensaje() {
    }

    public String getMensaje() {
	return msj;
    }

    public void setMensaje(String mensaje) {
	this.msj = mensaje;
    }

    public String getUserEmisor() {
	return userEmisor;
    }

    public void setUserEmisor(String idEmisor) {
	this.userEmisor = idEmisor;
    }

    public String getUserReceptor() {
	return userReceptor;
    }

    public void setUserReceptor(String idReceptor) {
	this.userReceptor = idReceptor;
    }

    public Object clone() {
	Object obj = null;
	obj = super.clone();
	return obj;
    }
}
