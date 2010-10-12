package source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que modela un cancelador, se produce la cancelacion de todos los sucesos
 * que sean cancelables.
 * 
 * @author Grupo20
 *
 */
public class CanceladorTotal extends Cancelador {

	/**
	 * Instancia unica de la clase.
	 */
	private static CanceladorTotal INSTANCIA = new CanceladorTotal();
	
	/**
	 * Constructor
	 */
	private CanceladorTotal(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del cancelador total.
	 */
	public static CanceladorTotal obtenerInstancia(){
		return CanceladorTotal.INSTANCIA;
	}

	@Override
	public void cancelarSucesos(List<Suceso> sucesosExistentes,	List<Suceso> sucesosNuevos) {
		for(Suceso sucesoActual: sucesosNuevos){
			cancelarSuceso(sucesosExistentes, sucesoActual);
		}	
	}
	
	@Override
	public void cancelarSuceso(List<Suceso> sucesosExistentes,Suceso sucesoNuevo) {
		Iterator<Suceso> it = sucesosExistentes.iterator();
		Suceso sucesoCancelable = null;
		boolean cancelacion = false;
		List<Suceso> listaSucesosCancelables = new ArrayList<Suceso>();
		while(it.hasNext()){
			sucesoCancelable = it.next();
			if(seCancelan(sucesoCancelable, sucesoNuevo)){
				listaSucesosCancelables.add(sucesoCancelable);
				cancelacion = true; 
			}
		}
		if(cancelacion){
			List<Suceso> listaAux = new ArrayList<Suceso>();
			listaAux.addAll(sucesosExistentes);
			listaAux.retainAll(listaSucesosCancelables);
			sucesosExistentes.removeAll(listaAux);
		}
	}

}
