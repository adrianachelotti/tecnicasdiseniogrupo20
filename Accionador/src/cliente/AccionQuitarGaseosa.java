package cliente;

import source.Accion;

public class AccionQuitarGaseosa extends Accion {

	private Gaseosa gaseosa ;
	
	
	public Gaseosa getGaseosa() {
		return gaseosa;
	}
	public void setGaseosa(Gaseosa gaseosa) {
		this.gaseosa = gaseosa;
	}
	public void ejecutar() {
		this.gaseosa.descontarGaseosa();
				
	}

}
