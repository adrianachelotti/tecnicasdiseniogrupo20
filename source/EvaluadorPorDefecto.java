package source;

import java.util.List;

/**
 * Clase que modela un evaluador. 
 * Evalua un conjunto de sucesos con el antecedente de una implicacion,en el 
 * caso de que los sucesos a evaluar esten incluidos en el antecedente se 
 * ejecuta la accion del consecuente. 
 * 
 * @author Grupo20
 *
 */
public class EvaluadorPorDefecto extends Evaluador {

	/**
	 * Instancia unica de la clase.
	 */
	private static EvaluadorPorDefecto INSTANCIA = new EvaluadorPorDefecto();
	
	/**
	 * Constructor
	 */
	private EvaluadorPorDefecto(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del evaluador por defecto.
	 */
	public static EvaluadorPorDefecto obtenerInstancia(){
		return EvaluadorPorDefecto.INSTANCIA;
	}
	
	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,List<Suceso> sucesos) {
		if (sucesos.containsAll(implicacion.getSucesos()))
			implicacion.getAccion().ejecutar();
	}

}
