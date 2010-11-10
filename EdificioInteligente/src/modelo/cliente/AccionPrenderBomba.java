package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;

public class AccionPrenderBomba extends Accion{

	private Bomba bomba;
	
	public void ejecutar() {
		this.bomba.setEncendida(true);
	}
	
	public Bomba getBomba() {
		return bomba;
	}
	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}

	
}
