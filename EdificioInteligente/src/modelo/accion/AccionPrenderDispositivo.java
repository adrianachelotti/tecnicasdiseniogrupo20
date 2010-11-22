package modelo.accion;

import modelo.manejadorDeSucesos.Accion;

public class AccionPrenderDispositivo extends Accion {

	public AccionPrenderDispositivo(){
		this.establecerNombre("Encender");
	}
	@Override
	public void ejecutar() {
		this.obtenerDispositivo().encender();
	}

}
