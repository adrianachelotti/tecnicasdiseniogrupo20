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
	 * @param suceso1 suceso a evaluar.
	 * @param suceso2 suceso a evaluar.
	 * @return true si los sucesos se cancelan.
	 * 		   false en caso contrario.
	 */
	protected boolean seCancelan(Suceso suceso1, Suceso suceso2)
	{
		String idSuceso1 = suceso1.getIdSuceso();
		String idSuceso2 = suceso2.getIdSuceso();
		String idCanceladorSuceso1 = suceso1.getIdSucesoCancelador();
		String idCanceladorSuceso2 = suceso2.getIdSucesoCancelador();
		
		if(idCanceladorSuceso1==null && idCanceladorSuceso2==null)
			return false;
		
		if(idCanceladorSuceso1!=null && idCanceladorSuceso1.equals(idSuceso2))
			return true;
		
		if(idCanceladorSuceso2!=null && idCanceladorSuceso2.equals(idSuceso1))
			return true;
		
		return false;
	}
}
