package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase PaquetePelear Paquete necesario para llevar a cabo la batalla en el
 * servidor.
 */
public class PaquetePelear extends Paquete implements Serializable, Cloneable {

    private ArrayList<String> personajeAtacante; // Es el atacante. //
    private ArrayList<String> personajeEnemigo; // Es el atacado. //
    private ArrayList<String> castaAtacante;
    private ArrayList<String> castaEnemigo;
    private String accion;

    /**
     * Constructor parametrizado del paquete Pelear, por defecto tiene el
     * comando ATACAR
     * @param personajeAtacante lista de atributos del atacante
     * @param personajeEnemigo lista de atributos del enemigo
     * @param castaAtacante lista de atributos de la casta del atacante
     * @param castaEnemigo lista de atributos de la casta del enemigo
     * @param accion accion
     */
    public PaquetePelear(final ArrayList<String> personajeAtacante, final ArrayList<String> personajeEnemigo,
	    final ArrayList<String> castaAtacante, final ArrayList<String> castaEnemigo, final String accion) {
	setComando(Comando.ATACAR);
	this.personajeAtacante = personajeAtacante;
	this.personajeEnemigo = personajeEnemigo;
	this.castaAtacante = castaAtacante;
	this.castaEnemigo = castaEnemigo;
	this.accion = accion;
    }

    /**
     * Constructor parametrizado del paquete Pelear, pero permite especificar el
     * comando del paquete.
     * @param comando comando
     * @param personajeAtacante lista de atributos del atacante
     * @param personajeEnemigo lista de atributos del enemigo
     * @param castaAtacante lista de atributos de la casta del atacante
     * @param castaEnemigo lista de atributos de la casta del enemigo
     * @param accion accion
     */
    public PaquetePelear(final int comando, final ArrayList<String> personajeAtacante,
	    final ArrayList<String> personajeEnemigo, final ArrayList<String> castaAtacante,
	    final ArrayList<String> castaEnemigo, final String accion) {
	setComando(comando);
	this.personajeAtacante = personajeAtacante;
	this.personajeEnemigo = personajeEnemigo;
	this.castaAtacante = castaAtacante;
	this.castaEnemigo = castaEnemigo;
	this.accion = accion;
    }

    /**
     * Agrega un atributo a la lista de atributos del personaje atacante.
     * @param atributo atributo
     */
    public void agregarAPersonajeAtacante(final String atributo) {
	personajeAtacante.add(atributo);
    }

    /**
     * Agrega un atributo a la lista de atributos del personaje enemigo.
     * @param atributo atributo
     */
    public void agregarAPersonajeEnemigo(final String atributo) {
	personajeEnemigo.add(atributo);
    }

    /**
     * Agrega un atributo a la lista de atributos de la casta del atacante.
     * @param atributo atributo
     */
    public void agregarACastaAtacante(final String atributo) {
	castaAtacante.add(atributo);
    }

    /**
     * Agrega un atributo a la lista de atributos de la casta del enemigo.
     * @param atributo atributo
     */
    public void agregarACastaEnemigo(final String atributo) {
	castaEnemigo.add(atributo);
    }

    /**
     * Obtiene la lista de atributos del personaje atacante.
     * @return una lista de string con los atributos
     */
    public ArrayList<String> getPersonajeAtacante() {
	return personajeAtacante;
    }

    /**
     * Setea la lista de atributos del personaje atacante.
     * @param personajeAtacante lista de string con los atributos
     */
    public void setPersonajeAtacante(final ArrayList<String> personajeAtacante) {
	this.personajeAtacante = personajeAtacante;
    }

    /**
     * Obtiene la lista de atributos del personaje enemigo.
     * @return una lista de string con los atributos
     */
    public ArrayList<String> getPersonajeEnemigo() {
	return personajeEnemigo;
    }

    /**
     * Setea la lista de atributos del personaje enemigo.
     * @param personajeEnemigo lista de string con los atributos
     */
    public void setPersonajeEnemigo(final ArrayList<String> personajeEnemigo) {
	this.personajeEnemigo = personajeEnemigo;
    }

    /**
     * Obtiene la lista de atributos de la casta del atacante.
     * @return una lista de string con los atributos
     */
    public ArrayList<String> getCastaAtacante() {
	return castaAtacante;
    }

    /**
     * Setea la lista de atributos de la casta del atacante.
     * @param castaAtacante lista de string con los atributos
     */
    public void setCastaAtacante(final ArrayList<String> castaAtacante) {
	this.castaAtacante = castaAtacante;
    }

    /**
     * Obtiene la lista de atributos de la casta del enemigo.
     * @return una lista de string con los atributos
     */
    public ArrayList<String> getCastaEnemigo() {
	return castaEnemigo;
    }

    /**
     * Setea la lista de atributos de la casta del enemigo.
     * @param castaEnemigo lista de string con los atributos
     */
    public void setCastaEnemigo(final ArrayList<String> castaEnemigo) {
	this.castaEnemigo = castaEnemigo;
    }

    /**
     * Obtiene la acción.
     * @return devuelve la acción
     */
    public String getAccion() {
	return accion;
    }

    /**
     * Setea la acción.
     * @param accion accion
     */
    public void setAccion(final String accion) {
	this.accion = accion;
    }

}
