package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;

public class AccionAgregarGaseosa extends Accion{
		
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
