package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;
import sun.text.resources.cldr.es.FormatData_es_EC;

/**
 * Clase para setear las caracteristicas del personaje. Se envia por Gson. Incluye un ArrayList
 * de Items que son actualizables desde el server a travez de consultas a la base de datos
 */
public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {


	private int id;
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel = 1;
	private int experiencia;
	private int puntosSkill = 0;

	private ArrayList<Item> items = new ArrayList<Item>();
	
	public PaquetePersonaje() throws IOException {
		estado = Estado.estadoOffline;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getMapa(){
		return idMapa;
	}

	public void setMapa(int mapa){
		idMapa = mapa;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCasta() {
		return casta;
	}


	public void setCasta(String casta) {
		this.casta = casta;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRaza() {
		return raza;
	}


	public void setRaza(String raza) {
		this.raza = raza;
	}

    public void sacarUltimoItem() {
	int i = items.size() - 1;
	if (i >= 0) {
	    sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
		    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
	}
    }

    public void ponerUltimoItem() {
	int i = items.size() - 1;
	if (i >= 0) {
	    useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
		    items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
	}
    }

    public void eliminarItems() {
	items.removeAll(items);
    }

    public void actualizarTrueque(ArrayList<Item> items) {
	this.items.removeAll(this.items);
	for (Item item : items) {
	    this.items.add(item);
	}
    }

    public int getPuntosSkill() {
	return puntosSkill;
    }

    public void setPuntosSkill(int puntosSkill) {
	this.puntosSkill = puntosSkill;
    }
}
