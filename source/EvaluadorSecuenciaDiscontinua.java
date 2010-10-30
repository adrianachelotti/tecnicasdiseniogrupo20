package source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que modela un evaluador. 
 * Evalua un conjunto de sucesos con el antecedente de una implicacion,en el 
 * caso de que los sucesos a evaluar esten incluidos en el antecedente formando una
 * secuencia se ejecuta la accion del consecuente. 
 * 
 * @author Grupo20
 *
 */
public class EvaluadorSecuenciaDiscontinua extends Evaluador {

	/**
	 * Instancia unica de la clase.
	 */
	private static EvaluadorSecuenciaDiscontinua INSTANCIA = new EvaluadorSecuenciaDiscontinua();
	
	/**
	 * Constructor
	 */
	private EvaluadorSecuenciaDiscontinua(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del evaluador de secuencia discontinua.
	 */
	public static EvaluadorSecuenciaDiscontinua obtenerInstancia(){
		return EvaluadorSecuenciaDiscontinua.INSTANCIA;
	}
	
	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,	List<Suceso> sucesos) {
		
		int tamanioAntecedente = implicacion.getSucesos().size();
		int tamanioSucesos = sucesos.size(); 
		
		if(tamanioAntecedente == tamanioSucesos){
			if (implicacion.getSucesos().equals(sucesos)){
				implicacion.getAccion().ejecutar();
				implicacion.setOrdenUltimaSuscripcion(ManejadorDeSucesos.obtenerOrdenDeSuscripcion());
			}
		}
		else if(tamanioSucesos >= tamanioAntecedente){
			int posicionInicio = 0;
			boolean encontrado = true;
			List<Suceso> listAux = new ArrayList<Suceso>();
			
			Iterator<Suceso> it = implicacion.getSucesos().iterator();
			Suceso sucesoAux = null;			
			
			while(it.hasNext() && encontrado){
				sucesoAux = it.next();
				listAux = sucesos.subList(posicionInicio, tamanioSucesos);
				posicionInicio = listAux.indexOf(sucesoAux);
				if(posicionInicio<0)
					encontrado = false;
				else{
					posicionInicio++;
					if(posicionInicio>=tamanioSucesos)
						encontrado = false;
				}
			}
			if(encontrado){
				implicacion.getAccion().ejecutar();
				implicacion.setOrdenUltimaSuscripcion(ManejadorDeSucesos.obtenerOrdenDeSuscripcion());
			}
		}
	}
	
}
