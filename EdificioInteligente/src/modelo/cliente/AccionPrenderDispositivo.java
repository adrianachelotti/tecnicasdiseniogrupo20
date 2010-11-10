package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;

public class AccionPrenderDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().encender();
	}

}
