package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Orco;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaquetePelear;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * Estado de batalla Puede ser contra otro cliente
 */
public class EstadoBatalla extends Estado {

    private static final int X_IMAGEN_RECURSO = 175;
    private static final int FRAME_ENEMIGO = 7;
    private static final int FRAME_PERSONAJE = 3;
    private static final int X_IMAGEN = 25;
    private static final int Y_IMAGEN = 5;
    private static final int Y_IMAGEN_ENEMY = 75;
    private static final int X_IMAGEN_ENEMY = 550;
    private static final int TAM_IMAGEN = 256;
    private static final int HABILIDAD_6 = 6;
    private static final int HABILIDAD_5 = Y_IMAGEN;
    private static final int HABILIDAD_4 = 4;
    private static final int HABLIDIAD_3 = 3;
	private static final int HABLIDIAD_2 = 2;
	private static final int HABLIDIAD_1 = 1;
    private static final int Y_OFFSET = 150;
    private static final int X_OFFSET = -350;
    private final Mundo mundo;
    private Personaje personaje;
    private Personaje enemigo;
    private int[] posMouse;
    public static PaquetePersonaje paquetePersonaje;
    public static PaquetePersonaje paqueteEnemigo;
    public static boolean miTurno;

    private boolean haySpellSeleccionada;
    private boolean seRealizoAccion;

    private final Gson gson = new Gson();

    private final BufferedImage miniaturaPersonaje;
    private final BufferedImage miniaturaEnemigo;

    public static MenuBatalla menuBatalla;
    public static PaquetePelear paquetePelear;

    /**
     * Instantiates a new estado batalla.
     * @param juego the juego
     * @param paqueteBatalla the paquete batalla
     */
    public EstadoBatalla(final Juego juego, final PaqueteBatalla paqueteBatalla) {
	super(juego);
	mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
	miTurno = paqueteBatalla.isMiTurno();

	paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
	paqueteEnemigo = juego.getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());

	crearPersonajes();

	menuBatalla = new MenuBatalla(miTurno, personaje);

	miniaturaEnemigo = Recursos.getPersonaje().get(enemigo.getNombreRaza()).get(Y_IMAGEN)[0];
	miniaturaPersonaje = Recursos.getPersonaje().get(personaje.getNombreRaza()).get(Y_IMAGEN)[0];

	// por defecto batalla perdida
	juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENUPERDERBATALLA);

	// limpio la accion del mouse
	juego.getHandlerMouse().setNuevoClick(false);

    }

    @Override
    public void actualizar() {

	juego.getCamara().setxOffset(X_OFFSET);
	juego.getCamara().setyOffset(Y_OFFSET);

	seRealizoAccion = false;
	haySpellSeleccionada = false;

	if (miTurno) {

	    if (juego.getHandlerMouse().getNuevoClick()) {
		posMouse = juego.getHandlerMouse().getPosMouse();

		if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == HABLIDIAD_1) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    paquetePelear.setAccion("hr1");
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == HABLIDIAD_2) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    paquetePelear.setAccion("hr2");
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == HABLIDIAD_3) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    paquetePelear.setAccion("hc1");
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == HABILIDAD_4) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    paquetePelear.setAccion("hc2");
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == HABILIDAD_5) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    paquetePelear.setAccion("hc3");
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == HABILIDAD_6) {
			seRealizoAccion = true;
			paquetePelear.setAccion("energizar");
			haySpellSeleccionada = true;
		    }
		}

		if (haySpellSeleccionada && seRealizoAccion) {
		    enviarAtaque(paquetePelear);
		} else if (haySpellSeleccionada && !seRealizoAccion) {
		    JOptionPane.showMessageDialog(null,
			    "No posees la energía suficiente para realizar esta habilidad.");
		}

		juego.getHandlerMouse().setNuevoClick(false);
	    }
	}

    }

    @Override
    public void graficar(final Graphics g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
	mundo.graficar(g);

	g.drawImage(Recursos.getPersonaje().get(paquetePersonaje.getRaza()).get(FRAME_PERSONAJE)[0], 0,
		X_IMAGEN_RECURSO, TAM_IMAGEN, TAM_IMAGEN, null);
	g.drawImage(Recursos.getPersonaje().get(paqueteEnemigo.getRaza()).get(FRAME_ENEMIGO)[0], X_IMAGEN_ENEMY,
		Y_IMAGEN_ENEMY, TAM_IMAGEN, TAM_IMAGEN, null);

	mundo.graficarObstaculos(g);
	menuBatalla.graficar(g);

	g.setColor(Color.GREEN);

	EstadoDePersonaje.dibujarEstadoDePersonaje(g, X_IMAGEN, Y_IMAGEN, personaje, miniaturaPersonaje);
	EstadoDePersonaje.dibujarEstadoDePersonaje(g, X_IMAGEN_ENEMY, Y_IMAGEN, enemigo, miniaturaEnemigo);

    }

    /**
     * Crear personajes.
     */
    private void crearPersonajes() {
	String raza = paquetePersonaje.getRaza();
	String nombre = paquetePersonaje.getNombre();
	int salud = paquetePersonaje.getSaludTope();
	int energia = paquetePersonaje.getEnergiaTope();
	int fuerza = (int) (paquetePersonaje.getFuerza() * paquetePersonaje.getMultiplicadorFuerzaCheat());
	int destreza = paquetePersonaje.getDestreza();
	int inteligencia = paquetePersonaje.getInteligencia();
	int experiencia = paquetePersonaje.getExperiencia();
	int nivel = paquetePersonaje.getNivel();
	int id = paquetePersonaje.getId();
	int saludTope = paquetePersonaje.getSaludTope();
	int energiaTope = paquetePersonaje.getEnergiaTope();

	ArrayList<String> personajeAtacante = new ArrayList<>();
	personajeAtacante.add(raza);
	personajeAtacante.add(nombre);
	personajeAtacante.add(String.valueOf(salud));
	personajeAtacante.add(String.valueOf(energia));
	personajeAtacante.add(String.valueOf(fuerza));
	personajeAtacante.add(String.valueOf(destreza));
	personajeAtacante.add(String.valueOf(inteligencia));
	personajeAtacante.add(String.valueOf(experiencia));
	personajeAtacante.add(String.valueOf(nivel));
	personajeAtacante.add(String.valueOf(id));
	personajeAtacante.add(String.valueOf(saludTope));
	personajeAtacante.add(String.valueOf(energiaTope));

	ArrayList<String> castaAtacante = new ArrayList<>();

	Casta casta = null;
	try {
	    casta = (Casta) Class.forName("dominio" + "." + paquetePersonaje.getCasta()).newInstance();
	    personaje = (Personaje) Class.forName("dominio" + "." + paquetePersonaje.getRaza())
		    .getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			    Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)
		    .newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
	    personaje.setPuntosSkill(paquetePersonaje.getPuntosSkill());

	    castaAtacante.add(casta.getClass().getSimpleName());
	    castaAtacante.add(String.valueOf(casta.getProbabilidadGolpeCritico()));
	    castaAtacante.add(String.valueOf(casta.getProbabilidadEvitarDanio()));
	    castaAtacante.add(String.valueOf(casta.getDanioCritico()));
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    JOptionPane.showMessageDialog(null, "Error al crear la batalla");
	}
	// cheats
	personaje.setInvulnerabilidad(paquetePersonaje.esInvulnerable());

	raza = paqueteEnemigo.getRaza();
	nombre = paqueteEnemigo.getNombre();
	salud = paqueteEnemigo.getSaludTope();
	energia = paqueteEnemigo.getEnergiaTope();
	fuerza = (int) (paqueteEnemigo.getFuerza() * paqueteEnemigo.getMultiplicadorFuerzaCheat());
	destreza = paqueteEnemigo.getDestreza();
	inteligencia = paqueteEnemigo.getInteligencia();
	experiencia = paqueteEnemigo.getExperiencia();
	nivel = paqueteEnemigo.getNivel();
	id = paqueteEnemigo.getId();
	saludTope = paqueteEnemigo.getSaludTope();
	energiaTope = paqueteEnemigo.getEnergiaTope();

	ArrayList<String> personajeEnemigo = new ArrayList<>();
	personajeEnemigo.add(raza);
	personajeEnemigo.add(nombre);
	personajeEnemigo.add(String.valueOf(salud));
	personajeEnemigo.add(String.valueOf(energia));
	personajeEnemigo.add(String.valueOf(fuerza));
	personajeEnemigo.add(String.valueOf(destreza));
	personajeEnemigo.add(String.valueOf(inteligencia));
	personajeEnemigo.add(String.valueOf(experiencia));
	personajeEnemigo.add(String.valueOf(nivel));
	personajeEnemigo.add(String.valueOf(id));
	personajeEnemigo.add(String.valueOf(saludTope));
	personajeEnemigo.add(String.valueOf(energiaTope));

	casta = null;
	if (paqueteEnemigo.getCasta().equals("Guerrero")) {
	    casta = new Guerrero();
	} else if (paqueteEnemigo.getCasta().equals("Hechicero")) {
	    casta = new Hechicero();
	} else if (paqueteEnemigo.getCasta().equals("Asesino")) {
	    casta = new Asesino();
	}

	ArrayList<String> castaEnemigo = new ArrayList<>();
	castaEnemigo.add(casta.getClass().getSimpleName());
	castaEnemigo.add(String.valueOf(casta.getProbabilidadGolpeCritico()));
	castaEnemigo.add(String.valueOf(casta.getProbabilidadEvitarDanio()));
	castaEnemigo.add(String.valueOf(casta.getDanioCritico()));

	if (paqueteEnemigo.getRaza().equals("Humano")) {
	    enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
	} else if (paqueteEnemigo.getRaza().equals("Orco")) {
	    enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
	} else if (paqueteEnemigo.getRaza().equals("Elfo")) {
	    enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
	}

	paquetePelear = new PaquetePelear(personajeAtacante, personajeEnemigo, castaAtacante, castaEnemigo, null);

	enemigo.setInvulnerabilidad(paqueteEnemigo.esInvulnerable());

	// si ambos personajes son invulnerables, los vuelvo a hacer vulnerables
	// en la batalla
	if (personaje.esInvulnerable() && enemigo.esInvulnerable()) {
	    personaje.setInvulnerabilidad(false);
	    enemigo.setInvulnerabilidad(false);
	}
    }

    /**
     * Enviar ataque.
     * @param paqueteAtacarParam the paquete atacar
     */
    public void enviarAtaque(final PaquetePelear paquetePelear) {
	try {
	    juego.getCliente().getSalida().writeObject(gson.toJson(paquetePelear));
	} catch (final IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
	}
    }

    /**
     * Finalizar batalla.
     */
    public void finalizarBatalla(final PaqueteFinalizarBatalla paqueteFinalizarBatalla) {
	try {
	    juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));
	    paquetePersonaje.setSaludTope(personaje.getSaludTope());
	    paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
	    paquetePersonaje.setExperiencia(personaje.getExperiencia());
	    paquetePersonaje.setNivel(personaje.getNivel());
	    paquetePersonaje.setPuntosSkill(personaje.getPuntosSkill());
	    paquetePersonaje.removerBonus();
	    paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
	    juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
	} catch (final IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
	}
    }

    /**
     * Gets the paquete personaje.
     * @return the paquete personaje
     */
    public PaquetePersonaje getPaquetePersonaje() {
	return paquetePersonaje;
    }

    /**
     * Gets the paquete enemigo.
     * @return the paquete enemigo
     */
    public PaquetePersonaje getPaqueteEnemigo() {
	return paqueteEnemigo;
    }

    /**
     * Sets the mi turno.
     * @param b the new mi turno
     */
    public void setMiTurno(final boolean b) {
	miTurno = b;
	menuBatalla.setHabilitado(b);
	juego.getHandlerMouse().setNuevoClick(false);
    }

    /**
     * Gets the personaje.
     * @return the personaje
     */
    public Personaje getPersonaje() {
	return personaje;
    }

    /**
     * Gets the enemigo.
     * @return the enemigo
     */
    public Personaje getEnemigo() {
	return enemigo;
    }

    /**
     * Informa de si el estado es EstadoJuego
     */
    @Override
    public boolean esEstadoDeJuego() {
	return false;
    }

    /**
     * Setea al personaje
     * @param personaje personaje
     */
    public void setPersonaje(final Personaje personaje) {
	this.personaje = personaje;
    }

    /**
     * Setea al enemigo
     * @param enemigo enemigo
     */
    public void setEnemigo(final Personaje enemigo) {
	this.enemigo = enemigo;
    }
}
