package modelo.manejadorDeSucesos;

import modelo.edificio.Dispositivo;

/**
 * Clase abstracta que deben heredar las acciones que se quieren efectuar.
 * 
 * @author Grupo20
 *
 */
public abstract class Accion {
	 
	/**
	 * Dispositivo sobre el cual se realizan la accion
	 */
	private Dispositivo dispositivo;

	/**
	 * Metodo en donde se deben implementar las acciones a realizar.
	 */
	public abstract void ejecutar();
	
	/**
	 * Metodo para obtener el dispositivo sobre el cual se 
	 * realiza la accion
	 * @return dispositivo actual
	 */
	public Dispositivo obtenerDispositivo() {
		return dispositivo;
	}

	/**
	 * Metodo para establecer el dispositivo sobre el cual se 
	 * realiza la accion
	 * @param dispositivo a establecer
	 */
	public void establecerDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

}
