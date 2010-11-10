package cliente;

import source.Accion;

public class AccionPrenderDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().encender();
	}

}
