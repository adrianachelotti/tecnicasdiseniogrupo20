package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de una puerta.
 * @author Grupo20
 *
 */
public class PuertaDriver implements DriverDispositivo{

	private boolean encendida;
	
	private static final String nombre ="Puerta-Driver";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Cierra la puerta.
	 */
	public void apagar() {
		this.encendida = false;
	}

	/**
	 * Abre la puerta.
	 */
	public void encender() {
		this.encendida = true;
	}

	/**
	 * Determina si la puerta esta cerrada.
	 */
	public boolean isEncendido() {
		return encendida;
	}

}
