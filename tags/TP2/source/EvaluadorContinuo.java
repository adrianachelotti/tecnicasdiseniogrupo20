package source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que modela un evaluador. 
 * Evalua un conjunto de sucesos con el antecedente de una implicacion,en el 
 * caso de que los sucesos a evaluar esten incluidos de manera continua
 * en el antecedente se ejecuta la accion del consecuente. 
 * 
 * @author Grupo20
 *
 */
public class EvaluadorContinuo extends Evaluador {

	/**
	 * Instancia unica de la clase.
	 */
	private static EvaluadorContinuo INSTANCIA = new EvaluadorContinuo();
	
	/**
	 * Constructor
	 */
	private EvaluadorContinuo(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del evaluador 
	 */
	public static EvaluadorContinuo obtenerInstancia(){
		return EvaluadorContinuo.INSTANCIA;
	}
		
	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,List<Suceso> sucesos) {
		int tamanioAntecedente = implicacion.getSucesos().size();
		int tamanioSucesos = sucesos.size(); 
		
		if(tamanioSucesos >= tamanioAntecedente)
		{
			int posicionInicio = 0;
			int posicionFin = 0;
			boolean valido = true;
			List<Suceso> listAux = new ArrayList<Suceso>();
			
			while(valido)
			{
				posicionFin = posicionInicio + tamanioAntecedente;
				if(posicionFin<=tamanioSucesos){
					listAux = sucesos.subList(posicionInicio, posicionFin);
					
					if (evaluar(implicacion.getSucesos(),listAux)){
						implicacion.getAccion().ejecutar();
						implicacion.setOrdenUltimaSuscripcion(ManejadorDeSucesos.obtenerOrdenDeSuscripcion());
						valido= false;
					}
					posicionInicio++;
				}
				else 
					valido = false;
			}
		}

	}
	
	/**
	 * Evalua si el antecedente se encuentra en el conjunto de sucesos.
	 * @param antecedente 
	 * @param sucesos
	 * @return
	 */
	private boolean evaluar(List<Suceso> antecedente, List<Suceso> sucesos)
	{
		int posicionElementoAEliminar = 0;
		boolean encontrado = true;
		List<Suceso> listAux = new ArrayList<Suceso>();
		listAux.addAll(sucesos);		
				
		Iterator<Suceso> it = antecedente.iterator();
		Suceso sucesoAux = null;			
		while(it.hasNext() && encontrado){
			sucesoAux = it.next();
			posicionElementoAEliminar = listAux.indexOf(sucesoAux);
			if(posicionElementoAEliminar<0)
				encontrado = false;
			else
				listAux.remove(posicionElementoAEliminar);
		}
		return encontrado;
	}
	

}
