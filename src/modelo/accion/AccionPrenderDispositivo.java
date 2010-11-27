package modelo.accion;

import modelo.manejadorDeSucesos.Accion;

/**
 * Clase que modela la accion de prender un dispositivo.
 * @author Grupo20
 *
 */
public class AccionPrenderDispositivo extends Accion {

	/**
	 * Constructor sin parametros.
	 */
	public AccionPrenderDispositivo(){
		this.establecerNombre("Encender");
	}
	@Override
	public void ejecutar() {
		this.obtenerDispositivo().encender();
	}

}
