package mensajeria;

import java.io.Serializable;

/**
 * Se encarga de armar el paquete que contiene la ubicacion del persona en el mapa.
 * Utilizada por la clase Juego para setear la ubicacion inicial del personaje. Se envia por Gson.
*/
public class PaqueteMovimiento extends Paquete implements Serializable, Cloneable {

    private int id;
    private float posX;
    private float posY;
    private int direccion;
    private int frame;

    public PaqueteMovimiento() {
	setComando(Comando.MOVIMIENTO);
    }

    public PaqueteMovimiento(int idPersonaje) {
	id = idPersonaje;
	setComando(Comando.MOVIMIENTO);
    }

    public PaqueteMovimiento(int idPersonaje, float posX, float posY) {
	this.id = idPersonaje;
	this.posX = posX;
	this.posY = posY;
	setComando(Comando.MOVIMIENTO);
    }

    public PaqueteMovimiento(int idPersonaje, float posX, float posY, int direccion) {
	this.id = idPersonaje;
	this.posX = posX;
	this.posY = posY;
	this.direccion = direccion;
	setComando(Comando.MOVIMIENTO);
    }

    public int getIdPersonaje() {
	return id;
    }

    public void setIdPersonaje(int idPersonaje) {
	this.id = idPersonaje;
    }

    public float getPosX() {
	return posX;
    }

    public void setPosX(float posX) {
	this.posX = posX;
    }

    public float getPosY() {
	return posY;
    }

    public void setPosY(float posY) {
	this.posY = posY;
    }

    public int getDireccion() {
	return direccion;
    }

    public void setDireccion(int direccion) {
	this.direccion = direccion;
    }

    public int getFrame() {
	return frame;
    }

    public void setFrame(int frame) {
	this.frame = frame;
    }

    @Override
    public Object clone() {
	Object obj = null;
	obj = super.clone();
	return obj;
    }
}
