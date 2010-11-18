package modelo.accion;

import modelo.manejadorDeSucesos.Accion;
public class AccionApagarDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().apagar();

	}

}
