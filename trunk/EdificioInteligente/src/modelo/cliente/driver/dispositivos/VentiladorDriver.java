package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de un ventilador.
 * @author Grupo20
 *
 */
public class VentiladorDriver implements DriverDispositivo{
	public boolean encendido;
	
	private static final String nombre ="Ventilador";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Apaga el ventilador.
	 */
	public void apagar() {
		this.encendido=false;
	}

	/**
	 * Enciende el ventilador.
	 */
	public void encender() {
		this.encendido=true;
	}

	/**
	 * Determina si el ventilador esta encendido.
	 */
	public boolean isEncendido() {
		return this.encendido;
	}
}
