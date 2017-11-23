package comandos;

	import mensajeria.PaqueteAtacar;
	/**
	 * Comando para atacar
	 */
	import java.io.IOException;
	import java.util.ArrayList;
	
	import javax.swing.JOptionPane;
	
	import dominio.Asesino;
	import dominio.Casta;
	import dominio.Elfo;
	import dominio.Guerrero;
	import dominio.Hechicero;
	import dominio.Humano;
	import dominio.Orco;
	import dominio.Personaje;
	import estados.Estado;
	import interfaz.MenuInfoPersonaje;
	import mensajeria.Comando;
	import mensajeria.PaqueteAtacar;
	//import mensajeria.PaqueteAtacar;
	import mensajeria.PaqueteFinalizarBatalla;
	import mensajeria.PaquetePelear;
	
	import estados.EstadoBatalla;
	
	public class Atacar extends ComandosEscucha {
		
		@Override
		public void ejecutar()
		{
			PaquetePelear pa = (PaquetePelear) gson.fromJson(cadenaLeida,  PaquetePelear.class);
			
			
			Personaje personaje = null; // Atacante. //
			Personaje enemigo = null;
			Casta cPersonaje = null;
			Casta cEnemigo = null;
			
			
			ArrayList<String> personajeAtacante = pa.getPersonajeAtacante();
			ArrayList<String> castaAtacante = pa.getCastaAtacante();
			ArrayList<String> personajeEnemigo = pa.getPersonajeEnemigo();
			ArrayList<String> castaEnemigo = pa.getCastaEnemigo();
			
			/////////////////// Empiezo: ARMO LAS CASTAS DE LOS CONTENDIENTES. //////////////////////////
			String tipoDeCasta = castaAtacante.get(0);
			if(tipoDeCasta.equals("Guerrero"))
			{
				cPersonaje = new Guerrero(Double.parseDouble(castaAtacante.get(1)), Double.parseDouble(castaAtacante.get(2)), Double.parseDouble(castaAtacante.get(3)));
			}
			if(tipoDeCasta.equals("Hechicero"))
			{
				cPersonaje = new Hechicero(Double.parseDouble(castaAtacante.get(1)), Double.parseDouble(castaAtacante.get(2)), Double.parseDouble(castaAtacante.get(3)));
			}
			if(tipoDeCasta.equals("Asesino"))
			{
				cPersonaje = new Asesino(Double.parseDouble(castaAtacante.get(1)), Double.parseDouble(castaAtacante.get(2)), Double.parseDouble(castaAtacante.get(3)));
			}
			
			
			tipoDeCasta = castaEnemigo.get(0);
			if(tipoDeCasta.equals("Guerrero"))
			{
				cEnemigo = new Guerrero(Double.parseDouble(castaEnemigo.get(1)), Double.parseDouble(castaEnemigo.get(2)), Double.parseDouble(castaEnemigo.get(3)));
			}
			if(tipoDeCasta.equals("Hechicero"))
			{
				cEnemigo = new Hechicero(Double.parseDouble(castaEnemigo.get(1)), Double.parseDouble(castaEnemigo.get(2)), Double.parseDouble(castaEnemigo.get(3)));
			}
			if(tipoDeCasta.equals("Asesino"))
			{
				cEnemigo = new Asesino(Double.parseDouble(castaEnemigo.get(1)), Double.parseDouble(castaEnemigo.get(2)), Double.parseDouble(castaEnemigo.get(3)));
			}
			/////////////////// Fin: ARMO LAS CASTAS DE LOS CONTENDIENTES. //////////////////////////
			
			/////////////////// Empiezo: ARMO LOS PERSONAJES CONTENDIENTES. //////////////////////////
			String raza = personajeAtacante.get(0);
			if(raza.equals("Humano"))
			{
				personaje = new Humano(personajeAtacante.get(1), Integer.parseInt(personajeAtacante.get(2)), Integer.parseInt(personajeAtacante.get(3)), Integer.parseInt(personajeAtacante.get(4)), Integer.parseInt(personajeAtacante.get(5)), Integer.parseInt(personajeAtacante.get(6)), cPersonaje, Integer.parseInt(personajeAtacante.get(7)), Integer.parseInt(personajeAtacante.get(8)), Integer.parseInt(personajeAtacante.get(9)), Integer.parseInt(personajeAtacante.get(10)), Integer.parseInt(personajeAtacante.get(11)));
			}
			if(raza.equals("Orco"))
			{
				personaje = new Orco(personajeAtacante.get(1), Integer.parseInt(personajeAtacante.get(2)), Integer.parseInt(personajeAtacante.get(3)), Integer.parseInt(personajeAtacante.get(4)), Integer.parseInt(personajeAtacante.get(5)), Integer.parseInt(personajeAtacante.get(6)), cPersonaje, Integer.parseInt(personajeAtacante.get(7)), Integer.parseInt(personajeAtacante.get(8)), Integer.parseInt(personajeAtacante.get(9)), Integer.parseInt(personajeAtacante.get(10)), Integer.parseInt(personajeAtacante.get(11)));
			}
			if(raza.equals("Elfo"))
			{
				personaje = new Elfo(personajeAtacante.get(1), Integer.parseInt(personajeAtacante.get(2)), Integer.parseInt(personajeAtacante.get(3)), Integer.parseInt(personajeAtacante.get(4)), Integer.parseInt(personajeAtacante.get(5)), Integer.parseInt(personajeAtacante.get(6)), cPersonaje, Integer.parseInt(personajeAtacante.get(7)), Integer.parseInt(personajeAtacante.get(8)), Integer.parseInt(personajeAtacante.get(9)), Integer.parseInt(personajeAtacante.get(10)), Integer.parseInt(personajeAtacante.get(11)));
			}
			
			
			raza = personajeEnemigo.get(0);
			if(raza.equals("Humano"))
			{
				enemigo = new Humano(personajeEnemigo.get(1), Integer.parseInt(personajeEnemigo.get(2)), Integer.parseInt(personajeEnemigo.get(3)), Integer.parseInt(personajeEnemigo.get(4)), Integer.parseInt(personajeEnemigo.get(5)), Integer.parseInt(personajeEnemigo.get(6)), cPersonaje, Integer.parseInt(personajeEnemigo.get(7)), Integer.parseInt(personajeEnemigo.get(8)), Integer.parseInt(personajeEnemigo.get(9)), Integer.parseInt(personajeEnemigo.get(10)), Integer.parseInt(personajeEnemigo.get(11)));
			}
			if(raza.equals("Orco"))
			{
				enemigo = new Orco(personajeEnemigo.get(1), Integer.parseInt(personajeEnemigo.get(2)), Integer.parseInt(personajeEnemigo.get(3)), Integer.parseInt(personajeEnemigo.get(4)), Integer.parseInt(personajeEnemigo.get(5)), Integer.parseInt(personajeEnemigo.get(6)), cPersonaje, Integer.parseInt(personajeEnemigo.get(7)), Integer.parseInt(personajeEnemigo.get(8)), Integer.parseInt(personajeEnemigo.get(9)), Integer.parseInt(personajeEnemigo.get(10)), Integer.parseInt(personajeEnemigo.get(11)));
			}
			if(raza.equals("Elfo"))
			{
				enemigo = new Elfo(personajeEnemigo.get(1), Integer.parseInt(personajeEnemigo.get(2)), Integer.parseInt(personajeEnemigo.get(3)), Integer.parseInt(personajeEnemigo.get(4)), Integer.parseInt(personajeEnemigo.get(5)), Integer.parseInt(personajeEnemigo.get(6)), cPersonaje, Integer.parseInt(personajeEnemigo.get(7)), Integer.parseInt(personajeEnemigo.get(8)), Integer.parseInt(personajeEnemigo.get(9)), Integer.parseInt(personajeEnemigo.get(10)), Integer.parseInt(personajeEnemigo.get(11)));
			}
			/////////////////// Fin: ARMO LOS PERSONAJES CONTENDIENTES. //////////////////////////
			
			
			String accion = pa.getAccion();
			
			
	
			if (!enemigo.estaVivo()) {
				juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENUGANARBATALLA);
				if(personaje.ganarExperiencia(enemigo.getNivel() * 40)){
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
				//paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), paqueteEnemigo.getId(), personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), enemigo.getEnergia(), personaje.getDefensa(), enemigo.getDefensa(), personaje.getCasta().getProbabilidadEvitarDanio(), enemigo.getCasta().getProbabilidadEvitarDanio());
				//enviarAtaque(paqueteAtacar);
				
				EstadoBatalla.paquetePelear.setPersonajeAtacante(personajeAtacante);
				EstadoBatalla.paquetePelear.setPersonajeEnemigo(personajeEnemigo);
				EstadoBatalla.paquetePelear.setCastaAtacante(castaAtacante);
				EstadoBatalla.paquetePelear.setCastaEnemigo(castaEnemigo);
				
				
				juego.getEstadoBatalla().getEnemigo().actualizarAtributos(pa.getPersonajeEnemigo(), pa.getCastaEnemigo());
				juego.getEstadoBatalla().getPersonaje().actualizarAtributos(pa.getPersonajeAtacante(), pa.getCastaAtacante());
				
				
				
				
				
				EstadoBatalla.miTurno = false;
				EstadoBatalla.menuBatalla.setHabilitado(false);
			}
		}
		
		/*
		@Override
		public void ejecutar()
		{
			PaquetePelear paquetePelear = (PaquetePelear)gson.fromJson(cadenaLeida, PaquetePelear.class);
			
			Object tratamientoDePersonaje = paquetePelear.getPersonaje();
			Object tratamientoDeEnemigo = paquetePelear.getEnemigo();
			Personaje personaje;
			Personaje enemigo;
			
			personaje = (Humano)tratamientoDePersonaje; //Por defecto es Humano. En caso que no, entrara en los siguiente if's. //
			if(tratamientoDePersonaje instanceof Orco)
			{
				personaje = (Orco)tratamientoDePersonaje;
			}
			if(tratamientoDePersonaje instanceof Elfo)
			{
				personaje = (Elfo)tratamientoDePersonaje;
			}
			
			enemigo = (Humano)tratamientoDeEnemigo; //Por defecto es Humano. En caso que no, entrara en los siguiente if's. //
			if(tratamientoDeEnemigo instanceof Orco)
			{
				enemigo = (Orco)tratamientoDeEnemigo;
			}
			if(tratamientoDeEnemigo instanceof Elfo)
			{
				enemigo = (Elfo)tratamientoDeEnemigo;
			}
			
	
			//Personaje personaje = paquetePelear.getPersonaje();
			//Personaje enemigo = paquetePelear.getEnemigo(); // Es el enemigo del objeto "personaje". //
			
			if (!enemigo.estaVivo()) {
				juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuGanarBatalla);
				if(personaje.ganarExperiencia(enemigo.getNivel() * 40)){
					juego.getPersonaje().setNivel(personaje.getNivel());
					juego.getPersonaje().setPuntosSkill(personaje.getPuntosSkill());
					juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuSubirNivel);
				}
				PaqueteFinalizarBatalla paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
				paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
				paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());
				paqueteFinalizarBatalla.setGanadorBatalla(juego.getPersonaje().getId());
				juego.getEstadoBatalla().finalizarBatalla(paqueteFinalizarBatalla);
				Estado.setEstado(juego.getEstadoJuego());
				
			} else {
				juego.getEstadoBatalla().setPersonaje(personaje);
				juego.getEstadoBatalla().setEnemigo(enemigo);
				juego.getEstadoBatalla().setMiTurno(false);
			}
		}
		*/
		
		
		public void finalizarBatalla(PaqueteFinalizarBatalla paqueteFinalizarBatalla, Personaje personaje, Personaje enemigo) {
			try {
				juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));
	
				EstadoBatalla.paquetePersonaje.setSaludTope(personaje.getSaludTope());
				EstadoBatalla.paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
				EstadoBatalla.paquetePersonaje.setNivel(personaje.getNivel());
				EstadoBatalla.paquetePersonaje.setExperiencia(personaje.getExperiencia());
				EstadoBatalla.paquetePersonaje.setDestreza(personaje.getDestreza());
				EstadoBatalla.paquetePersonaje.setFuerza(personaje.getFuerza());
				EstadoBatalla.paquetePersonaje.setInteligencia(personaje.getInteligencia());
	
				EstadoBatalla.paquetePersonaje.setPuntosSkill(personaje.getPuntosSkill());
				
				EstadoBatalla.paquetePersonaje.removerBonus();
	
				EstadoBatalla.paqueteEnemigo.setSaludTope(enemigo.getSaludTope());
				EstadoBatalla.paqueteEnemigo.setEnergiaTope(enemigo.getEnergiaTope());
				EstadoBatalla.paqueteEnemigo.setNivel(enemigo.getNivel());
				EstadoBatalla.paqueteEnemigo.setExperiencia(enemigo.getExperiencia());
				EstadoBatalla.paqueteEnemigo.setDestreza(enemigo.getDestreza());
				EstadoBatalla.paqueteEnemigo.setFuerza(enemigo.getFuerza());
				EstadoBatalla.paqueteEnemigo.setInteligencia(enemigo.getInteligencia());
	
				EstadoBatalla.paqueteEnemigo.removerBonus();
	
				EstadoBatalla.paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
				EstadoBatalla.paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);
	
				juego.getCliente().getSalida().writeObject(gson.toJson(EstadoBatalla.paquetePersonaje));
				juego.getCliente().getSalida().writeObject(gson.toJson(EstadoBatalla.paqueteEnemigo));
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
			}
		}

}
