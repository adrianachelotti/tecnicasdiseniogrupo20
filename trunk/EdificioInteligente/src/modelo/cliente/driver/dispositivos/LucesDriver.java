package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de una luz.
 * @author Grupo20
 *
 */
public class LucesDriver implements DriverDispositivo{
	
	private boolean encendida;
	
	private static final String nombre ="Luces-Driver";

	/**
	 * Constructor sin parametros.
	 */
	public LucesDriver(){
		this.encendida = false;
	}
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Apaga el dispositivo.
	 */
	public void apagar() {
		this.encendida = false;
	}

	/**
	 * Enciende el dispositivo.
	 */
	public void encender() {
		this.encendida = true;
	}

	/**
	 * Determina si la luz esta encendida.
	 */
	public boolean isEncendido() {
		return this.encendida;
	}

}
