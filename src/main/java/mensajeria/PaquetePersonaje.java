package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;

public class PaquetePersonaje extends PaquetePeleable implements Serializable, Cloneable {


	protected String casta;

	private int destreza;
	private int inteligencia;
	private int experiencia;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public PaquetePersonaje() throws IOException {
		estado = Estado.estadoOffline;
	}
	
	
	public String getCasta() {
		return casta;
	}


	public void setCasta(String casta) {
		this.casta = casta;
	}


	public int getDestreza() {
		return destreza;
	}


	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}


	public int getInteligencia() {
		return inteligencia;
	}


	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}


	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
	
	public final void anadirItem(Item i) {
		items.add(i);
	}
	
	public final void removerItem(Item i) {
		items.remove(i);
	}

	public ArrayList<Item> getItems() {
		return new ArrayList<Item>(items);
	}
	
	public final void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public final int getItemID(int index) {
		return items.get(index).getIdItem();
	}
	
	public final void anadirItem(int idItem, String nombre, int wearLocation, int bonusSalud, int bonusEnergia, int bonusAtaque, int bonusDefensa, int bonusMagia, String foto, String fotoEquipado) {
		try {
			items.add(new Item(idItem,nombre,wearLocation,bonusSalud,bonusEnergia,bonusAtaque, bonusDefensa, bonusMagia, foto, fotoEquipado));
			useBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al añadir item");

		}
	}
	
	public final void removerBonus() {
		//Intente usar un iterator y por alguna razón no andaba..
		int i = 0;
		while(i < items.size()) {
			sacarBonus(items.get(i).getBonusSalud(),items.get(i).getBonusEnergia(),items.get(i).getBonusFuerza(), items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
			i++;
		}
	}
	public final  void sacarBonus(int bonusSalud, int bonusEnergia, int bonusAtaque, int bonusDefensa, int bonusMagia) {
		saludTope -= bonusSalud;
		energiaTope -= bonusEnergia;
		fuerza -= bonusAtaque;
		destreza -= bonusDefensa;
		inteligencia -= bonusMagia;
	}
	public final void ponerBonus() {
		//Intente usar un iterator y por alguna razón no andaba..
		int i = 0;
		while(i < items.size()) {
			useBonus(items.get(i).getBonusSalud(),items.get(i).getBonusEnergia(),items.get(i).getBonusFuerza(), items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
			i++;
		}
	}

	public void useBonus(int bonusSalud, int bonusEnergia, int bonusAtaque, int bonusDefensa, int bonusMagia) {
		saludTope += bonusSalud;
		energiaTope += bonusEnergia;
		fuerza += bonusAtaque;
		destreza += bonusDefensa;
		inteligencia += bonusMagia;
	}

	public int getCantItems() {
		return items.size();
	}

	public void anadirItem(int idItem) {
		try {
			items.add(new Item(idItem,null,0,0,0,0, 0, 0, null, null));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al añadir item");
		}
		
	}

	public Iterator<Item> getIterator() {
		// TODO Auto-generated method stub
		return items.iterator();
	}

	public void removerUltimoItem() {
		items.remove(items.size() -1);
		
	}
	
	public boolean nuevoItem() {
		return items.get(items.size()-1).getNombre() == null;
	}

	public void ponerBonus(int cantItems) {
		int i = 0;
		while(i < cantItems) {
			useBonus(items.get(i).getBonusSalud(),items.get(i).getBonusEnergia(),items.get(i).getBonusFuerza(), items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
			i++;
		}
	}

	public void sacarUltimoItem() {
		int i = items.size()-1;
		if(i>=0) {
			sacarBonus(items.get(i).getBonusSalud(),items.get(i).getBonusEnergia(),items.get(i).getBonusFuerza(), items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
		}
	}
	public void ponerUltimoItem() {
		int i = items.size()-1;
		if(i>=0) {
			useBonus(items.get(i).getBonusSalud(),items.get(i).getBonusEnergia(),items.get(i).getBonusFuerza(), items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
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
}
