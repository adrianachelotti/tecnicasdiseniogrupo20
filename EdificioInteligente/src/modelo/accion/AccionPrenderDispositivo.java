package modelo.accion;

import modelo.manejadorDeSucesos.Accion;

public class AccionPrenderDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().encender();
	}

}
