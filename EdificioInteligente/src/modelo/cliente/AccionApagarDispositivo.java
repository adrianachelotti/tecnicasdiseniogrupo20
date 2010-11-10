package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;
import modelo.dispositivos.*;
public class AccionApagarDispositivo extends Accion {

	@Override
	public void ejecutar() {
		this.obtenerDispositivo().apagar();

	}

}
