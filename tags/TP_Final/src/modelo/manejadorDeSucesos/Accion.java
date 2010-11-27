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
	 * Nombre de la accion a realizar
	 */
	private  String nombre;
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

	/**
	 * Obtiene el nombre de la accion a realizar
	 * @return el nombre de la accion 
	 */
	public String obtenerNombre(){
		return nombre;	
	}
	/**
	 * Metodo para establecer el nombre de la accion a ejecutar
	 * @param nombre nombre de la accion
	 */
	public void establecerNombre(String nombre){
		this.nombre = nombre;
	}
}
