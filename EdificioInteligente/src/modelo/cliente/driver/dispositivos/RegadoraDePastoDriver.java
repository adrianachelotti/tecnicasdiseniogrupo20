package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

/**
 * Clase que modela el driver de una regadora de pasto.
 * @author Grupo20
 *
 */
public class RegadoraDePastoDriver implements DriverDispositivo{

	public boolean encendida;
	
	private static final String nombre ="Regadora-Driver";
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Apaga la regadora de pasto.
	 */
	public void apagar() {
		this.encendida=false;
	}

	/**
	 * Enciende la regadora de pasto.
	 */
	public void encender() {
		this.encendida=true;
	}

	/**
	 * Determina si la regadora de pasto esta encendida.
	 */
	public boolean isEncendido() {
		return this.encendida;
	}
}
