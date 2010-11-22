package modelo.manejadorDeSucesos;

import java.util.List;

/**
 * Clase abstracta que estable la estructura de un evaluador.
 * Evalua un conjunto de sucesos con el antecedente de una implicacion en
 * caso de cumplirse se ejecuta el consecuente.
 * 
 * @author Grupo20
 *
 */
public abstract class Evaluador {

	/**
	 * Evalua si los sucesos cumplen con el antecedente de la implicacion.
	 * En caso afirmativo se ejecuta la accion de la implicacion.
	 * @param implicacion implicacion a evaluar.
	 * @param sucesos sucesos a evaluar.
	 */
	public abstract void avisarSucesosOcurridos(Implicacion implicacion, List<Suceso> sucesos);

}
