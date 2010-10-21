package source;

import java.util.List;

/**
 * Clase abstracta que estable la estructura de un cancelador.
 * 
 * @author Grupo20
 *
 */
public abstract class Cancelador {

	/**
	 * Cancela los sucesos cancelables entre los conjuntos pasados como parametro.
	 * @param sucesosExistentes conjunto de sucesos existentes.
	 * @param sucesosNuevos conjunto de sucesos nuevos.
	 */
	public abstract void cancelarSucesos(List<Suceso> sucesosExistentes,List<Suceso> sucesosNuevos);

	/**
	 * Cancela los sucesos cancelables entre el conjunto y el suceso pasados como parametro,
	 * @param sucesosExistentes conjunto de sucesos existentes.
	 * @param sucesoNuevo suceso nuevo.
	 */
	public abstract void cancelarSuceso(List<Suceso> sucesosExistentes,	Suceso sucesoNuevo) ;
	
	/**
	 * Determina si dos sucesos son cancelables entre si.
	 * @param sucesoExistente suceso a evaluar.
	 * @param sucesoNuevo suceso a evaluar.
	 * @return true si los sucesos se cancelan.
	 * 		   false en caso contrario.
	 */
	protected boolean seCancelan(Suceso sucesoExistente, Suceso sucesoNuevo)
	{
		
		if(sucesoExistente.getIdSucesoCancelador()==null && sucesoNuevo.getIdSucesoCancelador()==null)
			return false;
		
		/*if(idCanceladorSucesoExistente!=null && idCanceladorSucesoExistente.equals(idSucesoNuevo))
			return true;*/
		
		if(sucesoNuevo.getIdSucesoCancelador()!=null && sucesoNuevo.getIdSucesoCancelador().equals(sucesoExistente.getIdSuceso()))
			return true;
		
		return false;
	}
}
