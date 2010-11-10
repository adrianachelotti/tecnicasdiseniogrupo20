package modelo.cliente;

import modelo.manejadorDeSucesos.Accion;
import modelo.manejadorDeSucesos.*;


public class AccionTirarGaseosa extends Accion {
	
	private ManejadorDeSucesos manejador;
	
	public AccionTirarGaseosa(ManejadorDeSucesos manejador){
		this.manejador = manejador;
	}
	
	public void ejecutar() {
		manejador.agregarSuceso(new Suceso("pocaGaseosa"));
	}

}
