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

	private boolean seCancelan(Suceso suceso1, Suceso suceso2)
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
