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
	 * @param sucesoCancelable suceso existente a evaluar si se cancela.
	 * @param sucesoCancelador suceso nuevo a evaluar si se cancela.
	 * @return true si los sucesos se cancelan.
	 * 		   false en caso contrario.
	 */
	protected boolean seCancelan(Suceso sucesoCancelable, Suceso sucesoCancelador)
	{
		if(sucesoCancelable.getIdSucesoCancelador()==null && sucesoCancelador.getIdSucesoCancelador()==null)
			return false;
				
		if(sucesoCancelador.getIdSucesoCancelador()!=null && sucesoCancelador.getIdSucesoCancelador().equals(sucesoCancelable.getIdSuceso()))
			return true;
		
		return false;
	}
}
