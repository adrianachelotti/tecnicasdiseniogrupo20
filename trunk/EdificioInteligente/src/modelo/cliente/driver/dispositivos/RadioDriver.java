package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de una radio.
 * @author Grupo20
 *
 */
public class RadioDriver implements DriverDispositivo{

	private boolean encendida;
	
	private static final String nombre ="Radio-Driver";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Apaga la radio.
	 */
	public void apagar() {
		this.encendida = false;		
	}

	/**
	 * Enciende la radio.
	 */
	public void encender() {
		this.encendida = true;
	}

	/**
	 * Determina si la radio esta encendida.
	 */
	public boolean isEncendido() {
		return encendida;
	}

}
