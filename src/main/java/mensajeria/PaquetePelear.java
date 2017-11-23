package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

public class PaquetePelear extends Paquete implements Serializable,Cloneable{
	
	ArrayList<String> personajeAtacante; // Es el atacante. //
	ArrayList<String> personajeEnemigo; //Es el atacado. //
	ArrayList<String> castaAtacante; 
	ArrayList<String> castaEnemigo;
	String accion;
	
	public PaquetePelear(ArrayList<String> personajeAtacante, ArrayList<String> personajeEnemigo, ArrayList<String> castaAtacante, ArrayList<String> castaEnemigo, String accion)
	{
		setComando(Comando.ATACAR);
		this.personajeAtacante = personajeAtacante;
		this.personajeEnemigo = personajeEnemigo;
		this.castaAtacante = castaAtacante;
		this.castaEnemigo = castaEnemigo;
		this.accion = accion;
	}
	
	public PaquetePelear(int comando, ArrayList<String> personajeAtacante, ArrayList<String> personajeEnemigo, ArrayList<String> castaAtacante, ArrayList<String> castaEnemigo, String accion)
	{
		setComando(comando);
		this.personajeAtacante = personajeAtacante;
		this.personajeEnemigo = personajeEnemigo;
		this.castaAtacante = castaAtacante;
		this.castaEnemigo = castaEnemigo;
		this.accion = accion;
	}
	
	public void agregarAPersonajeAtacante(String atributo)
	{
		personajeAtacante.add(atributo);
	}
	
	public void agregarAPersonajeEnemigo(String atributo)
	{
		personajeEnemigo.add(atributo);
	}
	
	public void agregarACastaAtacante(String atributo)
	{
		castaAtacante.add(atributo);
	}
	
	public void agregarACastaEnemigo(String atributo)
	{
		castaEnemigo.add(atributo);
	}

	public ArrayList<String> getPersonajeAtacante() {
		return personajeAtacante;
	}

	public void setPersonajeAtacante(ArrayList<String> personajeAtacante) {
		this.personajeAtacante = personajeAtacante;
	}

	public ArrayList<String> getPersonajeEnemigo() {
		return personajeEnemigo;
	}

	public void setPersonajeEnemigo(ArrayList<String> personajeEnemigo) {
		this.personajeEnemigo = personajeEnemigo;
	}

	public ArrayList<String> getCastaAtacante() {
		return castaAtacante;
	}

	public void setCastaAtacante(ArrayList<String> castaAtacante) {
		this.castaAtacante = castaAtacante;
	}

	public ArrayList<String> getCastaEnemigo() {
		return castaEnemigo;
	}

	public void setCastaEnemigo(ArrayList<String> castaEnemigo) {
		this.castaEnemigo = castaEnemigo;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

}
