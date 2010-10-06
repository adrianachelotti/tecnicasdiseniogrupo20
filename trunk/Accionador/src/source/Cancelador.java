package source;

import java.util.List;

public abstract class Cancelador {

	public abstract void cancelarSucesos(List<Suceso> sucesosExistentes,List<Suceso> sucesosNuevos);

	public abstract void cancelarSuceso(List<Suceso> sucesosExistentes,	Suceso sucesoNuevo) ;
	
	public boolean seCancelan(Suceso suceso1, Suceso suceso2)
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
