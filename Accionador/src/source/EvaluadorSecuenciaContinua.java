package source;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela un evaluador. 
 * Evalua un conjunto de sucesos con el antecedente de una implicacion,en el 
 * caso de que los sucesos a evaluar esten incluidos en el antecedente formando una
 * secuencia continua se ejecuta la accion del consecuente. 
 * 
 * @author Grupo20
 *
 */
public class EvaluadorSecuenciaContinua extends Evaluador {
	
	/**
	 * Instancia unica de la clase.
	 */
	private static EvaluadorSecuenciaContinua INSTANCIA = new EvaluadorSecuenciaContinua();
	
	/**
	 * Constructor
	 */
	private EvaluadorSecuenciaContinua(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del evaluador de secuencia continua.
	 */
	public static EvaluadorSecuenciaContinua obtenerInstancia(){
		return EvaluadorSecuenciaContinua.INSTANCIA;
	}
		
	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,List<Suceso> sucesos) {
		
		int tamanioAntecedente = implicacion.getSucesos().size();
		int tamanioSucesos = sucesos.size(); 
		
		if(tamanioAntecedente == tamanioSucesos){
			if (implicacion.getSucesos().equals(sucesos))
				implicacion.getAccion().ejecutar();
		}
		else if(tamanioSucesos >= tamanioAntecedente)
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
					if (implicacion.getSucesos().equals(listAux)){
						implicacion.getAccion().ejecutar();
						valido= false;
					}
					posicionInicio++;
				}
				else 
					valido = false;
			}
		}
		
	}
	
	
}
