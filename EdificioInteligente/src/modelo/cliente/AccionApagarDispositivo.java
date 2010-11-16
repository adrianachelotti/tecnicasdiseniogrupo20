package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;
public class AccionApagarDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().apagar();

	}

}
