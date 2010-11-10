package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;

public class AccionCliente extends Accion{
	boolean accionEjecutada = false;
	
	public boolean isAccionEjecutada() {
		return accionEjecutada;
	}

	public void setAccionEjecutada(boolean accionEjecutada) {
		this.accionEjecutada = accionEjecutada;
	}

	public void ejecutar() {
		accionEjecutada= true;
	}

}
