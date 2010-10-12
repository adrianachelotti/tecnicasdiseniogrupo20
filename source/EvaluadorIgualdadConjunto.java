package source;

import java.util.List;

/**
 * Clase que modela un evaluador.
 * Evalua un conjunto de sucesos con el antecedente de una implicacion en el
 * caso de que los conjuntos sean iguales se ejecuta la accion del consecuente. 
 * 
 * @author Grupo20
 *
 */
public class EvaluadorIgualdadConjunto extends Evaluador {

	/**
	 * Instancia unica de la clase.
	 */
	private static EvaluadorIgualdadConjunto INSTANCIA = new EvaluadorIgualdadConjunto();
	
	/**
	 * Constructor
	 */
	private EvaluadorIgualdadConjunto(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del evaluador igualdad conjunto.
	 */
	public static EvaluadorIgualdadConjunto obtenerInstancia(){
		return EvaluadorIgualdadConjunto.INSTANCIA;
	}
	
	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,	List<Suceso> sucesos) {
		if (sucesos.containsAll(implicacion.getSucesos())&&(implicacion.getSucesos().size()==sucesos.size()))
			implicacion.getAccion().ejecutar();		
	}

}
