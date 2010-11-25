package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de una ventana.
 * @author Grupo20
 *
 */
public class VentanaDriver implements DriverDispositivo {
	
	public boolean encendida;
	
	private static final String nombre ="Ventana-Driver";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}

	/**
	 * Cierra la ventana.
	 */
	public void apagar() {
		this.encendida=false;
	}

	/**
	 * Abre la ventana.
	 */
	public void encender() {
		this.encendida=true;
	}

	/**
	 * Determina si la ventana se encuentra abierta.
	 */
	public boolean isEncendido() {
		return this.encendida;
	}
}
