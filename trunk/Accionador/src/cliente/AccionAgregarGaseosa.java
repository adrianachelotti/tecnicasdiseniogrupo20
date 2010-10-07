package cliente;

import source.Accion;

public class AccionAgregarGaseosa implements Accion{
	private boolean accionEjecuta;
	
	private Gaseosa gaseosa ;
	
	
	public Gaseosa getGaseosa() {
		return gaseosa;
	}
	public void setGaseosa(Gaseosa gaseosa) {
		this.gaseosa = gaseosa;
	}
	public void ejecutar() {
	this.gaseosa.aumentarGaseosa();
				
	}

}
