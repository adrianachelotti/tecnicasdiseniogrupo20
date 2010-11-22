package modelo.accion;

import modelo.manejadorDeSucesos.Accion;
public class AccionApagarDispositivo extends Accion {

	public AccionApagarDispositivo(){
		this.establecerNombre("Apagar");
	}
	
	@Override
	public void ejecutar() {
		this.obtenerDispositivo().apagar();

	}

}
