package modelo.accion;

import modelo.manejadorDeSucesos.Accion;
/**
 * Clase que modela la accion de apagar un dispositivo.
 * @author Grupo20
 *
 */
public class AccionApagarDispositivo extends Accion {

	/**
	 * Constructor sin parametros.
	 */
	public AccionApagarDispositivo(){
		this.establecerNombre("Apagar");
	}
	
	@Override
	public void ejecutar() {
		this.obtenerDispositivo().apagar();

	}

}
