package source;

import java.util.Iterator;
import java.util.List;

public class CanceladorPorDefecto extends Cancelador {

	private static CanceladorPorDefecto INSTANCIA = new CanceladorPorDefecto();
	
	private CanceladorPorDefecto(){}
	
	public static CanceladorPorDefecto obtenerInstancia(){
		return CanceladorPorDefecto.INSTANCIA;
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
		
		while(it.hasNext()&& !cancelacion)
		{
			sucesoCancelable = it.next();
			cancelacion = seCancelan(sucesoCancelable, sucesoNuevo); 
		}
		
		if(cancelacion) sucesosExistentes.remove(sucesoCancelable);
				
	}
			

}
