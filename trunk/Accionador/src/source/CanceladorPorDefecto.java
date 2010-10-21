package source;

import java.util.Iterator;
import java.util.List;

/**
 * Clase que modela un cancelador,las cancelaciones de sucesos cancelables
 * entre si se producen uno a uno.
 * 
 * @author Grupo20
 *
 */
public class CanceladorPorDefecto extends Cancelador {

	/**
	 * Instancia unica de la clase.
	 */
	private static CanceladorPorDefecto INSTANCIA = new CanceladorPorDefecto();
	
	/**
	 * Constructor
	 */
	private CanceladorPorDefecto(){}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return instancia del cancelador por defecto.
	 */
	public static CanceladorPorDefecto obtenerInstancia(){
		return CanceladorPorDefecto.INSTANCIA;
	}

	@Override
	public void cancelarSucesos(List<Suceso> sucesosExistentes,	List<Suceso> sucesosNuevos) {
		
		for(Suceso sucesoActual: sucesosNuevos){
			cancelarSuceso(sucesosExistentes, sucesoActual);
		}
	}
	
	@Override
	public void cancelarSuceso(List<Suceso> sucesosExistentes,Suceso sucesoNuevo){
		Iterator<Suceso> it = sucesosExistentes.iterator();
		Suceso sucesoCancelable = null;
		boolean cancelacion = false;
		while(it.hasNext()&& !cancelacion){
			sucesoCancelable = it.next();
			cancelacion = seCancelan(sucesoCancelable, sucesoNuevo); 
		}
		if(cancelacion) sucesosExistentes.remove(sucesoCancelable);
	}

}
