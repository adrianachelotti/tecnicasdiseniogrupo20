package cliente;

import source.Accion;

public class AccionApagarDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().apagar();

	}

}
