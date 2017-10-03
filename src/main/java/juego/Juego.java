package juego;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import chat.MiChat;
import cliente.Cliente;
import cliente.EscuchaMensajes;
import dominio.Personaje;
import entidades.Entidad;
import estados.Estado;
import estados.EstadoBatalla;
import estados.EstadoJuego;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaqueteNpc;
import mensajeria.PaquetePersonaje;

public class Juego implements Runnable {

	private Pantalla pantalla;
	private final String NOMBRE;
	private final int ANCHO;
	private final int ALTO;

	private Thread hilo;
	private boolean corriendo;

	private BufferStrategy bs; // Estrategia para graficar mediante buffers (Primero se "grafica" en el/los buffer/s y finalmente en el canvas)
	private Graphics g;

	// Estados
	private Estado estadoJuego;
	private Estado estadoBatalla;
	private Estado estadoBatallaNpc;

	// HandlerMouse
	private HandlerMouse handlerMouse;

	// Camara
	private Camara camara;

	// Conexion
	private Cliente cliente;
	private EscuchaMensajes escuchaMensajes;
	
	// El player
	private PaquetePersonaje paquetePersonaje;
	private PaqueteMovimiento ubicacionPersonaje;
	
	// Personajes conectados
	private Map<Integer, PaquetePersonaje> personajesConectados;
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	
	// NPCs
	// hay 2 maps, uno para los paquetes que llevan toda la información de los npcs
	// y otro para las entidades, que se van a encargar de la posición y el graficado del npc
	// en la pantalla.
	private Map<Integer, Entidad> entidadesNpcs;
	private Map<Integer, PaqueteNpc> paquetesNpcs;

	private Map<String, MiChat> chatsActivos = new HashMap<>();


	private CargarRecursos cargarRecursos;

	public Juego(final String nombre, final int ancho, final int alto, Cliente cliente, PaquetePersonaje pp) {
		this.NOMBRE = nombre;
		this.ALTO = alto;
		this.ANCHO = ancho;
		this.cliente = cliente;
		this.paquetePersonaje = pp;

		// Inicializo árbol de npcs
		entidadesNpcs = new HashMap<Integer, Entidad>();
		paquetesNpcs = new HashMap<Integer, PaqueteNpc>();
		
		// Inicializo la ubicacion del personaje
		ubicacionPersonaje = new PaqueteMovimiento();
		ubicacionPersonaje.setIdPersonaje(paquetePersonaje.getId());
		ubicacionPersonaje.setFrame(0);
		ubicacionPersonaje.setDireccion(6);

		// Creo paquetes de los npcs
		paquetesNpcs.put(1, new PaqueteNpc(1, 100, 50, 10, 10, 10, 2, 25, "Lucas Videla", "Orco", "Guerrero"));
		paquetesNpcs.put(2, new PaqueteNpc(2, 200, 50, 10, 10, 10, 4, 50, "Lucas Videlason", "Orco", "Guerrero"));
		paquetesNpcs.put(3, new PaqueteNpc(3, 300, 50, 10, 10, 10, 6, 75, "Son of Lucas Videlason", "Orco", "Guerrero"));
		
		// Creo el escucha de mensajes
		escuchaMensajes = new EscuchaMensajes(this);
		escuchaMensajes.start();

		handlerMouse = new HandlerMouse();

		iniciar();

		cargarRecursos = new CargarRecursos(cliente);
		cargarRecursos.start();
	}

	public void iniciar() { // Carga lo necesario para iniciar el juego
		pantalla = new Pantalla(NOMBRE, ANCHO, ALTO, cliente);

		pantalla.getCanvas().addMouseListener(handlerMouse);

		camara = new Camara(this, 0, 0);

		Personaje.cargarTablaNivel();
	}

	private void actualizar() { // Actualiza los objetos y sus posiciones

		if (Estado.getEstado() != null) {
			Estado.getEstado().actualizar();
		}
	}

	private void graficar() { // Grafica los objetos y sus posiciones
		bs = pantalla.getCanvas().getBufferStrategy();
		if (bs == null) { // Seteo una estrategia para el canvas en caso de que no tenga una
			pantalla.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics(); // Permite graficar el buffer mediante g

		g.clearRect(0, 0, ANCHO, ALTO); // Limpiamos la pantalla

		// Graficado de imagenes
		g.setFont(new Font("Book Antiqua",1,15));

		if (Estado.getEstado() != null) {
			Estado.getEstado().graficar(g);
		}

		// Fin de graficado de imagenes

		bs.show(); // Hace visible el próximo buffer disponible
		g.dispose();
	}

	@Override
	public void run() { // Hilo principal del juego

		int fps = 60; // Cantidad de actualizaciones por segundo que se desean
		double tiempoPorActualizacion = 1000000000 / fps; // Cantidad de nanosegundos en FPS deseados
		double delta = 0;
		long ahora;
		long ultimoTiempo = System.nanoTime();
		long timer = 0; // Timer para mostrar fps cada un segundo
		int actualizaciones = 0; // Cantidad de actualizaciones que se realizan realmente

		while (corriendo) {
			ahora = System.nanoTime();
			delta += (ahora - ultimoTiempo) / tiempoPorActualizacion; // Calculo  para determinar cuando realizar la actualizacion y el graficado
			timer += ahora - ultimoTiempo; // Sumo el tiempo transcurrido hasta que se acumule 1 segundo y mostrar los FPS
			ultimoTiempo = ahora; // Para las proximas corridas del bucle

			if (delta >= 1) {
				actualizar();
				graficar();
				actualizaciones++;
				delta--;
			}

			if (timer >= 1000000000) { // Si paso 1 segundo muestro los FPS
				pantalla.getFrame().setTitle(NOMBRE + " | " + "FPS: " + actualizaciones);
				actualizaciones = 0;
				timer = 0;
			}
		}

		stop();
	}

	public synchronized void start() { // Inicia el juego
		if (corriendo)
			return;

		estadoJuego = new EstadoJuego(this);
		Estado.setEstado(estadoJuego);
		pantalla.mostrar();
		corriendo = true;
		hilo = new Thread(this);
		hilo.start();
	}

	public synchronized void stop() { // Detiene el juego
		if (!corriendo)
			return;
		try {
			corriendo = false;
			hilo.join();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Fallo al intentar detener el juego.");
		}
	}

	public int getAncho() {
		return ANCHO;
	}

	public int getAlto() {
		return ALTO;
	}

	public HandlerMouse getHandlerMouse() {
		return handlerMouse;
	}

	public Camara getCamara() {
		return camara;
	}

	public EstadoJuego getEstadoJuego() {
		return (EstadoJuego) estadoJuego;
	}

	public EstadoBatalla getEstadoBatalla(){
		return (EstadoBatalla) estadoBatalla;
	}

	public void setEstadoBatalla(EstadoBatalla estadoBatalla){
		this.estadoBatalla = estadoBatalla;
	}

	public Estado getEstadoBatallaNpc()
	{
		return estadoBatallaNpc;
	}

	public void setEstadoBatallaNpc(Estado estadoBatallaNpc)
	{
		this.estadoBatallaNpc = estadoBatallaNpc;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public EscuchaMensajes getEscuchaMensajes() {
		return escuchaMensajes;
	}

	public PaquetePersonaje getPersonaje() {
		return paquetePersonaje;
	}

	public PaqueteMovimiento getUbicacionPersonaje(){
		return ubicacionPersonaje;
	}

	public void setPersonaje(PaquetePersonaje paquetePersonaje) {
		this.paquetePersonaje = paquetePersonaje;
	}

	public void actualizarPersonaje() {
		paquetePersonaje = (PaquetePersonaje) (personajesConectados.get(paquetePersonaje.getId()).clone());
	}

	public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
		return personajesConectados;
	}

	public void setPersonajesConectados(Map<Integer, PaquetePersonaje> map) {
		this.personajesConectados = map;
	}
	
	public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
		return ubicacionPersonajes;
	}

	public void setUbicacionPersonajes(Map<Integer, PaqueteMovimiento> ubicacionPersonajes) {
		this.ubicacionPersonajes = ubicacionPersonajes;
	}

	public Map<String, MiChat> getChatsActivos() {
		return chatsActivos;
	}
	
	public Map<Integer, Entidad> getNpcs()
	{
		return entidadesNpcs;
	}

	public void setNpcs(Map<Integer, Entidad> npcs)
	{
		this.entidadesNpcs = npcs;
	}

	public Map<Integer, PaqueteNpc> getPaquetesNpcs()
	{
		return paquetesNpcs;
	}

	public void setUbicacionNpcs(Map<Integer, PaqueteNpc> paquetesNpcs)
	{
		this.paquetesNpcs = paquetesNpcs;
	}
}
