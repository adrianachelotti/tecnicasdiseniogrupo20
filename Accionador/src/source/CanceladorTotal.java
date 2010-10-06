package source;

import java.util.List;

public class CanceladorTotal extends Cancelador {

	private static CanceladorTotal INSTANCIA = new CanceladorTotal();
	
	private CanceladorTotal(){}
	
	public static CanceladorTotal obtenerInstancia(){
		return CanceladorTotal.INSTANCIA;
	}

	@Override
	public void cancelarSucesos(List<Suceso> sucesosExistentes,	List<Suceso> sucesosNuevos) {
		
	}

	@Override
	public void cancelarSuceso(List<Suceso> sucesosExistentes,Suceso sucesoNuevo) {
		
		
	}
	
	

}
