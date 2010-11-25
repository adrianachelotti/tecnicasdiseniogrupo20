package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de un televisor.
 * @author Grupo20
 *
 */
public class TelevisorDriver implements DriverDispositivo {

	public boolean encendida;
	
	private static final String nombre ="Televisor-Driver";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Apaga el televisor.
	 */
	public void apagar() {
		this.encendida=false;
	}

	/**
	 * Enciende el televisor.
	 */
	public void encender() {
		this.encendida=true;
	}

	/**
	 * Determina si el televisor esta encendido.
	 */
	public boolean isEncendido() {
		return this.encendida;
	}
}
