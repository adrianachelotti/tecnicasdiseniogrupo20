package cliente;

import source.Accion;
import source.ManejadorDeSucesos;
import source.Suceso;

public class AccionTirarGaseosa implements Accion {
	
	private ManejadorDeSucesos manejador;
	
	public AccionTirarGaseosa(ManejadorDeSucesos manejador){
		this.manejador = manejador;
	}
	
	public void ejecutar() {
		manejador.agregarSuceso(new Suceso("pocaGaseosa"));
	}

}
