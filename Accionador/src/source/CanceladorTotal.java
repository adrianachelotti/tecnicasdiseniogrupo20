package source;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CanceladorTotal extends Cancelador {

	private static CanceladorTotal INSTANCIA = new CanceladorTotal();
	
	private CanceladorTotal(){}
	
	public static CanceladorTotal obtenerInstancia(){
		return CanceladorTotal.INSTANCIA;
	}

	@Override
	public void cancelarSucesos(List<Suceso> sucesosExistentes,	List<Suceso> sucesosNuevos) {
		for(Suceso sucesoActual: sucesosNuevos)
		{
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
	
	
	/*
	@Override
	public void cancelarSuceso(List<Suceso> sucesosExistentes,Suceso sucesoNuevo) {
		
		Iterator<Suceso> it = sucesosExistentes.iterator();
		Suceso sucesoCancelable = null;
		boolean cancelacion = false;
		
		while(it.hasNext()&& !cancelacion)
		{
			sucesoCancelable = it.next();
			cancelacion = seCancelan(sucesoCancelable, sucesoNuevo); 
		}
		
		if(cancelacion){
			
			List<Suceso> listaSucesosCanselables = new ArrayList<Suceso>();
			listaSucesosCanselables.add(sucesoCancelable);
			
			List<Suceso> listaAux = new ArrayList<Suceso>();
			for(Suceso s: sucesosExistentes){
				listaAux.add(s);
			}
						
			listaAux.retainAll(listaSucesosCanselables);
			sucesosExistentes.remove(listaAux);
		}
	}
	*/
}
