package comandos;

/**
 * Comando para atacar
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dominio.Casta;
import dominio.Personaje;
import estados.Estado;
import estados.EstadoBatalla;
import interfaz.MenuInfoPersonaje;
import mensajeria.Comando;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaquetePelear;

/**
 * Clase atacar Comando que efectua las acciones de la batalla en el cliente.
 */
public class Atacar extends ComandosEscucha {

    private static final int MULTIPLICADOR_EXP = 40;
    private static final int ID_RAZA = 0;
    private static final int ID_NOMBRE = 1;
    private static final int ID_SALUD = 2;
    private static final int ID_ENERGIA = 3;
    private static final int ID_FUERZA = 4;
    private static final int ID_DESTREZA = 5;
    private static final int ID_INTELIGENCIA = 6;
    private static final int ID_EXPERIENCIA = 7;
    private static final int ID_NIVEL = 8;
    private static final int ID_IDPERSONAJE = 9;
    private static final int ID_SALUDTOPE = 10;
    private static final int ID_ENERGIATOPE = 11;

    private static final int ID_NOMBRECASTA = 0;

    @Override
    public void ejecutar() {
	PaquetePelear pa = (PaquetePelear) gson.fromJson(cadenaLeida, PaquetePelear.class);

	Personaje personaje = null; // Atacante. //
	Personaje enemigo = null;
	Casta cPersonaje = null;
	Casta cEnemigo = null;

	ArrayList<String> personajeAtacante = pa.getPersonajeAtacante();
	ArrayList<String> castaAtacante = pa.getCastaAtacante();
	ArrayList<String> personajeEnemigo = pa.getPersonajeEnemigo();
	ArrayList<String> castaEnemigo = pa.getCastaEnemigo();

	/////////////////// Empiezo: ARMO LAS CASTAS DE LOS CONTENDIENTES.
	/////////////////// //////////////////////////
	String casta = castaAtacante.get(ID_NOMBRECASTA);
	String raza = personajeAtacante.get(ID_RAZA);
	String nombre = personajeAtacante.get(ID_NOMBRE);
	int salud = Integer.parseInt(personajeAtacante.get(ID_SALUD));
	int energia = Integer.parseInt(personajeAtacante.get(ID_ENERGIA));
	int fuerza = Integer.parseInt(personajeAtacante.get(ID_FUERZA));
	int destreza = Integer.parseInt(personajeAtacante.get(ID_DESTREZA));
	int inteligencia = Integer.parseInt(personajeAtacante.get(ID_INTELIGENCIA));
	int experiencia = Integer.parseInt(personajeAtacante.get(ID_EXPERIENCIA));
	int nivel = Integer.parseInt(personajeAtacante.get(ID_NIVEL));
	int id = Integer.parseInt(personajeAtacante.get(ID_IDPERSONAJE));
	int saludTope = Integer.parseInt(personajeAtacante.get(ID_SALUDTOPE));
	int energiaTope = Integer.parseInt(personajeAtacante.get(ID_ENERGIATOPE));

	try {
	    cPersonaje = (Casta) Class.forName("dominio" + "." + casta).newInstance();
	    personaje = (Personaje) Class.forName("dominio" + "." + raza)
		    .getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			    Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE)
		    .newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, cPersonaje, experiencia, nivel,
			    id, saludTope, energiaTope);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    JOptionPane.showMessageDialog(null, "Error al crear la batalla");
	}

	casta = castaEnemigo.get(ID_NOMBRECASTA);
	raza = personajeEnemigo.get(ID_RAZA);
	nombre = personajeEnemigo.get(ID_NOMBRE);
	salud = Integer.parseInt(personajeEnemigo.get(ID_SALUD));
	energia = Integer.parseInt(personajeEnemigo.get(ID_ENERGIA));
	fuerza = Integer.parseInt(personajeEnemigo.get(ID_FUERZA));
	destreza = Integer.parseInt(personajeEnemigo.get(ID_DESTREZA));
	inteligencia = Integer.parseInt(personajeEnemigo.get(ID_INTELIGENCIA));
	experiencia = Integer.parseInt(personajeEnemigo.get(ID_EXPERIENCIA));
	nivel = Integer.parseInt(personajeEnemigo.get(ID_NIVEL));
	id = Integer.parseInt(personajeEnemigo.get(ID_IDPERSONAJE));
	saludTope = Integer.parseInt(personajeEnemigo.get(ID_SALUDTOPE));
	energiaTope = Integer.parseInt(personajeEnemigo.get(ID_ENERGIATOPE));

	try {
	    cEnemigo = (Casta) Class.forName("dominio" + "." + casta).newInstance();
	    enemigo = (Personaje) Class.forName("dominio" + "." + raza)
		    .getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			    Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE)
		    .newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, cEnemigo, experiencia, nivel,
			    id, saludTope, energiaTope);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    JOptionPane.showMessageDialog(null, "Error al crear la batalla");
	}

	String accion = pa.getAccion();
	if (!enemigo.estaVivo()) {
	    juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENUGANARBATALLA);
	    if (personaje.ganarExperiencia(enemigo.getNivel() * MULTIPLICADOR_EXP)) {
		juego.getPersonaje().setNivel(personaje.getNivel());
		juego.getPersonaje().setPuntosSkill(personaje.getPuntosSkill());
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENUSUBIRNIVEL);
	    }
	    PaqueteFinalizarBatalla paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
	    paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
	    paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());
	    paqueteFinalizarBatalla.setGanadorBatalla(juego.getPersonaje().getId());
	    finalizarBatalla(paqueteFinalizarBatalla, personaje, enemigo);
	    Estado.setEstado(juego.getEstadoJuego());

	} else {
	    EstadoBatalla.paquetePelear.setPersonajeAtacante(personajeAtacante);
	    EstadoBatalla.paquetePelear.setPersonajeEnemigo(personajeEnemigo);
	    EstadoBatalla.paquetePelear.setCastaAtacante(castaAtacante);
	    EstadoBatalla.paquetePelear.setCastaEnemigo(castaEnemigo);

	    juego.getEstadoBatalla().getEnemigo().actualizarAtributos(pa.getPersonajeEnemigo(), pa.getCastaEnemigo());
	    juego.getEstadoBatalla().getPersonaje().actualizarAtributos(pa.getPersonajeAtacante(),
		    pa.getCastaAtacante());

	    EstadoBatalla.miTurno = false;
	    EstadoBatalla.menuBatalla.setHabilitado(false);
	}
    }

    public void finalizarBatalla(PaqueteFinalizarBatalla paqueteFinalizarBatalla, Personaje personaje,
	    Personaje enemigo) {
	try {
	    juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));
	    EstadoBatalla.paquetePersonaje.setSaludTope(personaje.getSaludTope());
	    EstadoBatalla.paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
	    EstadoBatalla.paquetePersonaje.setNivel(personaje.getNivel());
	    EstadoBatalla.paquetePersonaje.setExperiencia(personaje.getExperiencia());
	    EstadoBatalla.paquetePersonaje.setPuntosSkill(personaje.getPuntosSkill());
	    EstadoBatalla.paquetePersonaje.removerBonus();
	    EstadoBatalla.paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
	    juego.getCliente().getSalida().writeObject(gson.toJson(EstadoBatalla.paquetePersonaje));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
	}
    }

}
