package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de un calefactor.
 * @author Grupo20
 *
 */
public class CalefactorDriver implements DriverDispositivo{

	private boolean encendido =false;
	
	private static final String nombre ="Calefactor-Driver";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Apaga el calefactor.
	 */
	public void apagar() {
		this.encendido = false;
	}

	/**
	 * Enciende el calefactor.
	 */
	public void encender() {
		this.encendido = true;
	}

	/**
	 * Determina si el calefactor esta encendido.
	 */
	public boolean isEncendido() {
		return encendido;
	}
	
}
